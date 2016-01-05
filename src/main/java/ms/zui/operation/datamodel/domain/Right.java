package ms.zui.operation.datamodel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="authorization")
public class Right {

	@Id
	@NotNull
	@Column(name="name")
	private String name;
	
	@Column(name="parent")
	private String parent;
			
	public String getName() {
		return this.name;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getParent() {
		return this.parent;
	}
	
	public void setParent(String value) {
		this.parent = value;
	}
}
