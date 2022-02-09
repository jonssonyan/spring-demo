package com.jonssonyan.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.jonssonyan.bo.ExportBo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel导出工具类
 */
public class ExcelUtil {
    /**
     * 创建workbook,
     * 通过mapList填充Excel内容
     * 返回workbook
     */
    public static Workbook moreSheetSheet(List<ExportBo> list) {
        List<Map<String, Object>> mapListList = new ArrayList<>();
        list.forEach(exportBo -> {
            Map<String, Object> oneSheet = ExcelUtil.createOneSheet(exportBo.getSheetName(), exportBo.getTitle(), exportBo.getClazz(), exportBo.getData());
            mapListList.add(oneSheet);
        });
        return ExcelExportUtil.exportExcel(mapListList, ExcelType.XSSF);
    }

    public static Map<String, Object> createOneSheet(ExportParams exportParams, Class<?> clazz, List<?> data) {
        Map<String, Object> map = new HashMap<>();
        map.put("title", exportParams);
        map.put("entity", clazz);
        map.put("data", data);
        return map;
    }

    /**
     * 创建一个表格并填充内容
     * 返回map供工作簿使用
     */
    public static Map<String, Object> createOneSheet(String sheetName, String title, Class<?> clazz, List<?> data) {
        ExportParams exportParams = new ExportParams(title, sheetName, ExcelType.XSSF);
        return createOneSheet(exportParams, clazz, data);
    }

    /**
     * 通过反射隐藏导出的字段
     *
     * @param o          导出对象
     * @param columnName 列名
     * @param target     是否隐藏
     * @throws Exception
     */
    public void hiddenColumn(Object o, String columnName, Boolean target) throws Exception {
        Assert.notNull(o, "TARGET OBJECT NOT FOUNT");
        Assert.notNull(columnName, "COLUMN NAME NOT NULL");
        if (target == null) target = true;
        //获取目标对象的属性值
        Field field = o.getClass().getDeclaredField(columnName);
        //获取注解反射对象
        Excel excelAnnotation = field.getAnnotation(Excel.class);
        //获取代理
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(excelAnnotation);
        Field excelField = invocationHandler.getClass().getDeclaredField("memberValues");
        excelField.setAccessible(true);
        Map memberValues = (Map) excelField.get(invocationHandler);
        memberValues.put("isColumnHidden", target);
    }
}