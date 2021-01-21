package com.springboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.entity.User;
import com.springboot.entity.export.UserExportVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface UserDao extends BaseMapper<User> {
    List<UserExportVO> select(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    void insertList(@Param("userExportVO") List<UserExportVO> userExportVOS);
}
