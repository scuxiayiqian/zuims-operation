package ms.zui.operation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

import ms.zui.operation.datamodel.dto.UserDTO;
import ms.zui.operation.service.UserService;


@RestController
public class UsersController {
		
	@Autowired
	UserService userService;
	
    @RequestMapping(value="/token", method=RequestMethod.DELETE)
    public HttpStatus logout(HttpSession session) {
    	session.invalidate();

    	return HttpStatus.OK;
    }

    @RequestMapping(value="/token", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> token(HttpSession session) {
    	
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	
    	UserDTO userDTO = userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
    	
    	map.put("user", userDTO);
    	map.put("token", session.getId());
    	
    	session.setAttribute("user", userDTO.getName());
    	
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
    @RequestMapping(value="/users", method=RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')")
    public List<UserDTO> users(HttpSession session) {
    	return userService.getAllUsers();
    }
    
    @RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long id) {
    	
    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	UserDTO userDTO = userService.getUserById(id);
    	
    	if (userDTO == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<UserDTO>(userDTO, httpStatus);
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.POST, consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')")
    public UserDTO createUser(@PathVariable long id, @RequestBody UserDTO userDTO) {
    	
    	UserDTO obj = userService.createUser(userDTO);
    	   	
    	return obj;
    }
    
    @RequestMapping(value="/users/{id}", method=RequestMethod.PUT, consumes="application/json")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')")
    public UserDTO updateUser(@PathVariable long id, @RequestBody UserDTO userDTO) {

    	UserDTO obj = userService.updateUser(userDTO);
    	
    	return obj;
    }
    
    @RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')")
    public HttpStatus deleteUser(@PathVariable long id) {

    	userService.deleteUser(id);
    	
    	return HttpStatus.OK;
    }
}
