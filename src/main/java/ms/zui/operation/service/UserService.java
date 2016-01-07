package ms.zui.operation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ms.zui.operation.datamodel.dao.UserRepository;
import ms.zui.operation.datamodel.domain.Role;
import ms.zui.operation.datamodel.domain.User;
import ms.zui.operation.datamodel.dto.RoleDTO;
import ms.zui.operation.datamodel.dto.UserDTO;
import ms.zui.operation.util.ConvertTo;

import java.util.ArrayList;
import java.util.Date;

@Service
public class UserService extends BaseService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleService roleService;
	
	public List<UserDTO> getAllUsers() {
		
		List<UserDTO> users = new ArrayList<UserDTO>();
		
		for(User user: this.userRepository.findAll()) {
			users.add(ConvertTo.convertToUserDTO(user));
		}
		return users;
	}
	
	public UserDTO getUserById(long id) {
		
		User user = this.userRepository.findOne(id);
		
		return ConvertTo.convertToUserDTO(user);
	}

	public UserDTO getUserDTOByName(String name) {
		
		UserDTO userDTO = null;
		
		System.out.println("getUserDTOByName - start: " + new Date().getTime());
		
		for(User user: userRepository.findByName(name)) {
			
			System.out.println("getUserDTOByName - findByName: " + new Date().getTime());

			if(name.equals(user.getName())) {
				userDTO = ConvertTo.convertToUserDTO(user);
		
				break;
			}
		}
		
		System.out.println("getUserDTOByName - getRolesByUserId: " + new Date().getTime());

		return userDTO;
	}
	
	public User getUserByName(String name) {
		
		User result = null;
		
		for(User user: userRepository.findByName(name)) {
			
			if(name.equals(user.getName())) {
				
				result = user;
				
				break;
			}
		}

		return result;
	}
	
	
	public List<UserDTO> getUsersByRoleName(String roleName) {
		
		List<User> users = this.userRepository.findByRoleName(roleName);
		
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		
		for(User user: users) {
			
			userDTOs.add(ConvertTo.convertToUserDTO(user));
		}
		return userDTOs;
	}
	
	public UserDTO createUser(UserDTO userDTO) {
		
		User newUser = this.userRepository.save(ConvertTo.convertToUser(userDTO));
				
		return ConvertTo.convertToUserDTO(newUser);
	}
	
	public UserDTO updateUser(UserDTO userDTO) {
		
		User newUser = this.userRepository.save(ConvertTo.convertToUser(userDTO));
		
		return ConvertTo.convertToUserDTO(newUser);
	}

	public UserDTO deleteUser(long id) {
		
		User deletedUser = userRepository.findOne(id);
		
		if(deletedUser == null) {
			return null;
		}
		
		UserDTO deletedUserDTO = ConvertTo.convertToUserDTO(deletedUser);
		
		userRepository.delete(id);

		return deletedUserDTO;		

	}
}
