package com.example.springboot.core.controller;

import com.example.springboot.core.pojo.UserJDBCTemplate;
import com.example.springboot.core.service.JdbcTemplateUserService;
import com.example.springboot.core.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller
//@RequestMapping("/user")
public class UserController {

//    @Autowired
    private UserService userService;

//    @Autowired
    private JdbcTemplateUserService jdbcTemplateUserService;

    @RequestMapping("/print")
    @ResponseBody
    public UserJDBCTemplate printUser(Long id, String userName, String note) {
        UserJDBCTemplate userJDBCTemplate = new UserJDBCTemplate();
        userJDBCTemplate.setId(id);
        userJDBCTemplate.setUserName(userName);
        userJDBCTemplate.setNote(note);
        userService.printUser(userJDBCTemplate);
        return userJDBCTemplate;
    }

    @RequestMapping("get")
    @ResponseBody
    public UserJDBCTemplate getUser(Long id)
    {
        return jdbcTemplateUserService.getUser(id);
    }
}
