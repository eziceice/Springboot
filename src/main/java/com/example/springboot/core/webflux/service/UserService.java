package com.example.springboot.core.webflux.service;

import com.example.springboot.core.webflux.pojo.WebFluxUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<WebFluxUser> getUser(long id);

    Mono<WebFluxUser> insertUser(WebFluxUser webFluxUser);

    Mono<WebFluxUser> updateUser(WebFluxUser webFluxUser);

    Mono<Void> deleteUser(long id);

    Flux<WebFluxUser> findUsers(String userName, String note);
}
