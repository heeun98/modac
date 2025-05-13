package com.project.modac.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
       /* registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowCredentials(true)
                .allowedHeaders("Authorization", "Content-Type") // 허용할 헤더 추가
                .exposedHeaders("Authorization");// Authorization 헤더를 클라이언트가 받을 수 있도록 설정
*/

    }
}