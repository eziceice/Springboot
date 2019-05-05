package com.example.springboot.core.mvc.controller;

import com.example.springboot.core.mongodb.mongotemplate.pojo.User;
import com.example.springboot.core.mongodb.mongotemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/details")
    public ModelAndView details(Long id)
    {
        User user = userService.getUser(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/details");
        mv.addObject("user", user);
        return mv;
    }
}
