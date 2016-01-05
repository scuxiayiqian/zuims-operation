package ms.zui.operation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ms.zui.operation.datamodel.dao.User2RoleRepository;
import ms.zui.operation.datamodel.dao.UserRepository;
import ms.zui.operation.datamodel.domain.Role;
import ms.zui.operation.datamodel.domain.User;
import ms.zui.operation.datamodel.domain.User2Role;
import ms.zui.operation.datamodel.dto.RoleDTO;
import ms.zui.operation.datamodel.dto.UserDTO;
import ms.zui.operation.util.ConvertTo;

import java.util.ArrayList;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	User2RoleRepository user2RoleRepository;
		
	private List<RoleDTO> getRolesByUserId(long id) {
		List<RoleDTO> roles = new ArrayList<RoleDTO>();
		
		List<User2Role> user2Roles = this.user2RoleRepository.findByUserId(id);
		
		for(User2Role u: user2Roles) {
			roles.add(this.roleService.getRoleById(u.getRoleId()));
		}
		
		return roles;
	}
	
	public List<UserDTO> getAllUsers() {
		
		List<UserDTO> users = new ArrayList<UserDTO>();
		
		for(User user: this.userRepository.findAll()) {
			users.add(ConvertTo.convertToUserDTO(user, getRolesByUserId(user.getId())));
		}
		return users;
	}
	
	public UserDTO getUserById(long id) {
		
		User user = this.userRepository.findOne(id);
		
		return ConvertTo.convertToUserDTO(user, getRolesByUserId(user.getId()));
	}

	public UserDTO getUserByName(String name) {
		
		UserDTO userDTO = null;
		
		for(User user: userRepository.findByName(name)) {
			
			if(name.equals(user.getName())) {
				userDTO = ConvertTo.convertToUserDTO(user, getRolesByUserId(user.getId()));
				
				break;
			}
		}

		return userDTO;
	}
	
	public List<UserDTO> getUsersByRoleName(String roleName) {
		
		Role role = this.roleService.getRoleByName(roleName);
		
		List<UserDTO> users = new ArrayList<UserDTO>();
		
		if(role == null) {
			return users;
		}
		
		List<User2Role> user2Roles = this.user2RoleRepository.findByRoleId(role.getId());
		
		for(User2Role user2Role: user2Roles) {
			
			users.add(ConvertTo.convertToUserDTO(this.userRepository.findOne(user2Role.getUserId()), getRolesByUserId(user2Role.getUserId())));
		}
		return users;
	}
	
	public UserDTO createUser(UserDTO userDTO) {
		
		User newUser = this.userRepository.save(ConvertTo.convertToUser(userDTO));
		
		for(RoleDTO roleDTO: userDTO.getRoles()) {
			
			User2Role user2Role = new User2Role();
			
			user2Role.setUserId(newUser.getId());
			user2Role.setRoleId(roleDTO.getId());
			
			this.user2RoleRepository.save(user2Role);
		}
		
		return ConvertTo.convertToUserDTO(newUser, userDTO.getRoles());
	}
	
	public UserDTO updateUser(UserDTO userDTO) {
		
		User newUser = this.userRepository.save(ConvertTo.convertToUser(userDTO));
		
		this.user2RoleRepository.deleteByUserId(userDTO.getId());
		
		for(RoleDTO roleDTO: userDTO.getRoles()) {
			
			User2Role user2Role = new User2Role();
			
			user2Role.setUserId(newUser.getId());
			user2Role.setRoleId(roleDTO.getId());
			
			this.user2RoleRepository.save(user2Role);
		}
		
		return ConvertTo.convertToUserDTO(newUser, userDTO.getRoles());
	}

	public UserDTO deleteUser(long id) {
		
		User deletedUser = userRepository.findOne(id);
		
		if(deletedUser == null) {
			return null;
		}
		
		UserDTO deletedUserDTO = ConvertTo.convertToUserDTO(deletedUser, getRolesByUserId(id));
		
		userRepository.delete(id);

		this.user2RoleRepository.deleteByUserId(id);

		return deletedUserDTO;		

	}
}
