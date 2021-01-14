package com.nwpu.melonbookkeeping.config;

import com.nwpu.melonbookkeeping.config.handler.TokenToUserHandlerMethodArgumentResolver;
import com.nwpu.melonbookkeeping.service.ConfigService;
import com.nwpu.melonbookkeeping.util.AdminHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WEB配置器
 */
@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {
    @Autowired
    TokenToUserHandlerMethodArgumentResolver tokenToUserHandlerMethodArgumentResolver;
    @Autowired
    AdminHandlerInterceptor adminHandlerInterceptor;

    /**
     * 配置token转user的注解
     */
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> handlerMethodArgumentResolverList) {
        handlerMethodArgumentResolverList.add(tokenToUserHandlerMethodArgumentResolver);
    }

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminHandlerInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/login");
    }

    /**
     * 配置静态资源目录
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 配置全局静态变量
     */
    @Autowired
    @Qualifier("thymeleafViewResolver")
    private ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    private ConfigService configService;

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        if (thymeleafViewResolver != null) {
            thymeleafViewResolver.setStaticVariables(configService.getWebConfig());
        }
    }
}
