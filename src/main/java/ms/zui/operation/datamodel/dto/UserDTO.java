package ms.zui.operation.datamodel.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {
	
	private long id;
	private String name;
	private String password;
	
	/*
	 * Roles: admin, manager, marketing
	 */
	private List<RoleDTO> roles;
	
	public UserDTO(){
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}	
	
	public List<RoleDTO> getRoles() {
		return this.roles;
	}
	
	//@JsonDeserialize(using = UserRolesDeserializer.class)
	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}
	
	@JsonIgnore
	public boolean isAdmin() {
		boolean isAdmin = false;
		
		for (RoleDTO role: roles) {
			if (role.getName().equals("admin")) {
				isAdmin = true;
			}
		}
		return isAdmin;
	}
	
	@JsonIgnore
	public String[] getAuthorities() {
		
		String[] authorities = new String[roles.size()];
		
		int nIndex = 0;
		for (RoleDTO role: roles) {
			authorities[nIndex] = role.toAuthorizationName();
			nIndex ++;
		}
		
		return authorities;
	}
	
	@JsonIgnore
	public boolean hasRole(String role) {
		
		boolean flag = false;
		
		for (RoleDTO r: roles) {
			if(role.equals(r.getName())) {
				flag = true;
				break;
			}
		}
		
		return flag;
	}
}
