package com.example.springboot.aop.controller;

import com.example.springboot.aop.dao.JpaUserRepository;
import com.example.springboot.aop.pojo.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/jpa")
public class JpaController {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @RequestMapping("/getUser")
    @ResponseBody
    public UserJpa getUserJpa(Long id)
    {
        return jpaUserRepository.findById(id).get();
    }
}
