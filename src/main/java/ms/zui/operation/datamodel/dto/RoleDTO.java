package ms.zui.operation.datamodel.dto;

import java.util.List;

import ms.zui.operation.datamodel.domain.Right;

public class RoleDTO {

	private String name;
	private List<RightDTO> rights;
	
	public RoleDTO() {
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<RightDTO> getRights() {
		return this.rights;
	}
	
	public void setRights(List<RightDTO> rights) {
		this.rights = rights;
	}
		
	public String toAuthorizationName() {
		
		return "ROLE_" + this.name.toUpperCase();
	}
}
