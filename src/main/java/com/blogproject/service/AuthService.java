package com.blogproject.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogproject.dto.LoginDto;
import com.blogproject.dto.RegisterDto;
import com.blogproject.entity.Role;
import com.blogproject.entity.User;
import com.blogproject.exceptions.BlogApiExceptions;
import com.blogproject.repository.RoleRepository;
import com.blogproject.repository.UserRepository;

@Service
public class AuthService {
	
	    private AuthenticationManager authenticationManager;
	    private UserRepository userRepository;
	    private RoleRepository roleRepository;
	    private PasswordEncoder passwordEncoder;
	    
	    
		    public AuthService(AuthenticationManager authenticationManager,
	                UserRepository userRepository,
	                RoleRepository roleRepository,
	                PasswordEncoder passwordEncoder) {
	this.authenticationManager = authenticationManager;
	this.userRepository = userRepository;
	this.roleRepository = roleRepository;
	this.passwordEncoder = passwordEncoder;
	}
 

	   
	    public String register(RegisterDto registerDto) {

	        if(userRepository.existsByUsername(registerDto.getUsername())){
	            throw new BlogApiExceptions(HttpStatus.BAD_REQUEST, "Username is already exists!.");
	        }
	        if(userRepository.existsByEmail(registerDto.getEmail())){
	            throw new BlogApiExceptions(HttpStatus.BAD_REQUEST, "Email is already exists!.");
	        }
	        User user = new User();
	        user.setName(registerDto.getName());
	        user.setUsername(registerDto.getUsername());
	        user.setEmail(registerDto.getEmail());
	        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

	        Set<Role> roles = new HashSet<>();
	        Role userRole = roleRepository.findByName("ROLE_USER").get();
	        roles.add(userRole);
	        user.setRoles(roles);

	        userRepository.save(user);

	        return "User registered successfully!.";
	    }    
	    public String login(LoginDto loginDto) {

	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
	                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        return "User Logged-in successfully!.";
	    }
	    
	    
}
