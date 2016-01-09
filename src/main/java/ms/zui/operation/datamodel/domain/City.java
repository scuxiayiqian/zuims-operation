package ms.zui.operation.datamodel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="city")
public class City {
	
	@Id
	@NotNull
	@Column(name="name")
	private String name;
	
	@Column(name="longitude")	
	private double longitude;
	
	@Column(name="latitude")
	private double latitude;
	
	public City() {
		this.name = "";
		this.longitude = 0.0;
		this.latitude = 0.0;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public double getLongitude() {
		return this.longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return this.latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
