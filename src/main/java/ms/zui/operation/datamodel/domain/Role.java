package ms.zui.operation.datamodel.domain;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Role {
	
	private String name;
	private Collection<Right> rights;
	
	@JsonCreator
	public Role(@JsonProperty("name") String name, @JsonProperty("rights") Collection<Right> rights) {
		this.name = name;
		this.rights = rights;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public Collection<Right> getRights() {
		return this.rights;
	}
	
	public void setRights(Collection<Right> value) {
		this.rights = value;
	}
	
	public String toAuthorizationName() {
		
		return "ROLE_" + this.name.toUpperCase();
	}
}
