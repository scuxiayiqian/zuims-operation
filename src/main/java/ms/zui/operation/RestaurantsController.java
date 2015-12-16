package ms.zui.operation;

import java.io.File;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestaurantsController {
	
    @RequestMapping(value="/restaurants", method=RequestMethod.GET)
    public Collection<Restaurant> restaurants(HttpSession session) {
    	return Application.repoRestaurant.values();
    }
    
    @RequestMapping(value="/restaurants/{name}", method=RequestMethod.GET)
    public ResponseEntity<Restaurant> getRestaurantByName(@PathVariable String name) {
    	
    	Restaurant restaurant = null;
    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	if (Application.repoRestaurant.containsKey(name)) {
    		restaurant = Application.repoRestaurant.get(name);
    	}
    	else {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Restaurant>(restaurant, httpStatus);
    }
    
    @RequestMapping(value="/restaurants", method=RequestMethod.POST, consumes="application/json")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
    	
    	HttpStatus httpStatus = HttpStatus.CREATED;
    	Restaurant obj = Application.repoRestaurant.put(restaurant.getName(), restaurant);
    	
    	try {
        	Application.mapper.writeValue(new File(Application.dataPath + "restaurant.json"), Application.repoRestaurant.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Restaurant>(obj, httpStatus);
    }
    
    @RequestMapping(value="/restaurants/{name}", method=RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable String name, @RequestBody Restaurant restaurant) {

    	HttpStatus httpStatus = HttpStatus.OK;
    	Restaurant obj = Application.repoRestaurant.put(restaurant.getName(), restaurant);
    	
    	try {
        	Application.mapper.writeValue(new File(Application.dataPath + "restaurant.json"), Application.repoRestaurant.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Restaurant>(obj, httpStatus);
    }
    
    @RequestMapping(value="/restaurants/{name}", method=RequestMethod.DELETE)
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable String name) {

    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	Restaurant obj = Application.repoRestaurant.remove(name);
    	
    	try {
        	Application.mapper.writeValue(new File(Application.dataPath + "restaurant.json"), Application.repoRestaurant.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Restaurant>(obj, httpStatus);
    }
}
