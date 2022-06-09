package com.example.userservice.filters;


import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class JsonFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // TODO Auto-generated method stub

        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        System.out.println(authHeader);

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {

            String errorMessage = "Missing Authorization Header or Authorization Header contains invalid token";

            DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
            DataBuffer buffer = bufferFactory.wrap(errorMessage.getBytes());
            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
            return exchange.getResponse().writeWith(Flux.just(buffer));

        }

        String token = authHeader.split(" ")[1];
        try {
            Jwts.parser()
                    .setSigningKey("stackroute")
                    .parseClaimsJws(token)
                    .getBody();
        }catch(Exception e) {
            String errorMessage = "Token Verification Failed";

            DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
            DataBuffer buffer = bufferFactory.wrap(errorMessage.getBytes());
            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
            return exchange.getResponse().writeWith(Flux.just(buffer));
        }

        return chain.filter(exchange);
    }

}