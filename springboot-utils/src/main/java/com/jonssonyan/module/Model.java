package com.jonssonyan.module;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Model {
    @JSONField(format = "MMM dd, yyyy h:mm:ss aa")
    private Date date;

    @JSONField(format = "MMM-dd-yyyy h:mm:ss aa")
    public Date date2;
}
