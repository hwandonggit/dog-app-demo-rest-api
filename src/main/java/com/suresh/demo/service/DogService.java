package com.suresh.demo.service;

import java.util.List;
import java.util.Map;

import com.suresh.demo.domain.Dog;

public interface DogService {
	
	 public Dog createDog(Dog dog);
	 public Dog getDog(long id);
	 public void updateDog(Dog dog);
	 public void deleteDog(Long id);
	 public List <Map.Entry<String,List<Dog>>> getAllDogs();
	 public List <Map.Entry<String,List<Dog>>> getAllDogsByBreed(String breed);
	 
}
