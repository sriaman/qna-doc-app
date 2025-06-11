package com.jktech.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor authInterceptor() {
        return requestTemplate -> {
            var auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getCredentials() instanceof String token) {
                System.out.println("Feign Client Auth Header: "+auth.getCredentials().toString());
                requestTemplate.header("Authorization", "Bearer " + token);
            }
        };
    }
}

