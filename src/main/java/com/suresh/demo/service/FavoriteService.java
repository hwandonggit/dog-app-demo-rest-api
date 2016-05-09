package com.suresh.demo.service;

import java.util.List;

import com.suresh.demo.domain.Favorite;
import com.suresh.demo.domain.Dog;
import com.suresh.demo.domain.User;

public interface FavoriteService {

	//save
	public Favorite addFavorite(User user , Dog dog);
	public Favorite findByUserIdDogId(User user , Dog dog);
	 //delete
     public String unFavorite(User user , Dog dog) throws Exception ;
     public List<Long> getAll();
}
