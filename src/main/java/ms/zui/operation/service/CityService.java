package ms.zui.operation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ms.zui.operation.datamodel.dao.CityRepository;
import ms.zui.operation.datamodel.domain.City;

@Service
public class CityService extends BaseService{

	@Autowired
	CityRepository cityService;
		
	public City getCityByName(String name) {
		
		return this.cityService.findOne(name);
	}
	
	public List<City> getAllCities() {
		List<City> cities = new ArrayList<City>();
		
		for(City city: this.cityService.findAll()) {
			cities.add(city);
		}
		return cities;
	}
	
	public City createCity(City city) {
		
		City result = null;
		
	   	try {
	   		result = this.cityService.save(city);
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return result;
	}
	
	public City updateCity(City city) {

		City result = null;
		
	   	try {
	   		result = this.cityService.save(city);
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return result;
	}

	public City deleteCity(String name) {

		City result = null;
		
	   	try {
	   		
	   		result = this.cityService.findOne(name);
	   		
	   		this.cityService.delete(name);
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return result;
	}
}
