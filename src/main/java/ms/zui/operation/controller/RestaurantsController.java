package ms.zui.operation.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ms.zui.operation.Application;
import ms.zui.operation.datamodel.domain.Restaurant;


@RestController
public class RestaurantsController {
	
    @RequestMapping(value="/restaurants", method=RequestMethod.GET)
    public Collection<Restaurant> restaurants(HttpSession session) {
    	return Application.restaurantService.getAllRestaurants();
    }
    
    @RequestMapping(value="/restaurants/{name}", method=RequestMethod.GET)
    public ResponseEntity<Restaurant> getRestaurantByName(@PathVariable String name) {
    	
    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	Restaurant restaurant = Application.restaurantService.getRestaurantByName(name);
    	if (restaurant == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Restaurant>(restaurant, httpStatus);
    }
    
    @RequestMapping(value="/restaurants", method=RequestMethod.POST, consumes="application/json")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
    	
    	HttpStatus httpStatus = HttpStatus.CREATED;
    	
    	Restaurant obj = Application.restaurantService.createUser(restaurant);
    	  	
    	return new ResponseEntity<Restaurant>(obj, httpStatus);
    }
    
    @RequestMapping(value="/restaurants/{name}", method=RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable String name, @RequestBody Restaurant restaurant) {

    	HttpStatus httpStatus = HttpStatus.OK;

    	Restaurant obj = Application.restaurantService.updateRestaurant(restaurant);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	return new ResponseEntity<Restaurant>(obj, httpStatus);
    }
    
    @RequestMapping(value="/restaurants/{name}", method=RequestMethod.DELETE)
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable String name) {

    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	Restaurant obj = Application.restaurantService.deleteRestaurant(name);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Restaurant>(obj, httpStatus);
    }
    
    @RequestMapping(value="/restaurants/promoted", method=RequestMethod.GET)
    public Collection<Restaurant> getPromotedRestaurants(HttpSession session) {
    	    	
    	return Application.restaurantService.getPromotedRestaurants();
    }
 
    @RequestMapping(value="/restaurants/promoted", method=RequestMethod.PUT)
    public ResponseEntity<Collection<Restaurant>> updatePromotedRestaurants(HttpSession session, @RequestBody Restaurant[] restaurants) {
    	
    	return new ResponseEntity<Collection<Restaurant>>(Application.restaurantService.updatePromotedRestaurants(restaurants), HttpStatus.OK);
    }

    @RequestMapping(value="/users/{name}/restaurants", method=RequestMethod.GET)
    public Collection<Restaurant> getRestaurantsByMarketing(HttpSession session, @PathVariable String name) {
    	    	
    	return Application.restaurantService.getRestaurantsByMarketingName(name);
    }
 }
