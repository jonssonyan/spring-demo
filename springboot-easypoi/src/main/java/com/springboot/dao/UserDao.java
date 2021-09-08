package com.springboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.entity.User;
import com.springboot.vo.UserExportVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface UserDao extends BaseMapper<User> {
    List<UserExportVo> select(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    void insertList(@Param("userExportVos") List<UserExportVo> userExportVos);
}
