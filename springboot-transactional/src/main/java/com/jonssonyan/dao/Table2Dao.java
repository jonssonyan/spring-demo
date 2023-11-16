package com.jonssonyan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jonssonyan.entity.Table1;
import com.jonssonyan.entity.Table2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface Table2Dao extends BaseMapper<Table2> {
}
