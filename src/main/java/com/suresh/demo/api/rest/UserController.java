package com.suresh.demo.api.rest;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.suresh.demo.domain.User;
import com.suresh.demo.service.SecurityService;
import com.suresh.demo.service.UserService;
import com.wordnik.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/example/v1/users")
@Api(value = "users", description = "User Registration API")
public class UserController extends AbstractRestHandler{
    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseBody
    public User registration(@RequestBody User user,
							    		HttpServletRequest request, 
							    		HttpServletResponse response) {
        
    	User createdUser = this.userService.save(user);
        if(null == createdUser)
        	response.setStatus(HttpServletResponse.SC_CONFLICT);
        else
        	response.setStatus(HttpServletResponse.SC_CREATED);
        return createdUser;
    }

    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseBody
    public User login(@RequestBody User user,
						    		HttpServletRequest request, 
						    		HttpServletResponse response) {
    	
    		User authenticatedUser = this.securityService.login(user.getUsername(), user.getPassword());
    	    if(null == authenticatedUser)
    	    	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    	    else
            	response.setStatus(HttpServletResponse.SC_OK);
           
    	    return authenticatedUser;
    		
    }

   
}