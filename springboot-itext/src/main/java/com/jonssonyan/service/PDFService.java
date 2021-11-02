package com.jonssonyan.service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

@Slf4j
@Service
public class PDFService {

    private static final String FONT_PATH = "fonts/simhei.ttf";

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;


    /**
     * freemarker渲染html
     *
     * @param data    数据
     * @param htmlTmp 模板文件地址
     * @return
     */
    public String freeMarkerRender(Map<String, Object> data, String htmlTmp) {
        Writer out = new StringWriter();
        try {
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(htmlTmp);
            template.process(data, out);
            out.flush();
            return out.toString();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * 根据模板生成pdf文件流
     *
     * @param content      文件内容
     * @param outputStream 输出流
     */
    public void createPdf(String content, OutputStream outputStream) {
        ITextRenderer render = new ITextRenderer();
        ITextFontResolver fontResolver = render.getFontResolver();
        try {
            fontResolver.addFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } catch (DocumentException | IOException e) {
            log.error(e.getMessage(), e);
        }
        // 解析html生成PDF
        render.setDocumentFromString(content);
        //解决图片相对路径的问题
        //render.getSharedContext().setBaseURL(LOGO_PATH);
        render.layout();
        try {
            render.createPDF(outputStream);
        } catch (DocumentException | IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}