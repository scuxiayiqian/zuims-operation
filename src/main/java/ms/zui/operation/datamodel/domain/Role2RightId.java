package ms.zui.operation.datamodel.domain;

import java.io.Serializable;

public class Role2RightId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3645041767385966179L;
	
	private long roleId;
	private long rightId;
	
	public Role2RightId() {
		
	}
	
	public long getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
	public long getRightId() {
		return this.rightId;
	}
	
	public void setRightId(long rightId) {
		this.rightId = rightId;
	}
}
