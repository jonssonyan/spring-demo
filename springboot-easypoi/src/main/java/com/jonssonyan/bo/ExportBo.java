package com.jonssonyan.bo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * excel导出业务对象
 */
@Data
@Builder
public class ExportBo {
    private String sheetName;
    private String title;
    private Class<?> clazz;
    private List<?> data;

    public ExportBo(String sheetName, String title, Class<?> clazz, List<?> data) {
        this.sheetName = sheetName;
        this.title = title;
        this.clazz = clazz;
        this.data = data;
    }
}
