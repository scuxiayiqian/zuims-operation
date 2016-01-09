package ms.zui.operation.datamodel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="authorization")
public class Right {

	@Id
	@Column(name="id")
	@GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")	
	private long id;
	
	@NotNull
	@Column(name="name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="parent")
	private Right parent;
	
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
	
	public Right getParent() {
		return this.parent;
	}
	
	public void setParent(Right value) {
		this.parent = value;
	}
}
