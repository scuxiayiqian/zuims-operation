package ms.zui.operation.datamodel.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;;


public class Guest {
	
	private String name;
	private String password;
	private String mobile;
	private boolean gender;
	
	@JsonCreator
	public Guest(@JsonProperty("name") String name, @JsonProperty("password") String password, @JsonProperty("mobile") String mobile, @JsonProperty("gender") boolean gender){
		this.name = name;
		this.password = password;
		this.mobile = mobile;
		this.gender = gender;
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
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String value) {
		this.mobile = value;
	}	

	public boolean getGender() {
		return gender;
	}
	
	public void setGender(boolean value) {
		this.gender = value;
	}	

}
