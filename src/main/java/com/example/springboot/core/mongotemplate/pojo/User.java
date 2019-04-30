package com.example.springboot.core.mongotemplate.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

@Document
@Getter
@Setter
public class User implements Serializable {

    @Id
    private Long id;

    @Field("user_name")
    private String userName;

    private String note;

    private List<Role> roles;
}
