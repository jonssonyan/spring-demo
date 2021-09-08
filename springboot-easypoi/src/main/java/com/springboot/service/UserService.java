package com.springboot.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public interface UserService {
    /**
     * 导出
     *
     * @param response
     * @param startTime
     * @param endTime
     */
    void userExport(HttpServletResponse response, Date startTime, Date endTime);

    /**
     * 导入
     *
     * @param file
     */
    void userImport(MultipartFile file);
}