package com.blogproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf().disable()
		.authorizeHttpRequests(auth ->auth.requestMatchers(HttpMethod.GET,"/**").permitAll()
				.requestMatchers("/api/auth/**").permitAll()
				.anyRequest().authenticated());
		return http.build();
	}
	

//	@Bean
//	public UserDetailsService users() {
//		UserDetails user = User.builder()
//			.username("siri")
//			.password(encoder().encode("siri"))
//			.roles("USER")
//			.build();
//		UserDetails admin = User.builder()
//				.username("anu")
//				.password(encoder().encode("anusha"))
//				.roles("ADMIN")
//				.build();
//		return new InMemoryUserDetailsManager(user, admin);
//	}
//	
	
	
	
	
	
}
