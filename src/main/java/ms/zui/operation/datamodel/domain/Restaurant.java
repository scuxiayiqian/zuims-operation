package ms.zui.operation.datamodel.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;;

public class Restaurant {

	private String name;
	private boolean isPromoted ;
	private String marketingName;
	
	@JsonCreator
	public Restaurant(@JsonProperty("name") String name, @JsonProperty("isPromoted") boolean isPromoted, @JsonProperty("marketingName") String marketingName){
		this.name = name;
		this.isPromoted = isPromoted;
		this.marketingName = marketingName;
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

	public String getMarketingName(){
		return this.marketingName;
	}
	
	public void setMarketingName(String value) {
		this.marketingName = value;
	}
}
