package ms.zui.operation.datamodel.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="role")
public class Role {
	
	@Id
	@Column(name="id")
	@GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")	
	private long id;
	
	@NotNull
	@Column(name="name")
	private String name;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="role2authorization", 
		joinColumns={@JoinColumn(name="roleid", referencedColumnName="id")},
		inverseJoinColumns={@JoinColumn(name="rightid", referencedColumnName="id")}
	)
	private List<Right> rights;
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

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
	
	public String toAuthorizationName() {
		
		return "ROLE_" + this.name.toUpperCase();
	}
}
