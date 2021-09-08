package com.springboot.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.bo.ExportBo;
import com.springboot.dao.UserDao;
import com.springboot.entity.User;
import com.springboot.service.UserService;
import com.springboot.util.ExcelUtil;
import com.springboot.vo.UserExportVo;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Async
    @Override
    public void userExport(HttpServletResponse response, Date startTime, Date endTime) {
        // 查询数据
        List<UserExportVo> userExportVos = userDao.select(startTime, endTime);

        String title = "用户信息表";
        if (startTime != null && endTime != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String startTimeStr = format.format(startTime);
            String endTimeStr = format.format(endTime);
            title = startTimeStr + "至" + endTimeStr + "用户信息表";
        }

        ExportBo exportBo = ExportBo.builder().sheetName(title).clazz(UserExportVo.class).data(userExportVos).build();
        ArrayList<ExportBo> exportBos = new ArrayList<>();
        exportBos.add(exportBo);
        Workbook workbook = ExcelUtil.moreSheetSheet(exportBos);
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
        List<UserExportVo> userExportVos = Lists.newArrayList();
        try {
            ImportParams importParams = new ImportParams();
            importParams.setHeadRows(2);
//            importParams.setTitleRows(0);
            userExportVos = ExcelImportUtil.importExcel(file.getInputStream(), UserExportVo.class, importParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userDao.insertList(userExportVos);
    }
}