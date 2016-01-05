package ms.zui.operation.datamodel.dto;

public class RightDTO {

	private String name;	
	private String parent;
	
	public RightDTO() {
		
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
		if(value == null){
			this.parent = "";
		}
		else {
			
			this.parent = value;
		}
	}
}
