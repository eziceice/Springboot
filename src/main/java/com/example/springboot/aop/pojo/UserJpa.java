package com.example.springboot.aop.pojo;

import com.example.springboot.aop.converter.SexConverter;
import com.example.springboot.aop.enumeration.SexEnum;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserJpa {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    private String note;

    private SexEnum sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }
}
