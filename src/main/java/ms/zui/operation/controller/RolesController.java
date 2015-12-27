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
import ms.zui.operation.datamodel.domain.Role;;


@RestController
public class RolesController {
	
    @RequestMapping(value="/roles", method=RequestMethod.GET)
    public Collection<Role> getCities(HttpSession session) {
    	return Application.roleService.getAllRoles();
    }
    
    @RequestMapping(value="/roles/{name}", method=RequestMethod.GET)
    public ResponseEntity<Role> getRoleByName(@PathVariable String name) {
    	
    	HttpStatus httpStatus = HttpStatus.OK;

    	Role role = Application.roleService.getRoleByName(name);
    	
    	if (role == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Role>(role, httpStatus);
    }
    
    @RequestMapping(value="/roles", method=RequestMethod.POST, consumes="application/json")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
    	
    	HttpStatus httpStatus = HttpStatus.CREATED;
    	
    	Role obj = Application.roleService.createRole(role);
    	
    	return new ResponseEntity<Role>(obj, httpStatus);
    }
    
    @RequestMapping(value="/roles/{name}", method=RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<Role> updateRole(@PathVariable String name, @RequestBody Role role) {

    	HttpStatus httpStatus = HttpStatus.OK;

    	Role obj = Application.roleService.updateRole(role);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	    	
    	return new ResponseEntity<Role>(obj, httpStatus);
    }
    
    @RequestMapping(value="/roles/{name}", method=RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Role> deleteRole(@PathVariable String name) {

    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	Role obj = Application.roleService.deleteRole(name);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Role>(obj, httpStatus);
    }
}
