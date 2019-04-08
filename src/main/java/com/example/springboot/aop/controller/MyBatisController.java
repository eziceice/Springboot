package com.example.springboot.aop.controller;

import com.example.springboot.aop.pojo.User;
import com.example.springboot.aop.service.MybatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mybatis")
public class MyBatisController {

    @Autowired
    private MybatisUserService mybatisUserService ;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id)
    {
        return mybatisUserService.getUser(id);
    }
}
