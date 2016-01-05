package ms.zui.operation.datamodel.domain;

import java.io.Serializable;

public class User2RoleId implements Serializable {

	private static final long serialVersionUID = 139633638952001976L;
	
	private String roleName;
	private long userId;
	
	public User2RoleId() {
		
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public long getUserId() {
		return this.userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}

}
