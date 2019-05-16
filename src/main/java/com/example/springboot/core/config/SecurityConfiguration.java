package com.example.springboot.core.config;

import com.example.springboot.core.security.service.MongoUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> userConfig = auth.inMemoryAuthentication().passwordEncoder(passwordEncoder);
//        userConfig.withUser("admin").password("$2a$10$7NH6n4K/Zz9K1W9fQ2gkY.tO6IJapAENbAO5WJsulD0ydyIC1yPri").authorities("ROLE_USER", "ROLE_ADMIN");
//        userConfig.withUser("user").password("$2a$10$7NH6n4K/Zz9K1W9fQ2gkY.tO6IJapAENbAO5WJsulD0ydyIC1yPri").roles("USER");
//    }

    @Autowired
    private MongoUserDetailService mongoUserDetailService;

    @Value("${system.user.password.secret}")
    private String secret;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(mongoUserDetailService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/user/byName").hasAnyRole("USER", "ADMIN")
                .antMatchers("/user/addUser").access("hasAuthority('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and().anonymous()
                .and().formLogin()
                .and().httpBasic();

        // 强制使用HTTPS和使用HTTP
//        http.requiresChannel().antMatchers("/admin/**").requiresSecure()
//                .and().requiresChannel().antMatchers("/user/**").requiresInsecure();
    }
}
