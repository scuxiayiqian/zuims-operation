package ms.zui.operation.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ms.zui.operation.Application;
import ms.zui.operation.datamodel.domain.Guest;


@RestController
public class GuestsController {
	
    @RequestMapping(value="/guests", method=RequestMethod.GET)
    public Collection<Guest> guests(HttpSession session) {
    	return Application.guestService.getAllGuests();
    }
    
    @RequestMapping(value="/guests/{name}", method=RequestMethod.GET)
    public ResponseEntity<Guest> getGuestByName(@PathVariable String name) {
    	
    	HttpStatus httpStatus = HttpStatus.OK;

    	Guest guest = Application.guestService.getGuestByName(name);
    	
    	if (guest == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Guest>(guest, httpStatus);
    }
    
    @RequestMapping(value="/guests", method=RequestMethod.POST, consumes="application/json")
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) {
    	
    	HttpStatus httpStatus = HttpStatus.CREATED;
    	
    	Guest obj = Application.guestService.createGuest(guest);
    	
    	return new ResponseEntity<Guest>(obj, httpStatus);
    }
    
    @RequestMapping(value="/guests/{name}", method=RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<Guest> updateGuest(@PathVariable String name, @RequestBody Guest guest) {

    	HttpStatus httpStatus = HttpStatus.OK;

    	Guest obj = Application.guestService.updateGuest(guest);
    	
    	if (guest == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	    	
    	return new ResponseEntity<Guest>(obj, httpStatus);
    }
    
    @RequestMapping(value="/guests/{name}", method=RequestMethod.DELETE)
    public ResponseEntity<Guest> deleteGuest(@PathVariable String name) {

    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	Guest obj = Application.guestService.deleteGuest(name);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Guest>(obj, httpStatus);
    }
}
