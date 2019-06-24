package com.example.springboot.core.webflux.client;

import com.example.springboot.core.webflux.pojo.WebFluxUser;
import com.example.springboot.core.webflux.vo.UserVo;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Example class shows how to use WebClient in WebFlux
 */
public class WebClientExample {

    private static void insertUser(WebClient webClient, WebFluxUser user) {
        Mono<UserVo> userVoMono = webClient.post()
                .uri("/users").contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(Mono.just(user), WebFluxUser.class)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve().bodyToMono(UserVo.class);
        UserVo userVo = userVoMono.block(); // Request will be triggered when block method is invoked
        System.out.println(userVo.getUserName());
    }

    private static void getUser(WebClient client, Long id) {
        Mono<UserVo> userVoMono = client.get()
                .uri("/users/{id}", id)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToMono(UserVo.class);
        UserVo userVo = userVoMono.block(); // Request will be triggered when block method is invoked
        System.out.println(userVo.getUserName());
    }

    private static void updateUser(WebClient client, WebFluxUser user) {
        Mono<UserVo> userVoMono = client.put().uri("/users")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(Mono.just(user), WebFluxUser.class)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToMono(UserVo.class);

        UserVo userVo = userVoMono.block(); // Request will be triggered when block method is invoked
        System.out.println(userVo.getUserName());
    }

    private static void findUsers(WebClient client, String userName, String note) {
        Flux<UserVo> userVoFlux = client.get().uri(uriBuilder ->
                uriBuilder.path("/users").queryParam("userName", userName)
                        .queryParam("note", note).build())
                .retrieve().bodyToFlux(UserVo.class);
        // Request will be triggered when toIterable().iterator() is invoked
        for (UserVo item : userVoFlux.toIterable()) {
            // 注意这里是采用下拉式向服务器获取信息，每执行一次循环的时候向服务器获取一次信息并输出 - BACK PRESSURE
            // 直到获取所有的数据流序列方法才会结束
            System.out.println(item.getUserName());
        }
    }

    private static void deleteUser(WebClient client, Long id) {
        Mono<Void> result = client.delete()
                .uri("/users/{id}", id)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToMono(Void.class);
        Void voidResult = result.block();// Request will be triggered when block method is invoked
        System.out.println(voidResult);
    }
}
