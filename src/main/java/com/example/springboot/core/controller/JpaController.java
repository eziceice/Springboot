//package com.example.springboot.aop.controller;
//
//import com.example.springboot.aop.dao.JpaUserRepository;
//import UserJpa;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//
////@Controller
////@RequestMapping("/jpa")
//public class JpaController {
//
////    @Autowired
//    private JpaUserRepository jpaUserRepository;
//
//    @RequestMapping("/getUser")
//    @ResponseBody
//    public UserJpa getUserJpa(Long id) {
//        return jpaUserRepository.findById(id).get();
//    }
//
//    @RequestMapping("/getUserById")
//    @ResponseBody
//    public UserJpa getUserById(Long id) {
//        UserJpa user = jpaUserRepository.getUserJpaById(id);
//        return user;
//    }
//
//    @RequestMapping("/findByUserNameLike")
//    @ResponseBody
//    public List<UserJpa> findByUserNameLike(String userName) {
//        List<UserJpa> userJpaList = jpaUserRepository.findByUsernameLike("%" + userName + "%");
//        return userJpaList;
//    }
//
//
//    @RequestMapping("/findByUserNameLikeOrNoteLike")
//    @ResponseBody
//    public List<UserJpa> findByUserNameLikeOrNoteLike(String userName, String note)
//    {
//        String userNameLike = "%" + userName + "%";
//        String noteLike = "%" + note + "%";
//        List<UserJpa> userJpaList = jpaUserRepository.findByUsernamelikeOrNoteLike(userNameLike, noteLike);
//        return userJpaList;
//    }
//}
