package ms.zui.operation.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
		
    @RequestMapping(value="/token", method=RequestMethod.DELETE)
    public HttpStatus logout(HttpSession session) {
    	session.invalidate();

    	return HttpStatus.OK;
    }

    @RequestMapping(value="/token", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> token(HttpSession session) {
    	
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	
    	User user = Application.userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
    	
    	map.put("user", user);
    	map.put("token", session.getId());
    	
    	session.setAttribute("user", user.getName());
    	
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
    @RequestMapping(value="/users", method=RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public Collection<User> users(HttpSession session) {
    	return Application.userService.getAllUsers();
    }
    
    @RequestMapping(value="/users/{name}", method=RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
    	
    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	User user = Application.userService.getUserByName(name);
    	
    	if (user == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<User>(user, httpStatus);
    }
    
    @RequestMapping(value="/roles/{role}/users", method=RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public Collection<User> getUsersByRole(@PathVariable String role) {
    	    	
    	Collection<User> users = Application.userService.getUsersByRole(role);
    	    	
    	return users;
    }

    @RequestMapping(value="/users", method=RequestMethod.POST, consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public User createUser(@RequestBody User user) {
    	
    	User obj = Application.userService.createUser(user);
    	   	
    	return obj;
    }
    
    @RequestMapping(value="/users/{name}", method=RequestMethod.PUT, consumes="application/json")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public User updateUser(@PathVariable String name, @RequestBody User user) {

    	User obj = Application.userService.updateUser(user);
    	
    	return obj;
    }
    
    @RequestMapping(value="/users/{name}", method=RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public HttpStatus deleteUser(@PathVariable String name) {

    	Application.userService.deleteUser(name);
    	
    	return HttpStatus.OK;
    }
}
