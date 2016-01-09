package ms.zui.operation.datamodel.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ms.zui.operation.datamodel.domain.Role;

public class UserDTO {
	
	private long id;
	private String name;
	private String password;
	
	private List<Role> roles;
	
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
	
	public List<Role> getRoles() {
		return this.roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	@JsonIgnore
	public String[] getAuthorities() {
		
		String[] authorities = new String[roles.size()];
		
		int nIndex = 0;
		for (Role role: roles) {
			authorities[nIndex] = role.toAuthorizationName();
			nIndex ++;
		}
		
		return authorities;
	}
	
	@JsonIgnore
	public boolean hasRole(String role) {
		
		boolean flag = false;
		
		for (Role r: roles) {
			if(role.equals(r.getName())) {
				flag = true;
				break;
			}
		}
		
		return flag;
	}
}
