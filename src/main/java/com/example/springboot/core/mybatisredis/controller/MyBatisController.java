//package com.example.springboot.core.controller;
//
//import com.example.springboot.core.mybatisredis.pojo.User;
//import com.example.springboot.core.service.MybatisUserService;
//import com.example.springboot.core.service.UserBatchService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/mybatis")
//public class MyBatisController {
//
//    @Autowired
//    private MybatisUserService mybatisUserService ;
//
//    @Autowired
//    private UserBatchService userBatchService;
//
//    @RequestMapping("/getUser")
//    @ResponseBody
//    public User getUser(Long id)
//    {
//        return mybatisUserService.getUser(id);
//    }
//
//    @RequestMapping("/insertUser")
//    @ResponseBody
//    public Map<String, Object> insertUser(String userName, String note)
//    {
//        User user = new User();
//        user.setUserName(userName);
//        user.setNote(note);
//        int update = mybatisUserService.insertUser(user);
//        Map<String, Object> result = new HashMap<>();
//        result.put("success", update==1);
//        result.put("user", user);
//        return result;
//    }
//
//    @RequestMapping("/findUsers")
//    @ResponseBody
//    public List<User> findUsers(String userName, String note)
//    {
//        return mybatisUserService.findUsers(userName, note);
//    }
//
//    @RequestMapping("/updateUserName")
//    @ResponseBody
//    public Map<String, Object> updateUserName(Long id, String userName)
//    {
//        User user = mybatisUserService.updateUserName(id, userName);
//        boolean flag = user != null;
//        String message = flag ? "Update successfully": "Update failure";
//        return resultMap(flag, message);
//    }
//
//    @RequestMapping("/deleteUser")
//    @ResponseBody
//    public Map<String, Object> deleteUser(Long id)
//    {
//        int result = mybatisUserService.deleteUser(id);
//        boolean flag = result == 1;
//        String message = flag ? "Delete successfully" : "Delete failure";
//        return resultMap(flag, message);
//    }
//
//    @RequestMapping("/insertUsers")
//    @ResponseBody
//    public Map<String, Object> insertUsers(String userName1, String note1, String userName2, String note2)
//    {
//        User user1 = new User();
//        user1.setUserName(userName1);
//        user1.setNote(note1);
//        User user2 = new User();
//        user2.setUserName(userName2);
//        user2.setNote(note2);
//        List<User> userList = new ArrayList<>();
//        userList.add(user1);
//        userList.add(user2);
//        int inserts = userBatchService.insertUsers(userList);
//        Map<String, Object> result = new HashMap<>();
//        result.put("success", inserts!=0);
//        result.put("user", userList);
//        return result;
//    }
//
//
//    private Map<String, Object> resultMap(boolean success, String message)
//    {
//        Map<String, Object> result = new HashMap<>();
//        result.put("success", success);
//        result.put("message", message);
//        return result;
//    }
//}
