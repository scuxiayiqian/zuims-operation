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

import ms.zui.operation.datamodel.dto.RoleDTO;
import ms.zui.operation.service.RoleService;;


@RestController
public class RolesController {
	
	@Autowired
	RoleService roleService;
	
    @RequestMapping(value="/roles", method=RequestMethod.GET)
    public List<RoleDTO> getRoles(HttpSession session) {
    	return roleService.getAllRoles();
    }
    
    @RequestMapping(value="/roles/{id}", method=RequestMethod.GET)
    public ResponseEntity<RoleDTO> getRoleByName(@PathVariable long id) {
    	
    	HttpStatus httpStatus = HttpStatus.OK;

    	RoleDTO roleDTO = roleService.getRoleById(id);
    	
    	if (roleDTO == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<RoleDTO>(roleDTO, httpStatus);
    }
    
    @RequestMapping(value="/roles/{id}", method=RequestMethod.POST, consumes="application/json")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')")
    public ResponseEntity<RoleDTO> createRole(@PathVariable long id, @RequestBody RoleDTO roleDTO) {
    	
    	HttpStatus httpStatus = HttpStatus.CREATED;
    	
    	RoleDTO obj = roleService.createRole(roleDTO);
    	
    	return new ResponseEntity<RoleDTO>(obj, httpStatus);
    }
    
    @RequestMapping(value="/roles/{id}", method=RequestMethod.PUT, consumes="application/json")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable long id, @RequestBody RoleDTO roleDTO) {

    	HttpStatus httpStatus = HttpStatus.OK;

    	RoleDTO obj = roleService.updateRole(roleDTO);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	    	
    	return new ResponseEntity<RoleDTO>(obj, httpStatus);
    }
    
    @RequestMapping(value="/roles/{id}", method=RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')")
    public ResponseEntity<RoleDTO> deleteRole(@PathVariable long id) {

    	HttpStatus httpStatus = HttpStatus.OK;
    	
    	RoleDTO obj = roleService.deleteRole(id);
    	
    	if (obj == null) {
    		httpStatus = HttpStatus.NOT_FOUND;
    	}
    	
    	return new ResponseEntity<RoleDTO>(obj, httpStatus);
    }
}
