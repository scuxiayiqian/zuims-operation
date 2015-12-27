package ms.zui.operation.service;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import ms.zui.operation.Application;
import ms.zui.operation.datamodel.domain.Role;;

public class RoleService {

	private HashMap<String, Role> 	repoRole;	
	private ObjectMapper mapper = new ObjectMapper();
	
	public RoleService() {
		
		Role[] rights = null;
		
		try {
			rights = mapper.readValue(new File(Application.dataPath + "role.json"), Role[].class);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		repoRole = new HashMap<String, Role>();
		
		for (int nIndex = 0; nIndex < rights.length; nIndex++) {
			repoRole.put(rights[nIndex].getName(), rights[nIndex]);
		}
	}
	
	public Role getRoleByName(String name) {
		
		return repoRole.get(name);
	}
	
	public Collection<Role> getAllRoles() {
		return repoRole.values();
	}
	
	public Role createRole(Role role) {
		
		repoRole.put(role.getName(), role);
		
	   	try {
	   		mapper.writeValue(new File(Application.dataPath + "role.json"), repoRole.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return role;
	}
	
	public Role updateRole(Role role) {
		
		Role returnRole = repoRole.put(role.getName(), role);
		
	   	try {
        	mapper.writeValue(new File(Application.dataPath + "role.json"), repoRole.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return returnRole;
	}

	public Role deleteRole(String name) {
		
		Role returnRole = repoRole.remove(name);
		
	   	try {
        	mapper.writeValue(new File(Application.dataPath + "role.json"), repoRole.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return returnRole;
	}
}
