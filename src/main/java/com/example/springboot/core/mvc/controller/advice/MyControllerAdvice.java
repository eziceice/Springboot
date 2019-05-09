package com.example.springboot.core.mvc.controller.advice;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice(basePackages = {"com.example.springboot.core.mvc.controller.advice.test.*"}, annotations = Controller.class)
public class MyControllerAdvice {

    @InitBinder
    public void initDataBinder(WebDataBinder binder)
    {
        CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    @ModelAttribute
    public void projectModel(Model model)
    {
        model.addAttribute("project_name", "springboot-core");
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String exception(Model model, Exception ex)
    {
        model.addAttribute("exception_message", ex.getMessage());
        return "Error";
    }
}
