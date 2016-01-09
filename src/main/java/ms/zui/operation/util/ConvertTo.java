package ms.zui.operation.util;

import ms.zui.operation.datamodel.domain.User;
import ms.zui.operation.datamodel.dto.UserDTO;

public class ConvertTo {
	
	public static UserDTO convertToUserDTO(User user) {
		
		if(user == null) {
			return null;
		}
		
		UserDTO userDTO = new UserDTO();
		
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setPassword(user.getPassword());
		userDTO.setRoles(user.getRoles());
				
		return userDTO;
	}
	
	public static User convertToUser(UserDTO userDTO) {
		User user = new User();
		
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		user.setRoles(userDTO.getRoles());

		return user;
	}
}
