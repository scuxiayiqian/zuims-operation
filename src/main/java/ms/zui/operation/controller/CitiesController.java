package ms.zui.operation.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ms.zui.operation.datamodel.domain.City;
import ms.zui.operation.service.CityService;


@RestController
public class CitiesController extends BaseController{
	
	@Autowired
	CityService cityService;
	
    @RequestMapping(value="/cities", method=RequestMethod.GET)
    public List<City> getCities(HttpSession session) {
    	return this.cityService.getAllCities();
    }
    
    @RequestMapping(value="/cities/{name}", method=RequestMethod.GET)
    public ResponseEntity<City> getCityByName(@PathVariable String name) {
    	
    	HttpStatus httpStatus = HttpStatus.OK;

    	City city = cityService.getCityByName(name);
    	
    	if (city == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<City>(city, httpStatus);
    }
    
    @RequestMapping(value="/cities", method=RequestMethod.POST, consumes="application/json")
	@PreAuthorize("hasRole('ROLE_DERICTOR') or hasRole('ROLE_SUPER') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<City> createCity(@RequestBody City city) {
    	
    	HttpStatus httpStatus = HttpStatus.CREATED;
    	
    	City obj = cityService.createCity(city);
    	
    	return new ResponseEntity<City>(obj, httpStatus);
    }
    
    @RequestMapping(value="/cities/{name}", method=RequestMethod.PUT, consumes="application/json")
	@PreAuthorize("hasRole('ROLE_DERICTOR') or hasRole('ROLE_SUPER') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<City> updateCity(@PathVariable String name, @RequestBody City city) {

    	HttpStatus httpStatus = HttpStatus.OK;

    	City obj = cityService.updateCity(city);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	    	
    	return new ResponseEntity<City>(obj, httpStatus);
    }
    
    @RequestMapping(value="/cities/{name}", method=RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_DERICTOR') or hasRole('ROLE_SUPER') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<City> deleteCity(@PathVariable String name) {

    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	City obj = cityService.deleteCity(name);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<City>(obj, httpStatus);
    }
}
