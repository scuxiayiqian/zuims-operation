package ms.zui.operation.datamodel.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;;

public class Right {
	
	private String name;
	//private String url;
	private String parent;
	
	@JsonCreator
	public Right(@JsonProperty("name") String name, @JsonProperty("parent") String parent) {
		this.name = name;
		this.parent = parent;
	}
	
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
