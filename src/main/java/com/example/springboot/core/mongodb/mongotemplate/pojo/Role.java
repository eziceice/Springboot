package com.example.springboot.core.mongodb.mongotemplate.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Document
@Getter
@Setter
public class Role implements Serializable {
    private Long id;

    @Field("role_name")
    private String roleName;

    private String note;
}
