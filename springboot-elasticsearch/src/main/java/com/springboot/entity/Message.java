package com.springboot.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@Document(indexName = "message")
public class Message implements Serializable {
    private static final long serialVersionUID = -6443074144324096407L;
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String msgId;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String content;

    @Field(type = FieldType.Long)
    private Long createTime;
}