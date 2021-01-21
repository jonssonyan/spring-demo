package com.springboot.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    /**
     * 创建workbook,
     * 通过maplist填充Excel内容
     * 返回workbook
     * <p>
     * 进一步使用可以写入流,e.g.
     * FileOutputStream fos = new FileOutputStream(file);
     * workbook.write(fos);
     */
    public static Workbook mutiSheet(List<Map<String, Object>> mapListList) {
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
}