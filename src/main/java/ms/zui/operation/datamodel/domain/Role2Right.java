package ms.zui.operation.datamodel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="role2authorization")
@IdClass(Role2RightId.class)
public class Role2Right {

	@Id
	@NotNull
	@Column(name="roleid")
	private long roleId;
	
	@Id
	@NotNull
	@Column(name="rightid")
	private long rightId;
	
	public long getRoleId() {
		return roleId;
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
