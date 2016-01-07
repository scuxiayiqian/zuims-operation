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

	public static RoleDTO convertToRoleDTO(Role role) {
		
		if(role == null) {
			return null;
		}
		
		RoleDTO roleDTO = new RoleDTO();
		
		roleDTO.setName(role.getName());
		
		ArrayList<RightDTO> rightDTOs = new ArrayList<RightDTO>();
		
		for(Right right: role.getRights()){
			
			rightDTOs.add(convertToRightDTO(right));
		}
		
		roleDTO.setRights(rightDTOs);
				
		return roleDTO;
	}
	
	public static Role convertToRole(RoleDTO roleDTO) {

		if(roleDTO == null) {
			return null;
		}
		
		Role role = new Role();
		
		role.setName(roleDTO.getName());
		ArrayList<Right> rights = new ArrayList<Right>();
		
		for(RightDTO rightDTO: roleDTO.getRights()){
			
			rights.add(convertToRight(rightDTO));
		}
		
		role.setRights(rights);
		
		return role;
	}

	public static UserDTO convertToUserDTO(User user) {
		
		if(user == null) {
			return null;
		}
		
		UserDTO userDTO = new UserDTO();
		
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setPassword(user.getPassword());
		
		ArrayList<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
		
		for(Role role: user.getRoles()){
			
			roleDTOs.add(convertToRoleDTO(role));
		}
		
		userDTO.setRoles(roleDTOs);
				
		return userDTO;
	}
	
	public static User convertToUser(UserDTO userDTO) {
		User user = new User();
		
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());

		ArrayList<Role> roles = new ArrayList<Role>();
		
		for(RoleDTO roleDTO: userDTO.getRoles()){
			
			roles.add(convertToRole(roleDTO));
		}
		
		user.setRoles(roles);

		return user;
	}
}
