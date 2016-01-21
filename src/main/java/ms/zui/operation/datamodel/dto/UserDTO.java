package ms.zui.operation.datamodel.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ms.zui.operation.datamodel.domain.Role;

public class UserDTO {
	
	private long id;
	private String name;
	private String mobile;
	private String fullname;
	private String wx;
	private String qq;
	private String email;
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
	
	public String getFullname(){
		return this.fullname;
	}
	
	public void setFullname(String value) {
		this.fullname = value;
	}

	public String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(String value) {
		this.mobile = value;
	}	
	
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getQq(){
		return this.qq;
	}
	
	public void setQq(String value) {
		this.qq = value;
	}
	
	public String getWx(){
		return this.wx;
	}
	
	public void setWx(String value) {
		this.wx = value;
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
