package com.suresh.demo.dao.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.suresh.demo.domain.Favorite;
import com.suresh.demo.domain.Dog;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Long>{
	
	Favorite findByUserIdAndDogId (Long userId, Long dogId);
	
}
