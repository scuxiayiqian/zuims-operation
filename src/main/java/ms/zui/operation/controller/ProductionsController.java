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
import ms.zui.operation.datamodel.domain.Production;
import ms.zui.operation.service.ProductionService;

@RestController
public class ProductionsController extends BaseController{
	
	@Autowired
	ProductionService productionService;
	
	@RequestMapping(value="/productions", method=RequestMethod.GET)
	public List<Production> getProductions(HttpSession session) {
		return productionService.getAllProductions();
	}
	
	@RequestMapping(value="/productions/{id}", method=RequestMethod.GET)
	public ResponseEntity<Production> getProductionByName(@PathVariable long id) {
		
		HttpStatus httpStatus = HttpStatus.OK;
		
		Production production = productionService.getProductionById(id);
		
		if (production == null) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<Production>(production, httpStatus);
	}
	
	@RequestMapping(value="/productions", method=RequestMethod.POST, consumes="application/json")
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_DIRECTOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')")
    public ResponseEntity<Production> createProduction(@RequestBody Production production) {
    	
    	HttpStatus httpStatus = HttpStatus.CREATED;
    	
    	Production obj = productionService.createProduction(production);
    	
    	return new ResponseEntity<Production>(obj, httpStatus);
    }
	
	@RequestMapping(value="/productions/{id}", method=RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<Production> updateProduction(@PathVariable long id, @RequestBody Production production) {

    	HttpStatus httpStatus = HttpStatus.OK;

    	Production obj = productionService.updateProduction(production);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	    	
    	return new ResponseEntity<Production>(obj, httpStatus);
    }
	
	@RequestMapping(value="/productions/{id}", method=RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_DIRECTOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')")
    public ResponseEntity<Production> deleteProduction(@PathVariable long id) {

    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	Production obj = productionService.deletProduction(id);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Production>(obj, httpStatus);
    }

}
