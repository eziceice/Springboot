package com.example.springboot.core.config;

import com.example.springboot.core.enumeration.SexEnum;
import com.example.springboot.core.webflux.pojo.WebFluxUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebFluxConfig implements WebFluxConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry)
    {
        registry.addConverter(stringToUserConverter());
    }

    @Bean
    public Converter<String, WebFluxUser> stringToUserConverter()
    {
        return s -> {
            String[] str = s.split("-");
            WebFluxUser user = new WebFluxUser();
            long id = Long.parseLong(str[0]);
            user.setId(id);
            user.setUserName(str[1]);
            int sexCode = Integer.valueOf(str[2]);
            SexEnum sexEnum = SexEnum.getEnumById(sexCode);
            user.setSexEnum(sexEnum);
            user.setNote(str[3]);
            return user;
        };
    }
}
