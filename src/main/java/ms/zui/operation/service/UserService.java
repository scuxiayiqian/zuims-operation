package ms.zui.operation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ms.zui.operation.datamodel.dao.UserRepository;
import ms.zui.operation.datamodel.domain.User;
import ms.zui.operation.datamodel.dto.UserDTO;
import ms.zui.operation.util.ConvertTo;

import java.util.ArrayList;

@Service
public class UserService extends BaseService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	ConvertTo converter;
	
	public List<UserDTO> getAllUsers() {
		
		List<UserDTO> users = new ArrayList<UserDTO>();
		
		for(User user: this.userRepository.findAll()) {
			users.add(converter.convertToUserDTO(user));
		}
		return users;
	}
	
	public UserDTO getUserById(long id) {
		
		User user = this.userRepository.findOne(id);
		
		return converter.convertToUserDTO(user);
	}
	
	public UserDTO getUserDTOByName(String name) {
		
		UserDTO userDTO = null;
		
		for(User user: userRepository.findByName(name)) {
			
			if(name.equals(user.getName())) {
				userDTO = converter.convertToUserDTO(user);
		
				break;
			}
		}

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
			
			userDTOs.add(converter.convertToUserDTO(user));
		}
		return userDTOs;
	}
	
	public UserDTO createUser(UserDTO userDTO) {
		
		User newUser = this.userRepository.save(converter.convertToUser(userDTO));
				
		return converter.convertToUserDTO(newUser);
	}
	
	public UserDTO updateUser(UserDTO userDTO) {
		
		User newUser = this.userRepository.save(converter.convertToUser(userDTO));
		
		return converter.convertToUserDTO(newUser);
	}

	public boolean changePassword(long id, String oldPassword, String newPassword) {
		
		User user = userRepository.findOne(id);
		
		if (oldPassword.equals(user.getPassword())) {
			
			user.setPassword(newPassword);
			
			user = userRepository.save(user);
		}
		else {
			user = null;
		}
		
		return user != null;
	}
	
	public UserDTO deleteUser(long id) {
		
		User deletedUser = userRepository.findOne(id);
		
		if(deletedUser == null) {
			return null;
		}
		
		UserDTO deletedUserDTO = converter.convertToUserDTO(deletedUser);
		
		userRepository.delete(id);

		return deletedUserDTO;		

	}
}
