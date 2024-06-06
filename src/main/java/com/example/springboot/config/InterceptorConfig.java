package com.example.springboot.config;
//yzy
import com.example.springboot.config.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(jwtInterceptor())
//                .addPathPatterns("/**")  // 拦截所有请求，通过判断token是否合法来决定是否需要登录
//                .excludePathPatterns("/user/login/**")
//                .excludePathPatterns("/user/register/**")
//                .excludePathPatterns("/**/export/**")
//                .excludePathPatterns("/**/import/**")
//                .excludePathPatterns("/file/**")
//                .excludePathPatterns("/swagger-ui/html/**")
//                .excludePathPatterns("/swagger-resources/**")
//                .excludePathPatterns("/webjars/**")
//                .excludePathPatterns("/v2/**")
//                .excludePathPatterns("/school_zju")
//                .excludePathPatterns("/total_situation")
//                .excludePathPatterns("/wander_time/wanderTime")
//                .excludePathPatterns("/area/getAxis");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("swagger-ui/html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }

}
