package com.jonssonyan.dao;

import com.jonssonyan.entity.Table1;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Table1Dao {
    void saveTable1(@Param("table1") Table1 table1);
}
