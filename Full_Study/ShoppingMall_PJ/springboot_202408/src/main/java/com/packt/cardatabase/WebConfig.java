package com.packt.cardatabase;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /thumbnails/로 시작하는 모든 요청을 /uploads/thumbnails/ 디렉토리에서 찾도록 설정
        registry.addResourceHandler("/thumbnails/**")
                .addResourceLocations("file:D:/uploads/thumbnails/");
    }
}
