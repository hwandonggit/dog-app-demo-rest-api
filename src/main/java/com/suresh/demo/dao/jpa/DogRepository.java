package com.suresh.demo.dao.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.suresh.demo.domain.Dog;


@Repository
public interface DogRepository extends PagingAndSortingRepository<Dog, Long> {
	
    Iterable<Dog> findDogsByBreedIgnoreCaseContaining(@Param("breed") String breed);
    Iterable<Dog> findAllByOrderByBreed();

}
