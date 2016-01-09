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

import ms.zui.operation.datamodel.domain.Role;
import ms.zui.operation.service.RoleService;;


@RestController
public class RolesController extends BaseController{
	
	@Autowired
	RoleService roleService;
	
    @RequestMapping(value="/roles", method=RequestMethod.GET)
    public List<Role> getRoles(HttpSession session) {
    	return roleService.getAllRoles();
    }
    
    @RequestMapping(value="/roles/{id}", method=RequestMethod.GET)
    public ResponseEntity<Role> getRoleByName(@PathVariable long id) {
    	
    	HttpStatus httpStatus = HttpStatus.OK;

    	Role role = roleService.getRoleByName(id);
    	
    	if (role == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Role>(role, httpStatus);
    }
    
    @RequestMapping(value="/roles/{name}", method=RequestMethod.POST, consumes="application/json")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')")
    public ResponseEntity<Role> createRole(@PathVariable String name, @RequestBody Role role) {
    	
    	HttpStatus httpStatus = HttpStatus.CREATED;
    	
    	Role obj = roleService.createRole(role);
    	
    	return new ResponseEntity<Role>(obj, httpStatus);
    }
    
    @RequestMapping(value="/roles/{name}", method=RequestMethod.PUT, consumes="application/json")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')")
    public ResponseEntity<Role> updateRole(@PathVariable String name, @RequestBody Role role) {

    	HttpStatus httpStatus = HttpStatus.OK;

    	Role obj = roleService.updateRole(role);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	    	
    	return new ResponseEntity<Role>(obj, httpStatus);
    }
    
    @RequestMapping(value="/roles/{id}", method=RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')")
    public ResponseEntity<Role> deleteRole(@PathVariable long id) {

    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	Role obj = roleService.deleteRole(id);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<Role>(obj, httpStatus);
    }
}
