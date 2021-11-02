package com.jonssonyan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jonssonyan.entity.ConsumerTable;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ConsumerDao extends BaseMapper<ConsumerTable> {
}
