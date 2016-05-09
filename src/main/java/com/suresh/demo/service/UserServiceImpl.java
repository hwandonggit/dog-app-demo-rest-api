package com.suresh.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.suresh.demo.dao.jpa.UserRepository;
import com.suresh.demo.domain.User;

@Service
public class UserServiceImpl  implements UserService{
	
	@Autowired
    UserRepository userRepository;
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public User save(User user) {
    	User duplicateUser = findByUsername(user.getUsername());
	    	if(null == duplicateUser){
	        user.setPassword(passwordEncoder().encode(user.getPassword()));
	        User newUser = userRepository.save(user);
	        newUser.setPassword(null);
	        return newUser;
    	}
	    return null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
