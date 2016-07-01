package com.mitrais.scrummit.app;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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
@PropertySources({
    @PropertySource(value = "classpath:mail.properties", ignoreResourceNotFound = true),
    @PropertySource(value = "file:${catalina.home}/conf/mail.properties", ignoreResourceNotFound = true)
})

public class SpringWebConfig extends WebMvcConfigurerAdapter {
 
	@Value("${mail.protocol}")
    private String protocol;
    @Value("${mail.host}")
    private String host;
    @Value("${mail.port}")
    private int port;
    @Value("${mail.smtp.auth}")
    private boolean auth;
    @Value("${mail.smtp.starttls.enable}")
    private boolean starttls_enable;
    @Value("${mail.smtp.starttls.required}")
    private boolean starttls_required;
    @Value("${mail.from}")
    private String from;
    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;
    
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
	
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", auth);
        mailProperties.put("mail.smtp.starttls.enable", starttls_enable);
        mailProperties.put("mail.smtp.starttls.required", starttls_required);
        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setProtocol(protocol);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        return mailSender;
    }
}