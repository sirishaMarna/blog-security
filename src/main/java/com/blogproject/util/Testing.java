package com.blogproject.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Testing {
public static void main(String[] args) {
	BCryptPasswordEncoder p = new BCryptPasswordEncoder();
	System.out.println(p.encode("siri"));
	System.out.println(p.encode("kumari"));
}
}

