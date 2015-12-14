package ms.zui.operation;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UsersController {
	@Bean
	HeaderHttpSessionStrategy sessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}
	
	@RequestMapping(value="/token", method=RequestMethod.POST)
	public ResponseEntity<Map<String, String>> token(HttpSession session, @RequestBody User user) {
		User objUser = Application.repoUser.get(user.getName());
		
		if (objUser != null) {
			if (objUser.getPassword().compareTo(user.getPassword()) == 0) {
				session.setAttribute("user", objUser);
				return new ResponseEntity<Map<String, String>>(Collections.singletonMap("token", session.getId()), HttpStatus.OK);			
			}
		}
		return new ResponseEntity<Map<String, String>>(Collections.singletonMap("token", ""), HttpStatus.NOT_FOUND);
	}
	
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public ResponseEntity<User> logout(HttpSession session) {
    	User user = (User) session.getAttribute("user");
    	session.invalidate();

    	return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value="/token", method=RequestMethod.GET)
	public ResponseEntity<Map<String, String>> token(HttpSession session) {
		return new ResponseEntity<Map<String, String>>(Collections.singletonMap("token", session.getId()), HttpStatus.OK);
	}
	
    @RequestMapping(value="/users", method=RequestMethod.GET)
    public Collection<User> users(HttpSession session) {
    	return Application.repoUser.values();
    }
    
    @RequestMapping(value="/users/{name}", method=RequestMethod.GET)
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
    	
    	User user = null;
    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	if (Application.repoUser.containsKey(name)) {
    		user = Application.repoUser.get(name);
    	}
    	else {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<User>(user, httpStatus);
    }
    
    @RequestMapping(value="/users", method=RequestMethod.POST, consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
    	User obj = Application.repoUser.put(user.getName(), user);
    	
    	try {
        	Application.mapper.writeValue(new File("user.json"), Application.repoUser.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
    	return obj;
    }
    
    @RequestMapping(value="/users/{name}", method=RequestMethod.PUT, consumes="application/json")
    public User updateUser(@PathVariable String name, @RequestBody User user) {

    	Application.repoUser.get(name).setPassword(user.getPassword());
    	User obj = Application.repoUser.put(user.getName(), user);
    	
    	try {
        	Application.mapper.writeValue(new File("user.json"), Application.repoUser.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
    	return obj;
    }
    
    @RequestMapping(value="/users/{name}", method=RequestMethod.DELETE)
    public HttpStatus deleteUser(@PathVariable String name) {

    	Application.repoUser.remove(name);
    	
    	try {
        	Application.mapper.writeValue(new File("user.json"), Application.repoUser.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
    	return HttpStatus.OK;
    }
    
    @RequestMapping(value="/users/login", method=RequestMethod.POST, consumes="application/json")
    public HttpStatus login(@RequestBody User user) {
    	if (Application.repoUser.get(user.getName()).getPassword().equals(user.getPassword())) {
    		return HttpStatus.OK;
    	}
    	else {
    		return HttpStatus.NOT_FOUND;
    	}
    }
}
