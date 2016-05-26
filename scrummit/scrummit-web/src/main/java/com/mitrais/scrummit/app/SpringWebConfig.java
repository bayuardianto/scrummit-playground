package com.mitrais.scrummit.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.mitrais.scrummit.interceptor.AuthenInterceptor;

@EnableWebMvc //<mvc:annotation-driven />
@Configuration
@ComponentScan(basePackages = "com.mitrais.scrummit")
public class SpringWebConfig extends WebMvcConfigurerAdapter {
 
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
                        .addResourceLocations("/resources/");
	}
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver 
                         = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
	   RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
	   mapping.setInterceptors(new Object[] {getAuthenInterceptor()});
	   return mapping;
	}
	
	@Bean
	public AuthenInterceptor getAuthenInterceptor() {
		return new AuthenInterceptor();
	}
}