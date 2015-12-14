package ms.zui.operation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;;


public class User {
	
	private String name;
	private String password;
	private String role;
	
	@JsonCreator
	public User(@JsonProperty("name") String name, @JsonProperty("password") String password, @JsonProperty("role") String role){
		this.name = name;
		this.password = password;
		this.role = role;
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
	
	public String getRole() {
		return role;
	}

	public void setRole(String value) {
		this.role = value;
	}
	
	@JsonIgnore
	public boolean isAdmin() {
		return role.compareTo("admin") == 0 ? true : false;
	}
}
