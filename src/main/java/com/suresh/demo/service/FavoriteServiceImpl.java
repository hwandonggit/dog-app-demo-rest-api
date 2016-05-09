package com.suresh.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suresh.demo.dao.jpa.FavoriteRepository;
import com.suresh.demo.domain.Favorite;
import com.suresh.demo.domain.Dog;
import com.suresh.demo.domain.User;

@Service
public class FavoriteServiceImpl implements FavoriteService{

	@Autowired
	FavoriteRepository favoriteRepository;
	@Autowired 
	DogServiceImpl dogService;
	
	@Override
	public Favorite addFavorite(User user, Dog dog) {
		Dog favoriteDog = dogService.getDog(dog.getId());
		Favorite favorite = findByUserIdDogId(user, favoriteDog);
		if(null == favorite){
			Favorite newFavorite = favoriteRepository.save(new Favorite(user.getId(),dog.getId())); 		
			return newFavorite;
		}else
		
		return null;
	}

	@Override
	public Favorite findByUserIdDogId(User user, Dog dog) {
		
		Favorite favorite = favoriteRepository.findByUserIdAndDogId(user.getId(), dog.getId());
		
		return favorite;
		
	}

	@SuppressWarnings("finally")
	@Override
	public String unFavorite(User user, Dog dog) throws Exception {
		Favorite favorite = findByUserIdDogId(user, dog);
		if(null != favorite){
			try{
				favoriteRepository.delete(favorite); 
			}catch(Exception e){throw new Exception(e.getMessage().toString());}
			finally{
				return "";
			}
		}else
			return null;
	}

	@Override
	public List<Long> getAll() {
		Iterable<Favorite> allFavorites = favoriteRepository.findAll();
		if(null != allFavorites){
				Stream<Map.Entry<Long, Long>> sorted =
						StreamSupport.stream(allFavorites.spliterator(), false).
						collect(Collectors.groupingBy(Favorite::getDogId,Collectors.counting())).entrySet().stream()
					       .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
                List <Long> sortedList = new ArrayList<>();
				sorted.forEach(entry -> sortedList.add(entry.getKey()));
				return sortedList;
		}
		return null;
	}

}
