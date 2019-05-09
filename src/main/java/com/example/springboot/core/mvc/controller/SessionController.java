package com.example.springboot.core.mvc.controller;

import com.example.springboot.core.mongodb.mongotemplate.pojo.User;
import com.example.springboot.core.mongodb.mongotemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes(names = {"user"}, types = User.class)
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    @ResponseBody
    public String test(@SessionAttribute("id") Long id)
    {
        User user = userService.getUser(id);
        return user.toString();
    }
}
