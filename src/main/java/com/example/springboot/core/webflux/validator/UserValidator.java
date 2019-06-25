package com.example.springboot.core.webflux.validator;


import com.example.springboot.core.webflux.pojo.WebFluxUser;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(WebFluxUser.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        WebFluxUser user = (WebFluxUser) o;
        if (StringUtils.isEmpty(user.getUserName())) {
            errors.reject("userName", null, "cant be null");
        }
    }
}
