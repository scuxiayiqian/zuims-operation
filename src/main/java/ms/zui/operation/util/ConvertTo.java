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
	
	public static RightDTO convertToRightDTO(Right right, Right  parent) {
		RightDTO rightDTO = new RightDTO();
		
		rightDTO.setId(right.getId());
		rightDTO.setName(right.getName());
		
		if(parent != null) {
			rightDTO.setParent(parent.getName());
		}
		else {
			
			rightDTO.setParent("");
		}
		
		return rightDTO;
	}
	
	public static Right convertToRight(RightDTO rightDTO, RightDTO parent) {
		Right right = new Right();
		
		right.setId(rightDTO.getId());
		right.setName(rightDTO.getName());
		
		if(parent != null) {
			
			right.setParent(parent.getId());
		}
		else {
			right.setParent(-1);
		}
		
		return right;
	}

	public static RoleDTO convertToRoleDTO(Role role, List<RightDTO> rightDTOs) {
		
		if(role == null) {
			return null;
		}
		
		RoleDTO roleDTO = new RoleDTO();
		
		roleDTO.setId(role.getId());
		roleDTO.setName(role.getName());
		roleDTO.setRights(rightDTOs);
				
		return roleDTO;
	}
	
	public static Role convertToRole(RoleDTO roleDTO) {
		Role role = new Role();
		
		role.setId(roleDTO.getId());
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
