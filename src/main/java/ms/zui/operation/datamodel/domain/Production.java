package ms.zui.operation.datamodel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="production")
public class Production {
	
	@Id
	@Column(name="id")
	@GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")	
	private long id;

	@NotNull
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	public Production() {
	}

	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}

}
