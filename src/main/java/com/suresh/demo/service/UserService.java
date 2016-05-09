package com.suresh.demo.service;

import com.suresh.demo.domain.User;

public interface UserService {
	
	 public User save(User user);
	 public User findByUsername(String username);
	 

}
