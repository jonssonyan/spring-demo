package com.springboot.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.dao.UserDao;
import com.springboot.entity.User;
import com.springboot.entity.export.UserExportVO;
import com.springboot.service.UserService;
import com.springboot.utils.ExcelUtil;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void userExport(HttpServletResponse response, Date startTime, Date endTime) {
        List<UserExportVO> userExportVOS = userDao.select(startTime, endTime);
        String title = "用户信息表";
        if (startTime != null && endTime != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String startTimeStr = format.format(startTime);
            String endTimeStr = format.format(endTime);
            title = startTimeStr + "至" + endTimeStr + "用户信息表";
        }
        Map<String, Object> oneSheet = ExcelUtil.createOneSheet(title, title, UserExportVO.class, userExportVOS);
        List<Map<String, Object>> list = Lists.newArrayList();
        list.add(oneSheet);
        Workbook workbook = ExcelUtil.mutiSheet(list);
        //通过输出流输出文件
        OutputStream os = null;
        try {
            response.setContentType("application/msexcel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(title.getBytes(), StandardCharsets.ISO_8859_1) + ".xlsx");
            response.setCharacterEncoding("UTF-8");
            os = response.getOutputStream();
            workbook.write(os);
        } catch (IOException e) {
            // 打印异常
            log.error("导出异常：", e);
        } finally {
            // 关闭资源
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void userImport(MultipartFile file) {
        List<UserExportVO> userExportVOS = Lists.newArrayList();
        try {
            ImportParams importParams = new ImportParams();
            importParams.setHeadRows(2);
//            importParams.setTitleRows(0);
            userExportVOS = ExcelImportUtil.importExcel(file.getInputStream(), UserExportVO.class, importParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userDao.insertList(userExportVOS);
    }
}