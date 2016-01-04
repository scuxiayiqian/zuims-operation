package ms.zui.operation.datamodel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="account2role")
@IdClass(User2RoleId.class)
public class User2Role {

	@Id
	@NotNull
	@Column(name="roleid")
	private long roleId;
	
	@Id
	@NotNull
	@Column(name="accountid")
	private long userId;
	
	
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
