package com.suresh.demo.api.rest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.suresh.demo.domain.Dog;
import com.suresh.demo.exception.DataFormatException;
import com.suresh.demo.service.DogServiceImpl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;


@RestController
@RequestMapping(value = "/example/v1/dogs")
@Api(value = "dogs", description = "Dog API")
public class DogController extends AbstractRestHandler {

    @Autowired
    private DogServiceImpl dogService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createDog(@RequestBody Dog dog,
                                 HttpServletRequest request, HttpServletResponse response) {
        Dog createdDog = this.dogService.createDog(dog);
        response.setHeader("Dog", request.getRequestURL().append("/").append(createdDog.getId()).toString());
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Map.Entry<String,List<Dog>>> getAllDog(
                                      HttpServletRequest request, HttpServletResponse response) {
        return this.dogService.getAllDogs();
    }

   
    @RequestMapping(value = "/breeds/{breed}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Map.Entry<String,List<Dog>>> getAllDogByBreed(
                                      @PathVariable("breed") String breed,
                                      HttpServletRequest request, HttpServletResponse response) {
        return this.dogService.getAllDogsByBreed(breed);
    }
    
    
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Dog getDog(@ApiParam(value = "The ID of the dog.", required = true)
                             @PathVariable("id") Long id,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        Dog dog = this.dogService.getDog(id);
        checkResourceFound(dog);
        return dog;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a dog resource.", notes = "You have to provide a valid dog ID in the URL and in the payload. The ID attribute can not be updated.")
    public void updateDog(@ApiParam(value = "The ID of the existing dog resource.", required = true)
                                 @PathVariable("id") Long id, @RequestBody Dog dog,
                                 HttpServletRequest request, HttpServletResponse response) {
        checkResourceFound(this.dogService.getDog(id));
        if (id != dog.getId()) throw new DataFormatException("ID doesn't match!");
        this.dogService.updateDog(dog);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a dog resource.", notes = "You have to provide a valid dog ID in the URL. Once deleted the resource can not be recovered.")
    public void deleteDog(@ApiParam(value = "The ID of the existing dog resource.", required = true)
                                 @PathVariable("id") Long id, HttpServletRequest request,
                                 HttpServletResponse response) {
        checkResourceFound(this.dogService.getDog(id));
        this.dogService.deleteDog(id);
    }
}
