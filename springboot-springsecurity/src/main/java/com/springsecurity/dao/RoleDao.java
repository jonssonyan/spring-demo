package com.springsecurity.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springsecurity.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleDao extends BaseMapper<Role> {

}
