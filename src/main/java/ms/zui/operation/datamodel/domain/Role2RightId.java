package ms.zui.operation.datamodel.domain;

import java.io.Serializable;

public class Role2RightId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3645041767385966179L;
	
	private String roleName;
	private String rightName;
	
	public Role2RightId() {
		
	}
	
	public String getRoleName() {
		return this.roleName;
	}
	
	public void setRoleName(String roleId) {
		this.roleName = roleId;
	}
	
	public String getRightName() {
		return this.rightName;
	}
	
	public void setRightName(String rightId) {
		this.rightName = rightId;
	}
}
