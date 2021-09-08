package com.springboot.controller;

import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response, @RequestParam(value = "startTime", required = false) Date startTime,
                            @RequestParam(value = "endTime", required = false) Date endTime) {
        userService.userExport(response, startTime, endTime);
    }

    @GetMapping("/importExcel")
    public void importExcel(@RequestParam("file") MultipartFile file) {
        userService.userImport(file);
    }
}
