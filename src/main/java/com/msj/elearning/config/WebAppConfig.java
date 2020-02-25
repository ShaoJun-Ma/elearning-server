package com.msj.elearning.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    //配置静态资源
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //服务器请求资源的路径
        registry.addResourceHandler("/**")
                //文件的真实路径
                .addResourceLocations("file:D:\\elearning\\");
    }
}
