package com.jonssonyan.dao;

import com.jonssonyan.entity.Table2;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Table2Dao {
    void saveTable2(@Param("table2") Table2 table2);
}
