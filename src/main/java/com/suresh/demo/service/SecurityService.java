package com.suresh.demo.service;

import com.suresh.demo.domain.User;


public interface SecurityService {

	 public String findLoggedInUsername();
	 public User login(String username, String password);
	 
}
