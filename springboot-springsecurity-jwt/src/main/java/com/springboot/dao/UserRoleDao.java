package com.springboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRoleDao extends BaseMapper<UserRole> {
}
