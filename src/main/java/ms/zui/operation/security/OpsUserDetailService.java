package ms.zui.operation.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ms.zui.operation.datamodel.dto.UserDTO;
import ms.zui.operation.service.UserService;


@Component
public class OpsUserDetailService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDTO userDTO = userService.getUserByName(username);
		
		if(userDTO == null) {
			throw new UsernameNotFoundException(String.format("User with the username %s doesn't exist", username));
		}
		
		// Create a granted authority based on user's role. 
		// Can't pass null authorities to user. Hence initialize with an empty arraylist
		List<GrantedAuthority> authorities = new ArrayList<>();

		authorities = AuthorityUtils.createAuthorityList(userDTO.getAuthorities());
		
		// Create a UserDetails object from the data 
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(userDTO.getName(), userDTO.getPassword(), authorities);
		
		return userDetails;
	}
}
