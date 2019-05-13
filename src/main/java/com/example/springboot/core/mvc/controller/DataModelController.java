package com.example.springboot.core.mvc.controller;

import com.example.springboot.core.mongodb.mongotemplate.pojo.User;
import com.example.springboot.core.mongodb.mongotemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@Controller
//@RequestMapping("/data")
public class DataModelController {
    @Autowired
    private UserService userService;

    @GetMapping("/model")
    public String useModel(Long id, Model model)
    {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "data/user";
    }


    @GetMapping("/modelMap")
    public ModelAndView useModelMap(Long id, ModelMap modelMap)
    {
        User user = userService.getUser(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("data/user");
        modelMap.put("user", user);
        return mv;
    }

    @GetMapping("mav")
    public ModelAndView useModelAndView(Long id, ModelAndView mv)
    {
        User user = userService.getUser(id);
        mv.addObject("user", user);
        mv.setViewName("data/user");
        return mv;
    }

}
