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
import org.springframework.web.bind.annotation.RestController;

import ms.zui.operation.Application;
import ms.zui.operation.datamodel.domain.Production;

@RestController
public class ProductionsController extends BaseController{
	
	@RequestMapping(value="/productions", method=RequestMethod.GET)
	public Collection<Production> getProductions(HttpSession session) {
		return Application.productionService.getAllProductions();
	}
	
	@RequestMapping(value="/productions/{name}", method=RequestMethod.GET)
	public ResponseEntity<Production> getProductionByName(@PathVariable String name) {
		
		HttpStatus httpStatus = HttpStatus.OK;
		
		Production production = Application.productionService.getProductionByName(name);
		
		if (production == null) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<Production>(production, httpStatus);
	}
	
	@RequestMapping(value="/productions", method=RequestMethod.POST, consumes="application/json")
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_DIRECTOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')")
    public ResponseEntity<Production> createProduction(@RequestBody Production production) {
    	
    	HttpStatus httpStatus = HttpStatus.CREATED;
    	
    	Production obj = Application.productionService.createProduction(production);
    	
    	return new ResponseEntity<Production>(obj, httpStatus);
    }
	
	@RequestMapping(value="/productions/{name}", method=RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<Production> updateProduction(@PathVariable String name, @RequestBody Production production) {

    	HttpStatus httpStatus = HttpStatus.OK;

    	Production obj = Application.productionService.updateProduction(production);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	    	
    	return new ResponseEntity<Production>(obj, httpStatus);
    }
	
	@RequestMapping(value="/productions/{name}", method=RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_DIRECTOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')")
    public ResponseEntity<Production> deleteProduction(@PathVariable String name) {

    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	Production obj = Application.productionService.deletProduction(name);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Production>(obj, httpStatus);
    }

}
