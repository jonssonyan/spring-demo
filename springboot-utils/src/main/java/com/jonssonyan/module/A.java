package com.jonssonyan.module;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class A {
    /**
     * serialzeFeatures/parseFeatures: Bean和数组转换
     */
    @JSONField(ordinal = 4, serialzeFeatures = SerializerFeature.BeanToArray, parseFeatures = Feature.SupportArrayToBean)
    private List<User> userList;
    /**
     * name: 设置字段名
     */
    @JSONField(name = "ID", ordinal = 3)
    private int id;

    /**
     * serialize = false: 设置是否不序列化某字段
     */
    @JSONField(serialize = false, ordinal = 2)
    public Date date;

    /**
     * deserialize = false: 设置是否不反序列化某字段
     * ordinal: 设置字段顺序
     */
    @JSONField(deserialize = false, ordinal = 1)
    public Date date2;
}
