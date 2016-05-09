package com.suresh.demo.api.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.suresh.demo.domain.Favorite;
import com.suresh.demo.domain.Dog;
import com.suresh.demo.domain.User;
import com.suresh.demo.service.FavoriteService;
import com.wordnik.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/example/v1/favorites")
@Api(value = "favorites", description = "Favoriting API")
public class FavorieController extends AbstractRestHandler{

	@Autowired
	FavoriteService favoriteService;
	
	@RequestMapping(value = "/dogs/{dogId}",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseBody
    public Favorite makeFavorite(@PathVariable("dogId") String dogId, 
    							 @RequestBody User user,
							     HttpServletRequest request, 
						    	HttpServletResponse response) {
		
		Dog dog = new Dog();
		dog.setId(Long.valueOf(dogId));
		Favorite newlyFavorited = this.favoriteService.addFavorite(user, dog);
        
        if(null == newlyFavorited)
        	response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
        else
        	response.setStatus(HttpServletResponse.SC_CREATED);
        return newlyFavorited;
    }
	
	
	@RequestMapping(value = "/dogs/{dogId}",
            method = RequestMethod.DELETE,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseBody
    public void unFavorite(@PathVariable("dogId") String dogId, 
    							@RequestBody User user,
							     HttpServletRequest request, 
						    	HttpServletResponse response) {
	
		Dog dog = new Dog();
		dog.setId(Long.valueOf(dogId));
		String unFavorited = null;
		try {
			unFavorited = this.favoriteService.unFavorite(user, dog);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        if(null == unFavorited)
        	response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        else
        	response.setStatus(HttpServletResponse.SC_OK);
       
    }
}
