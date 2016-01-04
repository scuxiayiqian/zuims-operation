package ms.zui.operation.datamodel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="authorization")
public class Right {

	@Id
	@NotNull
	@Column(name="id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	private long id;

	@NotNull
	@Column(name="name")
	private String name;
	
	@Column(name="parent")
	private long parent;
		
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public long getParent() {
		return this.parent;
	}
	
	public void setParent(long value) {
		this.parent = value;
	}
	
}
