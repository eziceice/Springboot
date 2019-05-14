package com.example.springboot.core.rest.controller;

import com.example.springboot.core.mongodb.mongotemplate.service.UserService;
import com.example.springboot.core.rest.dto.UserDTO;
import com.example.springboot.core.rest.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController2 {

    @Autowired
    private UserTransformer userTransformer;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public UserDTO getUserWithXML(@PathVariable("id") Long id)
    {
        return userTransformer.transferToRestModel(userService.getUser(id));
    }

    public static UserDTO getUser(Long id)
    {
        RestTemplate restTemplate = new RestTemplate();
        // 第一个为URL，第二个为返回类型，第三个为参数 - GET方法
        UserDTO userDTO = restTemplate.getForObject("http://localhost:8080/user/{id}", UserDTO.class, id);
        return userDTO;
    }

    public static List<UserDTO> findUser(String userName, String note, int start, int limit)
    {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> params = new HashMap<>();
        params.put("userName", userName);
        params.put("note", note);
        params.put("start", start);
        params.put("limit", limit);
        String url = "http://localhost:8080/users/{userName}/{note}/{start}/{limit}";
        // 多参数GET
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class, params);
        List<UserDTO> results = responseEntity.getBody();
        return results;
    }

    // POST
    public static UserDTO insertUser(UserDTO userDTO)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<UserDTO> request = new HttpEntity<>(userDTO, headers);
        RestTemplate restTemplate = new RestTemplate();
        UserDTO userDTO1 = restTemplate.postForObject("http://localhost:8080/user", request, UserDTO.class);
        return userDTO1;
    }

    // DELETE
    public static void deleteUser(Long id)
    {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:8080/user/{id}", id);
    }

    public static UserDTO insertUserEntity(UserDTO userDTO)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<UserDTO> request = new HttpEntity<>(userDTO, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDTO> userEntity = restTemplate.postForEntity("http://localhost:8080/user2/entity", request, UserDTO.class);
        UserDTO userDTO1 = userEntity.getBody();
        HttpHeaders httpHeaders = userEntity.getHeaders();
        int status = userEntity.getStatusCodeValue();
        return userDTO1;
    }

    // use exchange
    public static UserDTO useExchange(UserDTO userDTO, Long id)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<UserDTO> request = new HttpEntity<>(userDTO, headers);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user2/entity";
        ResponseEntity<UserDTO> userDTOResponseEntity = restTemplate.exchange(url, HttpMethod.POST, request, UserDTO.class);
        UserDTO userDTO1 = userDTOResponseEntity.getBody();
        return userDTO1;
    }
}
