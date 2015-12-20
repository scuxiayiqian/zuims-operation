package ms.zui.operation.service;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import ms.zui.operation.Application;
import ms.zui.operation.datamodel.domain.Restaurant;

public class RestaurantService {

	private HashMap<String, Restaurant> 	repoRestaurant;	
	private ObjectMapper mapper = new ObjectMapper();
	
	public RestaurantService() {
		
		Restaurant[] restaurants = null;
		
		try {
			restaurants = mapper.readValue(new File(Application.dataPath + "restaurant.json"), Restaurant[].class);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		repoRestaurant = new HashMap<String, Restaurant>();
		
		for (int nIndex = 0; nIndex < restaurants.length; nIndex++) {
			repoRestaurant.put(restaurants[nIndex].getName(), restaurants[nIndex]);
		}
	}

	public Restaurant getRestaurantByName(String name) {
		
		return repoRestaurant.get(name);
	}

	public Collection<Restaurant> getAllRestaurants() {
		return repoRestaurant.values();
	}

	public Collection<Restaurant> getPromotedRestaurantsByCity(String city) {
		
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		
		for (Restaurant obj: repoRestaurant.values()) {
			
			if (obj.getIsPromoted() && city.equals(obj.getCity())) {
				
				restaurants.add(obj);
			}
		}
		
		return restaurants;
	}

	public Collection<Restaurant> getRestaurantsByMarketingName(String marketingName) {
		
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		
		for (Restaurant obj: repoRestaurant.values()) {
			
			if (obj.getMarketingName().equals(marketingName)) {
				
				restaurants.add(obj);
			}
		}
		
		return restaurants;
	}
	
	private void clearPromotedRestaurantsByCity(String city) {
		
		for (Restaurant obj: repoRestaurant.values()) {
			
			if (city.equals(obj.getCity())) {
				
				obj.setIsPromoted(false);
			}
		}
	}
	
	public Collection<Restaurant> updatePromotedRestaurantsByCity(String city, Restaurant[] restaurants) {
		
		clearPromotedRestaurantsByCity(city);
		
		for (Restaurant obj: restaurants) {
			
			Restaurant restaurant = repoRestaurant.get(obj.getName());
			
			if (restaurant.getCity().equals(city)) {
				
				repoRestaurant.put(obj.getName(), obj);
			}
		}

	   	try {
        	mapper.writeValue(new File(Application.dataPath + "restaurant.json"), repoRestaurant.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}

		return getPromotedRestaurantsByCity(city);
	}
	
	public Restaurant createUser(Restaurant restaurant) {
		
		repoRestaurant.put(restaurant.getName(), restaurant);
		
	   	try {
        	mapper.writeValue(new File(Application.dataPath + "restaurant.json"), repoRestaurant.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return restaurant;
	}
	
	public Restaurant updateRestaurant(Restaurant restaurant) {
		
		Restaurant returnRest = repoRestaurant.put(restaurant.getName(), restaurant);
		
	   	try {
        	mapper.writeValue(new File(Application.dataPath + "restaurant.json"), repoRestaurant.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return returnRest;
	}

	public Restaurant deleteRestaurant(String name) {
		
		Restaurant returnRest = repoRestaurant.remove(name);
		
	   	try {
        	mapper.writeValue(new File(Application.dataPath + "restaurant.json"), repoRestaurant.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return returnRest;
	}

}
