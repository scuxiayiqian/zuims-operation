package ms.zui.operation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ms.zui.operation.datamodel.dao.RightRepository;
import ms.zui.operation.datamodel.dao.RoleRepository;
import ms.zui.operation.datamodel.domain.Role;
import ms.zui.operation.datamodel.dto.RoleDTO;
import ms.zui.operation.util.ConvertTo;;

@Service
public class RoleService extends BaseService{

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	RightRepository rightRepository;
	
	public RoleDTO getRoleByName(String name) {
		
		Role role = roleRepository.findOne(name);
		
		return ConvertTo.convertToRoleDTO(role);
	}
	
	public List<RoleDTO> getAllRoles() {
		
		List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
		
		for(Role role: roleRepository.findAll()) {
			
			roleDTOs.add(ConvertTo.convertToRoleDTO(role));
		}
		
		return roleDTOs;
	}
	
	public RoleDTO createRole(RoleDTO roleDTO) {
		
		Role newRole = roleRepository.save(ConvertTo.convertToRole(roleDTO));
				
		return ConvertTo.convertToRoleDTO(newRole);
	}
	
	public RoleDTO updateRole(RoleDTO roleDTO) {
		
		Role newRole = roleRepository.save(ConvertTo.convertToRole(roleDTO));
				
		return ConvertTo.convertToRoleDTO(newRole);
	}
	
	public RoleDTO deleteRole(String name) {
		
		Role deletedRole = roleRepository.findOne(name);
		RoleDTO deletedRoleDTO = ConvertTo.convertToRoleDTO(deletedRole);
		
		roleRepository.delete(name);

		return deletedRoleDTO;		
	}
}
