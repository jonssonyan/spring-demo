package com.jonssonyan.vo;

import lombok.Data;

import java.util.List;

@Data
public class PDFExportVo {
    private String title;
    private Integer num;
    private List<String> list;
}
