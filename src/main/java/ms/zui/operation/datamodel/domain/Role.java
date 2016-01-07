package ms.zui.operation.datamodel.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="role")
public class Role {
	
	@Id
	@NotNull
	@Column(name="name")
	private String name;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="role2authorization", 
		joinColumns={@JoinColumn(name="roleName", referencedColumnName="name")},
		inverseJoinColumns={@JoinColumn(name="rightName", referencedColumnName="name")}
	)
	private List<Right> rights;
	
	public List<Right> getRights() {
		return this.rights;
	}
	
	public void setRights(List<Right> rights) {
		this.rights = rights;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String value) {
		this.name = value;
	}
}
