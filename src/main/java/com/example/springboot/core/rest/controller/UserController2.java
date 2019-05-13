package com.example.springboot.core.rest.controller;

import com.example.springboot.core.mongodb.mongotemplate.pojo.User;
import com.example.springboot.core.mongodb.mongotemplate.service.UserService;
import com.example.springboot.core.rest.dto.UserDTO;
import com.example.springboot.core.rest.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController2 {

    @Autowired
    private UserTransformer userTransformer;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public UserDTO getUser(@PathVariable("id") Long id)
    {
        return userTransformer.transferToRestModel(userService.getUser(id));
    }

}
