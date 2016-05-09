package com.suresh.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Service;

import com.suresh.demo.dao.jpa.DogRepository;
import com.suresh.demo.domain.Dog;


@Service
public class DogServiceImpl implements DogService{

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    FavoriteService favoriteService;

    public DogServiceImpl() {
    }

    @Override
    public Dog createDog(Dog dog) {
        return dogRepository.save(dog);
    }

    @Override
    public Dog getDog(long id) {
        return dogRepository.findOne(id);
    }

    @Override
    public void updateDog(Dog dog) {
        dogRepository.save(dog);
    }

    @Override
    public void deleteDog(Long id) {
        dogRepository.delete(id);
    }

    @Override
    public List <Map.Entry<String,List<Dog>>> getAllDogs() {
    	List<Long> favoriteList = favoriteService.getAll();
    	Iterable<Dog> listOfDogs = dogRepository.findAllByOrderByBreed();
    	List<Dog> generalList = new ArrayList<>();
    	listOfDogs.forEach(generalList::add);
        List<Dog> orderedList = new ArrayList<Dog>();
    	for(Long entry : favoriteList){
    		for(Dog toAdd : generalList)
    			if (toAdd.getId().equals(entry)){
    				orderedList.add(toAdd);
    			}
    	}
    	generalList.removeAll(orderedList);
    	orderedList.addAll(generalList);
      
    	Stream<Map.Entry<String,List<Dog>>> sorted =
									    	StreamSupport.stream(orderedList.spliterator(), false).
											collect(Collectors.groupingBy(Dog::getBreed,Collectors.toList())).entrySet().stream();
									
    		
    	  List <Map.Entry<String,List<Dog>>> sortedList = new ArrayList<>();
			sorted.forEach(entry -> sortedList.add(entry));
		
        return sortedList;
    }
    
    @Override
    public List <Map.Entry<String,List<Dog>>> getAllDogsByBreed(String breed) {
    	
    	String searchString = breed.replaceAll("\\+", " ");
    	List<Long> favoriteList = favoriteService.getAll();   	
    	Iterable<Dog> listOfDogs = dogRepository.findDogsByBreedIgnoreCaseContaining(searchString);
       	List<Dog> generalListByBreed = new ArrayList<>();
    	listOfDogs.forEach(generalListByBreed::add);
        List<Dog> orderedList = new ArrayList<Dog>();
    	for(Long entry : favoriteList){
    		for(Dog toAdd : generalListByBreed)
    			if (toAdd.getId().equals(entry)){
    				orderedList.add(toAdd);
    			}
    	}
    	    	
    	generalListByBreed.removeAll(orderedList);
    	orderedList.addAll(generalListByBreed);
      
    	Stream<Map.Entry<String,List<Dog>>> sorted =
		    	StreamSupport.stream(orderedList.spliterator(), false).
				collect(Collectors.groupingBy(Dog::getBreed,Collectors.toList())).entrySet().stream();
		

		List <Map.Entry<String,List<Dog>>> sortedList = new ArrayList<>();
		sorted.forEach(entry -> sortedList.add(entry));
		
		return sortedList;
    
    }
    
}
