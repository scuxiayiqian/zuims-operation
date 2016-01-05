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
	@Column(name="roleName")
	private String roleName;
	
	@Id
	@NotNull
	@Column(name="rightName")
	private String rightName;
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getRightName() {
		return this.rightName;
	}
	
	public void setRightName(String rightName) {
		this.rightName = rightName;
	}
	
}
