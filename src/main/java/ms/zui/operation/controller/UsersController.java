package ms.zui.operation.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ms.zui.operation.Application;
import ms.zui.operation.datamodel.domain.User;


@RestController
public class UsersController {
	
	@RequestMapping(value="/token", method=RequestMethod.POST)
	public ResponseEntity<Map<String, String>> token(HttpSession session, @RequestBody User user) {
		User objUser = Application.userService.getUserByName(user.getName());
		
		if (objUser != null) {
			if (objUser.getPassword().compareTo(user.getPassword()) == 0) {
				session.setAttribute("user", objUser);
				return new ResponseEntity<Map<String, String>>(Collections.singletonMap("token", session.getId()), HttpStatus.OK);			
			}
		}
		return new ResponseEntity<Map<String, String>>(Collections.singletonMap("token", ""), HttpStatus.NOT_FOUND);
	}
	
    @RequestMapping(value="/users/logout", method=RequestMethod.PUT)
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
    	return Application.userService.getAllUsers();
    }
    
    @RequestMapping(value="/users/{name}", method=RequestMethod.GET)
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
    	
    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	User user = Application.userService.getUserByName(name);
    	
    	if (user == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<User>(user, httpStatus);
    }
    
    @RequestMapping(value="/users", method=RequestMethod.POST, consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
    	
    	User obj = Application.userService.createUser(user);
    	   	
    	return obj;
    }
    
    @RequestMapping(value="/users/{name}", method=RequestMethod.PUT, consumes="application/json")
    public User updateUser(@PathVariable String name, @RequestBody User user) {

    	User obj = Application.userService.updateUser(user);
    	
    	return obj;
    }
    
    @RequestMapping(value="/users/{name}", method=RequestMethod.DELETE)
    public HttpStatus deleteUser(@PathVariable String name) {

    	Application.userService.deleteUser(name);
    	
    	return HttpStatus.OK;
    }
    
    @RequestMapping(value="/users/login", method=RequestMethod.POST, consumes="application/json")
    public HttpStatus login(@RequestBody User user) {
    	
    	if (Application.userService.login(user)) {
    		return HttpStatus.OK;
    	} 
    	else {
    		return HttpStatus.NOT_FOUND;
    	}
    }
}
