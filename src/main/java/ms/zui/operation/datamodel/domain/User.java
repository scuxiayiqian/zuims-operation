package ms.zui.operation.datamodel.domain;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public class User {
	
	private String name;
	private String password;
	
	/*
	 * Roles: admin, manager, marketing
	 */
	private Collection<Role> roles;
	
	@JsonCreator
	public User(@JsonProperty("name") String name, @JsonProperty("password") String password, @JsonProperty("roles")  Collection<Role> roles){
		this.name = name;
		this.password = password;
		this.roles = roles;
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
	
	public Collection<Role> getRoles() {
		return this.roles;
	}
	
	//@JsonDeserialize(using = UserRolesDeserializer.class)
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	@JsonIgnore
	public boolean isAdmin() {
		boolean isAdmin = false;
		
		for (Role role: roles) {
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
