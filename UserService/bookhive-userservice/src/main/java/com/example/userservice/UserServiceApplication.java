package com.example.userservice;

import com.example.userservice.filters.JsonFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.GlobalFilter;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public RouteLocator getRouteLocator(RouteLocatorBuilder builder) {

        return builder.routes()
                .route((r)->{return r.path("/orders/**").uri("lb://order-service");})
                .route((r)->{return r.path("/cart/**").uri("lb://order-service");})
                .route((r)->{return r.path("/bookorders/**").uri("lb://order-service");})
                .route((r)->{return r.path("/inventory/**").uri("lb://order-service");})
                .route((r)->{return r.path("/books/**").uri("lb://product-service");})
                .route((r)->{return r.path("/user/**").uri("lb://product-service");})
                .route((r)->{return r.path("/users/**").uri("lb://order-service");})
                .build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {

        System.out.println("cors invoked");

        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST","PUT","DELETE"));
        corsConfig.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }

    @Bean
    public GlobalFilter getGlobalFilter() {
        return new JsonFilter();
    }

}
