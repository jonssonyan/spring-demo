package com.springboot.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public interface UserService {
    void userExport(HttpServletResponse response, Date startTime, Date endTime);

    void userImport(MultipartFile file);
}