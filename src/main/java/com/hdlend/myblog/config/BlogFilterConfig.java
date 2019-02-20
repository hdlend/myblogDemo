package com.hdlend.myblog.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hdlend.myblog.filter.BlogFilter;

@Configuration
public class BlogFilterConfig {
	
		@Bean
	    public FilterRegistrationBean<BlogFilter> BlogFilterRegistration() {
	        //新建过滤器注册类
	        FilterRegistrationBean<BlogFilter> registration = new FilterRegistrationBean<BlogFilter>();
	        // 添加我们写好的过滤器
	        registration.setFilter( new BlogFilter());
	        // 设置过滤器的URL模式
	        registration.addUrlPatterns("/blog/*");
	        //设置过滤器顺序 值越小 优先级越高
	        registration.setOrder(1);
	        return registration;
	    }
		

}
