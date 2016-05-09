package com.suresh.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.suresh.demo.domain.User;

@Service
public class SecurityServiceImpl implements SecurityService{
 
    @Autowired
    UserService userService;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }

        return null;
    }

    @Override
    public User login(String username, String password) {
        User userDetails = userService.findByUsername(username);
       
        if(null != userDetails){
	        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
	        							= new UsernamePasswordAuthenticationToken(userDetails, passwordEncoder().encode(password),null);
           boolean matched = passwordEncoder().matches(password, userDetails.getPassword());
	
	       if (matched && usernamePasswordAuthenticationToken.isAuthenticated()) {
	          Object principal = usernamePasswordAuthenticationToken.getPrincipal();
	            userDetails = new User();
	            userDetails.setId(((User)principal).getId());
	            userDetails.setUsername(((User)principal).getUsername());
	            return userDetails;
	        }
	    }
        return null;
    }
    
  
}
