package com.example.springboot.core.mvc.controller.advice.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/advice")
public class AdviceController {

    @GetMapping("test")
    public String test(@RequestParam Date date, ModelMap modelMap) {
        System.out.println(modelMap.get("project_name"));
        System.out.println(date.toString());
        throw new RuntimeException("Exception!");
    }
}
