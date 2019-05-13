package com.example.springboot.core.rest.transformer;

import com.example.springboot.core.mongodb.mongotemplate.pojo.User;
import com.example.springboot.core.rest.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserTransformer {

    public UserDTO transferToRestModel(User user)
    {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
}
