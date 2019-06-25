package com.example.springboot.core.config;

import com.example.springboot.core.webflux.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Autowired
    private UserHandler userHandler;

    @Bean
    public RouterFunction<ServerResponse> userRouter()
    {
        RouterFunction<ServerResponse> router =
                route(GET("/router/users/{id}").and(accept(APPLICATION_STREAM_JSON)),userHandler::getUser)
                .andRoute(POST("/router/users").and((contentType(APPLICATION_STREAM_JSON)).and(accept(APPLICATION_STREAM_JSON)))
                , userHandler::updateUserName);
        return router;
    }

    @Bean
    public RouterFunction<ServerResponse> securityRouter()
    {
        RouterFunction<ServerResponse> router = route(
                GET("/security/users/{id}").and(accept(APPLICATION_STREAM_JSON))
                ,userHandler::getUser).filter(this::filterLogic);
        return router;
    }

    private Mono<ServerResponse> filterLogic(ServerRequest request, HandlerFunction<ServerResponse> next)
    {
        String userName = request.headers().header("username").get(0);
        String password = request.headers().header("password").get(0);

        if (userName != null && password != null && !userName.equals(password))
        {
            return next.handle(request);
        }
        return ServerResponse.status(HttpStatus.UNAUTHORIZED).build();
    }
}
