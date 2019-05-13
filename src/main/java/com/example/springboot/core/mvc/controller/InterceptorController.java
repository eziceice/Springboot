package com.example.springboot.core.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

//@Controller
//@RequestMapping("/interceptor")
public class InterceptorController {
    @GetMapping("/start")
    @ResponseBody
    public Map<String, String> start()
    {
        System.out.println("controller working");
        Map<String, String> result = new HashMap<>();
        result.put("1", "1");
        return result;
    }
}
