package com.springboot.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.springboot.bo.ExportBo;
import org.apache.poi.ss.usermodel.Workbook;

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
}