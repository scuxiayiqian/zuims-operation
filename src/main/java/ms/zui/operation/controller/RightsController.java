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
import ms.zui.operation.datamodel.domain.Right;;


@RestController
public class RightsController {
	
    @RequestMapping(value="/rights", method=RequestMethod.GET)
    public Collection<Right> getCities(HttpSession session) {
    	return Application.rightService.getAllRights();
    }
    
    @RequestMapping(value="/rights/{name}", method=RequestMethod.GET)
    public ResponseEntity<Right> getRightByName(@PathVariable String name) {
    	
    	HttpStatus httpStatus = HttpStatus.OK;

    	Right right = Application.rightService.getRightByName(name);
    	
    	if (right == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Right>(right, httpStatus);
    }
    
    @RequestMapping(value="/rights/{name}", method=RequestMethod.POST, consumes="application/json")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Right> createRight(@RequestBody Right right, @PathVariable String name) {
    	
    	HttpStatus httpStatus = HttpStatus.CREATED;
    	
    	Right obj = Application.rightService.createRight(right);
    	
    	return new ResponseEntity<Right>(obj, httpStatus);
    }
    
    @RequestMapping(value="/rights/{name}", method=RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<Right> updateRight(@PathVariable String name, @RequestBody Right right) {

    	HttpStatus httpStatus = HttpStatus.OK;

    	Right obj = Application.rightService.updateRight(right);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	    	
    	return new ResponseEntity<Right>(obj, httpStatus);
    }
    
    @RequestMapping(value="/rights/{name}", method=RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Right> deleteRight(@PathVariable String name) {

    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	Right obj = Application.rightService.deleteRight(name);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Right>(obj, httpStatus);
    }
}
