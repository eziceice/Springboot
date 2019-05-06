package com.example.springboot.core.mvc.controller;

import com.example.springboot.core.mongodb.mongotemplate.pojo.User;
import com.example.springboot.core.mongodb.mongotemplate.service.UserService;
import com.example.springboot.core.mvc.utils.DoubleEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Double.class, new DoubleEditor());
    }

    @RequestMapping("/details")
    public ModelAndView details(Long id)
    {
        User user = userService.getUser(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/details");
        mv.addObject("user", user);
        return mv;
    }

    @RequestMapping("/table")
    public ModelAndView table()
    {
        List<User> userList = userService.findUserByUsername("yutian");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/table");
        mv.addObject("userList", userList);
        return mv;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<User> list(@RequestParam(value = "userName", required = false) String userName,
                           @RequestParam(value = "note", required = false) String note)
    {
        List<User> userList = userService.findUserByUsername(userName);
        return userList;
    }

    @PostMapping("/format")
    @ResponseBody
    public Map<String, Object> format(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, Double number)
    {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("date", date);
        dataMap.put("number", number);
        return dataMap;
    }
}
