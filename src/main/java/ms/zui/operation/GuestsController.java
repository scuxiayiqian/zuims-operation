package ms.zui.operation;

import java.io.File;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GuestsController {
	
    @RequestMapping(value="/guests", method=RequestMethod.GET)
    public Collection<Guest> guests(HttpSession session) {
    	return Application.repoGuest.values();
    }
    
    @RequestMapping(value="/guests/{name}", method=RequestMethod.GET)
    public ResponseEntity<Guest> getGuestByName(@PathVariable String name) {
    	
    	Guest guest = null;
    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	if (Application.repoGuest.containsKey(name)) {
    		guest = Application.repoGuest.get(name);
    	}
    	else {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Guest>(guest, httpStatus);
    }
    
    @RequestMapping(value="/guests", method=RequestMethod.POST, consumes="application/json")
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) {
    	
    	HttpStatus httpStatus = HttpStatus.CREATED;
    	Guest obj = Application.repoGuest.put(guest.getName(), guest);
    	
    	try {
        	Application.mapper.writeValue(new File(Application.dataPath + "guest.json"), Application.repoGuest.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Guest>(obj, httpStatus);
    }
    
    @RequestMapping(value="/guests/{name}", method=RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<Guest> updateGuest(@PathVariable String name, @RequestBody Guest guest) {

    	HttpStatus httpStatus = HttpStatus.OK;
    	Guest obj = Application.repoGuest.put(guest.getName(), guest);
    	
    	try {
        	Application.mapper.writeValue(new File(Application.dataPath + "guest.json"), Application.repoGuest.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Guest>(obj, httpStatus);
    }
    
    @RequestMapping(value="/guests/{name}", method=RequestMethod.DELETE)
    public ResponseEntity<Guest> deleteGuest(@PathVariable String name) {

    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	Guest obj = Application.repoGuest.remove(name);
    	
    	try {
        	Application.mapper.writeValue(new File(Application.dataPath + "guest.json"), Application.repoGuest.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Guest>(obj, httpStatus);
    }
}
