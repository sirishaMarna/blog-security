package com.blogproject;

import java.time.Duration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
		System.out.println("Welcome to blog application");
	}
	
	 @Bean
	    public ModelMapper getModelMapper() {
	        return new ModelMapper();
	    }


	    @Bean
	    public RestTemplate restTemplate(RestTemplateBuilder builder) {
	     
	    	return builder
	    		.setConnectTimeout(Duration.ofMillis(3000))
	    		.setReadTimeout(Duration.ofMillis(3000))
	    		.build();
	    }

}
