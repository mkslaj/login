package com.example.demo.config;

import com.example.demo.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final List<String> BYPASS_PATHS = List.of(
            "/user/login",
            "/user/register");

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        // 配置拦截器，排除登录和注册路径
        registry.addInterceptor(loginInterceptor).excludePathPatterns(BYPASS_PATHS);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许前端应用的跨域请求
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // 允许来自前端服务器的请求
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的HTTP方法
                .allowedHeaders("*") // 允许所有头部
                .allowCredentials(true); // 允许携带凭证，如Cookies
    }
}