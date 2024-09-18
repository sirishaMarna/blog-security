package com.blogproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogproject.dto.LoginDto;
import com.blogproject.dto.RegisterDto;
import com.blogproject.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	 private AuthService authService;

	    public AuthController(AuthService authService) {
	        this.authService = authService;
	    }
	    
	    @PostMapping
	    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
	        String response = authService.register(registerDto);
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
	    }
	    
	    @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
	        String response = authService.login(loginDto);
	        return ResponseEntity.ok(response);
	    }
}
