package ms.zui.operation.controller.parameter;

public class ChangePasswordParameter {

	private String oldPassword;
	private String newPassword;
	
	public String getOldPassword() {
		return this.oldPassword;
	}
	
	public void setOldPassword(String value) {
		this.oldPassword = value;
	}

	public String getNewPassword() {
		return this.newPassword;
	}
	
	public void setNewPassword(String value) {
		this.newPassword = value;
	}
}
