package ms.zui.operation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ms.zui.operation.datamodel.dao.RightRepository;
import ms.zui.operation.datamodel.dao.Role2RightRepository;
import ms.zui.operation.datamodel.dao.RoleRepository;
import ms.zui.operation.datamodel.domain.Right;
import ms.zui.operation.datamodel.domain.Role;
import ms.zui.operation.datamodel.domain.Role2Right;
import ms.zui.operation.datamodel.dto.RightDTO;
import ms.zui.operation.datamodel.dto.RoleDTO;
import ms.zui.operation.util.ConvertTo;;

@Service
public class RoleService extends BaseService{

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	Role2RightRepository role2RightRepository;
	
	@Autowired
	RightRepository rightRepository;
	
	private List<RightDTO> getRightsByRoleName(String roleName) {
		
		List<RightDTO> rightDTOs = new ArrayList<RightDTO>();
		
		List<Role2Right> role2Rights = this.role2RightRepository.findByRoleName(roleName);
		
		for(Role2Right role2Right: role2Rights) {
			Right right = this.rightRepository.findOne(role2Right.getRightName());
				
			rightDTOs.add(ConvertTo.convertToRightDTO(right));
		}
		
		return rightDTOs;
	}
	
	public RoleDTO getRoleByName(String name) {
		
//		List<Role> roles = this.roleRepository.findByName(name);
//		
//		Role role = null;
//		
//		if(!roles.isEmpty()) {
//			role = roles.get(0);
//		}
		
		Role role = roleRepository.findOne(name);
		
		return ConvertTo.convertToRoleDTO(role, getRightsByRoleName(name));
	}
	
	public List<RoleDTO> getAllRoles() {
		
		List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
		
		for(Role role: roleRepository.findAll()) {
			
			roleDTOs.add(ConvertTo.convertToRoleDTO(role, getRightsByRoleName(role.getName())));
		}
		
		return roleDTOs;
	}
	
	public RoleDTO createRole(RoleDTO roleDTO) {
		
		Role newRole = roleRepository.save(ConvertTo.convertToRole(roleDTO));
		
		for(RightDTO rightDTO: roleDTO.getRights()) {
			
			Role2Right role2Right = new Role2Right();
			
			role2Right.setRoleName(newRole.getName());
			role2Right.setRightName(rightDTO.getName());
			
			this.role2RightRepository.save(role2Right);
		}
		
		return ConvertTo.convertToRoleDTO(newRole, null);
	}
	
	public RoleDTO updateRole(RoleDTO roleDTO) {
		
		Role newRole = roleRepository.save(ConvertTo.convertToRole(roleDTO));
		
		this.role2RightRepository.deleteByRoleName(roleDTO.getName());
		
		for(RightDTO rightDTO: roleDTO.getRights()) {
			
			Role2Right role2Right = new Role2Right();
			
			role2Right.setRoleName(newRole.getName());
			role2Right.setRightName(rightDTO.getName());
			
			this.role2RightRepository.save(role2Right);
		}
		
		return ConvertTo.convertToRoleDTO(newRole, getRightsByRoleName(newRole.getName()));
	}
	
	public RoleDTO deleteRole(String name) {
		
//		List<Role> roles = this.roleRepository.findByName(name);
//		
//		Role deletedRole = null;
//		
//		if(!roles.isEmpty()) {
//			deletedRole = roles.get(0);
//		}
//		
//		if(deletedRole == null) {
//			return null;
//		}
		
		Role deletedRole = roleRepository.findOne(name);
		RoleDTO deletedRoleDTO = ConvertTo.convertToRoleDTO(deletedRole, getRightsByRoleName(deletedRole.getName()));
		
		roleRepository.delete(name);

		this.role2RightRepository.deleteByRoleName(name);

		return deletedRoleDTO;		
	}
}
