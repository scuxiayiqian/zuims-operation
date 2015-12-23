package ms.zui.operation.service;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import ms.zui.operation.Application;
import ms.zui.operation.datamodel.domain.City;

public class CityService {

	private HashMap<String, City> 	repoCity;	
	private ObjectMapper mapper = new ObjectMapper();
	
	public CityService() {
		
		City[] cities = null;
		
		try {
			cities = mapper.readValue(new File(Application.dataPath + "city.json"), City[].class);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		repoCity = new HashMap<String, City>();
		
		for (int nIndex = 0; nIndex < cities.length; nIndex++) {
			repoCity.put(cities[nIndex].getName(), cities[nIndex]);
		}
	}
	
	public City getCityByName(String name) {
		
		return repoCity.get(name);
	}
	
	public Collection<City> getAllCities() {
		return repoCity.values();
	}
	
	public City createCity(City city) {
		
		repoCity.put(city.getName(), city);
		
	   	try {
	   		mapper.writeValue(new File(Application.dataPath + "city.json"), repoCity.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return city;
	}
	
	public City updateCity(City city) {
		
		City returnCity = repoCity.put(city.getName(), city);
		
	   	try {
        	mapper.writeValue(new File(Application.dataPath + "city.json"), repoCity.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return returnCity;
	}

	public City deleteCity(String name) {
		
		City returnCity = repoCity.remove(name);
		
	   	try {
        	mapper.writeValue(new File(Application.dataPath + "city.json"), repoCity.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return returnCity;
	}
}
