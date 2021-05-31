package com.springboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao extends BaseMapper<User> {
    /**
     * 通过用户id查询角色
     *
     * @param id
     * @return
     */
    User findRoles(@Param("id") Long id);

    /**
     * 通过用户名称查询包含角色
     *
     * @param username
     * @return
     */
    User selectByUsername(@Param("username") String username);
}
