package ms.zui.operation.datamodel.domain;

import org.springframework.security.core.authority.AuthorityUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;;

public class Role {
	
	private String name;
	
	@JsonCreator
	public Role(@JsonProperty("name") String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String AuthorizationName() {
		
		switch (this.name) {
		case "admin": 
			return "ROLE_ADMIN";
		case "manager":
			return "ROLE_MANAGER";
		case "marketing":
			return "ROLE_MARKETING";
		default:
			return "ROLE_MARKETING";
		}
	}
}
