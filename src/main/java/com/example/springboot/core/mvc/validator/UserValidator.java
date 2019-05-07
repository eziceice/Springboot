package com.example.springboot.core.mvc.validator;


import com.example.springboot.core.mongodb.mongotemplate.pojo.User;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(User.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (o == null)
        {
            errors.rejectValue("", null, "User can't be empty");
            return;
        }

        User user = (User) o;
        if (StringUtils.isEmpty(user.getUserName()))
        {
            errors.rejectValue("userName", null, "Username can't be empty");
        }
    }
}
