package com.jonssonyan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jonssonyan.entity.Table1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface Table1Dao extends BaseMapper<Table1> {
}
