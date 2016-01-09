package ms.zui.operation.datamodel.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="account")
public class User {
	
	@Id
	@NotNull
	@Column(name="id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	private long id;
	
	@NotNull
	@Column(name="name")
	private String name;

	@NotNull
	@Column(name="password")
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="account2role", 
		joinColumns={@JoinColumn(name="accountid", referencedColumnName="id")},
		inverseJoinColumns={@JoinColumn(name="roleid", referencedColumnName="id")}
	)
	private List<Role> roles;
	
	public User() {
		
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}	
	
	public List<Role> getRoles() {
		return this.roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
