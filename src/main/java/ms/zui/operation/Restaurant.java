package ms.zui.operation;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;;

public class Restaurant {

	private String name;
	private boolean isPromoted ;
	
	@JsonCreator
	public Restaurant(@JsonProperty("name") String name, @JsonProperty("isPromoted") boolean isPromoted){
		this.name = name;
		this.isPromoted = isPromoted;
	}

	public String getName(){
		return this.name;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public boolean getIsPromoted() {
		return this.isPromoted;
	}
	
	public void setIsPromoted(boolean value) {
		this.isPromoted = value;
	}		
}
