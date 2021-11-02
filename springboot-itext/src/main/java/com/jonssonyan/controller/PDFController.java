package com.jonssonyan.controller;

import cn.hutool.core.bean.BeanUtil;
import com.jonssonyan.service.PDFService;
import com.jonssonyan.vo.PDFExportVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

@RestController
@Slf4j
public class PDFController {

    @Autowired
    private PDFService pdfService;

    @GetMapping("/exportToPdf")
    public void exportToPdf(HttpServletResponse response) {

        // 文件名称
        String fileName = new String("测试.pdf".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));

        // 数据对象
        PDFExportVo pdfExportVo = new PDFExportVo();
        pdfExportVo.setTitle("这是标题");
        pdfExportVo.setNum(1);
        pdfExportVo.setList(Arrays.asList("第一段", "第二段", "第三段"));

        Map<String, Object> data = BeanUtil.beanToMap(pdfExportVo);
        try {
            String content = pdfService.freeMarkerRender(data, "test.ftl");
            pdfService.createPdf(content, response.getOutputStream());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
