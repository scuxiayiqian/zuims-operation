package ms.zui.operation.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ms.zui.operation.Application;
import ms.zui.operation.datamodel.domain.Restaurant;


@RestController
public class RestaurantsController {
	
    @RequestMapping(value="/restaurants", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public Collection<Restaurant> restaurants(HttpSession session) {
    	return Application.restaurantService.getAllRestaurants();
    }
    
    @RequestMapping(value="/restaurants/{name}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_MARKETING')")    
    public ResponseEntity<Restaurant> getRestaurantByName(@PathVariable String name) {
    	
    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	Restaurant restaurant = Application.restaurantService.getRestaurantByName(name);
    	if (restaurant == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Restaurant>(restaurant, httpStatus);
    }
    
    @RequestMapping(value="/restaurants", method=RequestMethod.POST, consumes="application/json")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_MARKETING')")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
    	
    	HttpStatus httpStatus = HttpStatus.CREATED;
    	
    	Restaurant obj = Application.restaurantService.createRestaurant(restaurant);

    	if (obj == null) {
    		httpStatus = HttpStatus.CONFLICT;
    	}
    	
    	return new ResponseEntity<Restaurant>(obj, httpStatus);
    }
    
    @RequestMapping(value="/restaurants/{name}", method=RequestMethod.PUT, consumes="application/json")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_MARKETING')")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable String name, @RequestBody Restaurant restaurant) {

    	HttpStatus httpStatus = HttpStatus.OK;

    	Restaurant obj = Application.restaurantService.updateRestaurant(restaurant);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	return new ResponseEntity<Restaurant>(obj, httpStatus);
    }
    
    @RequestMapping(value="/restaurants/{name}", method=RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable String name) {

    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	Restaurant obj = Application.restaurantService.deleteRestaurant(name);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Restaurant>(obj, httpStatus);
    }
     
    @RequestMapping(value="/cities/{city}/restaurants/promoted", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public Collection<Restaurant> getPromotedRestaurantsByCity(HttpSession session,
    		@PathVariable String city) {
    	    	
    	return Application.restaurantService.getPromotedRestaurantsByCity(city);
    }
    
    @RequestMapping(value="/cities/{city}/restaurants/promoted", method=RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<Collection<Restaurant>> updatePromotedRestaurants(HttpSession session, 
    		@PathVariable String city, @RequestBody Restaurant[] restaurants) {
    	
    	return new ResponseEntity<Collection<Restaurant>>(Application.restaurantService.updatePromotedRestaurantsByCity(city, restaurants), HttpStatus.OK);
    }

    @RequestMapping(value="/users/{name}/restaurants", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_MARKETING')")
    public Collection<Restaurant> getRestaurantsByMarketing(HttpSession session, @PathVariable String name) {
    	    	
    	return Application.restaurantService.getRestaurantsByMarketingName(name);
    }
 }
