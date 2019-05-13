package com.example.springboot.core.rest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Getter
@Setter
@ToString
public class UserDTO {
    private Long id;

    private String userName;

    private String note;
}
