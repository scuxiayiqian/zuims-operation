package ms.zui.operation.datamodel.domain;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;;

public class Restaurant {

	private String name;
	private String city;
	private boolean isPromoted ;
	private String marketingName;
	private Collection<Production> productions;
	
	@JsonCreator
	public Restaurant(@JsonProperty("name") String name, @JsonProperty("city") String city, @JsonProperty("isPromoted") boolean isPromoted, @JsonProperty("marketingName") String marketingName, @JsonProperty("productions") Collection<Production> productions){
		this.name = name;
		this.city = city;
		this.isPromoted = isPromoted;
		this.marketingName = marketingName;
		this.productions = productions;
	}

	public String getName(){
		return this.name;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public void setCity(String value) {
		this.city = value;
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
	
	public Collection<Production> getProductions() {
		return this.productions;
	}
	
	public void setProductions(Collection<Production> productions) {
		this.productions = productions;
	}
}
