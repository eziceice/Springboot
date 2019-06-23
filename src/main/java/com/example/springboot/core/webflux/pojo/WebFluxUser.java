package com.example.springboot.core.webflux.pojo;

import com.example.springboot.core.enumeration.SexEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.io.Serializable;

@Data
@Document
public class WebFluxUser implements Serializable {
    @Id
    private long id;
    private SexEnum sexEnum;
    @Field("user_name")
    private String userName;
    private String note;
}
