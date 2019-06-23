package com.example.springboot.core.webflux.service;

import com.example.springboot.core.webflux.pojo.WebFluxUser;
import com.example.springboot.core.webflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<WebFluxUser> getUser(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<WebFluxUser> insertUser(WebFluxUser webFluxUser) {
        return userRepository.save(webFluxUser);
    }

    @Override
    public Mono<WebFluxUser> updateUser(WebFluxUser webFluxUser) {
        return userRepository.save(webFluxUser);
    }

    @Override
    public Mono<Void> deleteUser(long id) {
        return userRepository.deleteById(id);
    }

    @Override
    public Flux<WebFluxUser> findUsers(String userName, String note) {
        return userRepository.findByUserNameLikeAndNoteLike(userName, note);
    }
}
