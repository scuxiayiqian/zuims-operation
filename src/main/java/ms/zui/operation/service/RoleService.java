package ms.zui.operation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ms.zui.operation.datamodel.dao.RightRepository;
import ms.zui.operation.datamodel.dao.RoleRepository;
import ms.zui.operation.datamodel.domain.Role;

@Service
public class RoleService extends BaseService{

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	RightRepository rightRepository;
	
	public Role getRoleByName(long id) {
		
		return roleRepository.findOne(id);
	}
	
	public List<Role> getAllRoles() {
		
		return (List<Role>) this.roleRepository.findAll();
	}
	
	public Role createRole(Role role) {
		
		return this.roleRepository.save(role);
	}
	
	public Role updateRole(Role role) {
		
		return this.roleRepository.save(role);
	}
	
	public Role deleteRole(long id) {
		
		Role deletedRole = roleRepository.findOne(id);
				
		roleRepository.delete(id);

		return deletedRole;		
	}
}
