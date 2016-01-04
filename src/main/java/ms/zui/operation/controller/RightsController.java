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

import ms.zui.operation.Application;
import ms.zui.operation.datamodel.domain.Right;
import ms.zui.operation.service.RightService;;


@RestController
public class RightsController {
	
	@Autowired
	RightService rightService;
	
    @RequestMapping(value="/rights", method=RequestMethod.GET)
    public List<Right> getRights(HttpSession session) {
    	return rightService.getRights();
    }
    
    @RequestMapping(value="/rights/{id}", method=RequestMethod.GET)
    public ResponseEntity<Right> getRightByName(@PathVariable long id) {
    	
    	HttpStatus httpStatus = HttpStatus.OK;

    	Right right = rightService.getRight(id);
    	
    	if (right == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Right>(right, httpStatus);
    }
    
    @RequestMapping(value="/rights/{id}", method=RequestMethod.POST, consumes="application/json")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Right> createRight(@RequestBody Right right, @PathVariable long id) {
    	
    	HttpStatus httpStatus = HttpStatus.CREATED;
    	
    	Right obj = rightService.createRight(right);
    	
    	return new ResponseEntity<Right>(obj, httpStatus);
    }
    
    @RequestMapping(value="/rights/{id}", method=RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<Right> updateRight(@PathVariable long id, @RequestBody Right right) {

    	HttpStatus httpStatus = HttpStatus.OK;

    	Right obj = rightService.updateRight(right);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	    	
    	return new ResponseEntity<Right>(obj, httpStatus);
    }
    
    @RequestMapping(value="/rights/{id}", method=RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Right> deleteRight(@PathVariable long id) {

    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	Right obj = rightService.deleteRight(id);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Right>(obj, httpStatus);
    }
}
