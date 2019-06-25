package com.example.springboot.core.webflux.handler;

import com.example.springboot.core.webflux.pojo.WebFluxUser;
import com.example.springboot.core.webflux.repository.UserRepository;
import com.example.springboot.core.webflux.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class UserHandler {

    @Autowired
    private UserRepository userRepository;

    private UserVo translate(WebFluxUser webFluxUser)
    {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(webFluxUser, userVo);
        userVo.setSexCode(webFluxUser.getSexEnum().getId());
        userVo.setSexName(webFluxUser.getSexEnum().getName());
        return userVo;
    }

    public Mono<ServerResponse> getUser(ServerRequest request)
    {
        String id = request.pathVariable("id");
        Mono<UserVo> userVoMono = userRepository.findById(Long.valueOf(id))
                .map(this::translate);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(userVoMono, UserVo.class);
    }

    public Mono<ServerResponse> updateUserName(ServerRequest request)
    {
        String id = request.headers().header("id").get(0);
        String userName = request.headers().header("userName").get(0);
        Mono<WebFluxUser> userMono = userRepository.findById(Long.valueOf(id));
        WebFluxUser user = userMono.block();
        user.setUserName(userName);
        Mono<UserVo> result = userRepository.save(user).map(u -> translate(u));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(result, UserVo.class);
    }
}
