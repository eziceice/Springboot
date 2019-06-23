package com.example.springboot.core.webflux.repository;

import com.example.springboot.core.webflux.pojo.WebFluxUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveMongoRepository<WebFluxUser, Long> {

    Flux<WebFluxUser> findByUserNameLikeAndNoteLike(String userName, String note);
}
