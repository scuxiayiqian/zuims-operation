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

import ms.zui.operation.datamodel.dto.RightDTO;
import ms.zui.operation.service.RightService;;


@RestController
public class RightsController {
	
	@Autowired
	RightService rightService;
	
    @RequestMapping(value="/rights", method=RequestMethod.GET)
    public List<RightDTO> getRights(HttpSession session) {
    	return rightService.getRights();
    }
    
    @RequestMapping(value="/rights/{id}", method=RequestMethod.GET)
    public ResponseEntity<RightDTO> getRightByName(@PathVariable long id) {
    	
    	HttpStatus httpStatus = HttpStatus.OK;

    	RightDTO rightDTO = rightService.getRight(id);
    	
    	if (rightDTO == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<RightDTO>(rightDTO, httpStatus);
    }
    
    @RequestMapping(value="/rights/{id}", method=RequestMethod.POST, consumes="application/json")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RightDTO> createRight(@RequestBody RightDTO rightDTO, @PathVariable long id) {
    	
    	HttpStatus httpStatus = HttpStatus.CREATED;
    	
    	RightDTO obj = rightService.createRight(rightDTO);
    	
    	return new ResponseEntity<RightDTO>(obj, httpStatus);
    }
    
    @RequestMapping(value="/rights/{id}", method=RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<RightDTO> updateRight(@PathVariable long id, @RequestBody RightDTO rightDTO) {

    	HttpStatus httpStatus = HttpStatus.OK;

    	RightDTO obj = rightService.updateRight(rightDTO);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	    	
    	return new ResponseEntity<RightDTO>(obj, httpStatus);
    }
    
    @RequestMapping(value="/rights/{id}", method=RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RightDTO> deleteRight(@PathVariable long id) {

    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	RightDTO obj = rightService.deleteRight(id);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<RightDTO>(obj, httpStatus);
    }
}
