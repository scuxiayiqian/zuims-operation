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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@NotNull
	@Column(name="fullname")
	private String fullname = "";

	@Column(name="mobile")
	private String mobile;
	
	@Column(name="qq")
	private String qq;

	@Column(name="wx")
	private String wx;
	
	@Column(name="email")
	private String email;

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
	
	public String getFullname(){
		return this.fullname;
	}
	
	public void setFullname(String value) {
		this.fullname = value;
	}

	public String getMobile(){
		return this.mobile;
	}
	
	public void setMobile(String value) {
		this.mobile = value;
	}

	public String getQq(){
		return this.qq;
	}
	
	public void setQq(String value) {
		this.qq = value;
	}

	public String getWx(){
		return this.wx;
	}
	
	public void setWx(String value) {
		this.wx = value;
	}

	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}

	public List<Role> getRoles() {
		return this.roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	@JsonIgnore
	public String[] getAuthorities() {
		
		String[] authorities = new String[roles.size()];
		
		int nIndex = 0;
		for (Role role: roles) {
			authorities[nIndex] = role.toAuthorizationName();
			nIndex ++;
		}
		
		return authorities;
	}
}
