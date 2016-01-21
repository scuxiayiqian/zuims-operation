package ms.zui.operation.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ms.zui.operation.datamodel.dao.UserRepository;
import ms.zui.operation.datamodel.domain.User;
import ms.zui.operation.datamodel.dto.UserDTO;

@Service
public class ConvertTo {
	
	@Autowired
	UserRepository userRepository;
	
	public UserDTO convertToUserDTO(User user) {
		
		if(user == null) {
			return null;
		}
		
		UserDTO userDTO = new UserDTO();
		
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setFullname(user.getFullname());
		userDTO.setEmail(user.getEmail());
		userDTO.setMobile(user.getMobile());
		userDTO.setQq(user.getQq());
		userDTO.setWx(user.getWx());
		userDTO.setRoles(user.getRoles());
				
		return userDTO;
	}
	
	public User convertToUser(UserDTO userDTO) {

		User user = userRepository.findOne(userDTO.getId());
		
		user.setName(userDTO.getName());
		user.setFullname(userDTO.getFullname());
		user.setEmail(userDTO.getEmail());
		user.setMobile(userDTO.getMobile());
		user.setQq(userDTO.getQq());
		user.setWx(userDTO.getWx());
		user.setRoles(userDTO.getRoles());

		return user;
	}
}
