package ms.zui.operation.datamodel.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Production {
	
	private String name;
	private String description;
	
	@JsonCreator
	public Production(@JsonProperty("name") String name, @JsonProperty("description") String description) {
		this.name = name;
		this.description = description;
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
