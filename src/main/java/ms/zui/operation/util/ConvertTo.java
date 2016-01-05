package ms.zui.operation.util;

import java.util.ArrayList;
import java.util.List;

import ms.zui.operation.datamodel.domain.Right;
import ms.zui.operation.datamodel.domain.Role;
import ms.zui.operation.datamodel.domain.User;
import ms.zui.operation.datamodel.dto.RightDTO;
import ms.zui.operation.datamodel.dto.RoleDTO;
import ms.zui.operation.datamodel.dto.UserDTO;

public class ConvertTo {
	
	public static RightDTO convertToRightDTO(Right right) {
		
		if(right == null) {
			return null;
		}
		
		RightDTO rightDTO = new RightDTO();
		
		rightDTO.setName(right.getName());
		rightDTO.setParent(right.getParent());

		return rightDTO;
	}
	
	public static Right convertToRight(RightDTO rightDTO) {
		
		if(rightDTO == null) {
			return null;
		}
		
		Right right = new Right();
		
		right.setName(rightDTO.getName());
		right.setParent(rightDTO.getParent());
		
		return right;
	}

	public static RoleDTO convertToRoleDTO(Role role, List<RightDTO> rightDTOs) {
		
		if(role == null) {
			return null;
		}
		
		RoleDTO roleDTO = new RoleDTO();
		
		roleDTO.setName(role.getName());
		roleDTO.setRights(rightDTOs);
				
		return roleDTO;
	}
	
	public static Role convertToRole(RoleDTO roleDTO) {

		if(roleDTO == null) {
			return null;
		}
		
		Role role = new Role();
		
		role.setName(roleDTO.getName());
		
		return role;
	}

	public static UserDTO convertToUserDTO(User user, List<RoleDTO> roleDTOs) {
		
		if(user == null) {
			return null;
		}
		
		UserDTO userDTO = new UserDTO();
		
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setPassword(user.getPassword());
		userDTO.setRoles(roleDTOs);
				
		return userDTO;
	}
	
	public static User convertToUser(UserDTO userDTO) {
		User user = new User();
		
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());

		return user;
	}
}
