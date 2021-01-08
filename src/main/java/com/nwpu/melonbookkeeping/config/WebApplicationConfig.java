package com.nwpu.melonbookkeeping.config;

import com.nwpu.melonbookkeeping.config.handler.TokenToUserHandlerMethodArgumentResolver;
import com.nwpu.melonbookkeeping.service.ConfigService;
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

@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {
    @Autowired
    TokenToUserHandlerMethodArgumentResolver tokenToUserHandlerMethodArgumentResolver;

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> handlerMethodArgumentResolverList) {
        handlerMethodArgumentResolverList.add(tokenToUserHandlerMethodArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }

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
