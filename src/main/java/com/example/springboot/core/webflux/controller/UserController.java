package com.example.springboot.core.webflux.controller;

import com.example.springboot.core.webflux.pojo.WebFluxUser;
import com.example.springboot.core.webflux.service.UserService;
import com.example.springboot.core.webflux.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Mono<UserVo> getUser(@PathVariable long id)
    {
        return userService.getUser(id).map(this::translate);
    }

    @PostMapping
    public Mono<UserVo> insertUser(@RequestBody WebFluxUser webFluxUser)
    {
        return userService.insertUser(webFluxUser).map(this::translate);
    }

    @PutMapping
    public Mono<UserVo> updateUser(@RequestBody WebFluxUser webFluxUser)
    {
        return userService.updateUser(webFluxUser).map(this::translate);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable long id)
    {
        return userService.deleteUser(id);
    }

    @GetMapping
    public Flux<UserVo> findUsers(@RequestParam String userName, @RequestParam String note)
    {
        return userService.findUsers(userName, note).map(this::translate);
    }

    private UserVo translate(WebFluxUser webFluxUser)
    {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(webFluxUser, userVo);
        userVo.setSexCode(webFluxUser.getSexEnum().getId());
        userVo.setSexName(webFluxUser.getSexEnum().getName());
        return userVo;
    }

    @PostMapping("/user2/{user}")
    public Mono<UserVo> insertUser2(@PathVariable("user") WebFluxUser webFluxUser)
    {
        return userService.insertUser(webFluxUser).map(this::translate);
    }
}
