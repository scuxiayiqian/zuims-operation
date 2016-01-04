package ms.zui.operation.datamodel.domain;

import java.io.Serializable;

public class User2RoleId implements Serializable {

	private static final long serialVersionUID = 139633638952001976L;
	
	private long roleId;
	private long userId;
	
	public User2RoleId() {
		
	}
	
	public long getRoleId() {
		return roleId;
	}
	
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
	public long getUserId() {
		return this.userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}

}
