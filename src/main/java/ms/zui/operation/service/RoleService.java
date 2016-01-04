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
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	Role2RightRepository role2RightRepository;
	
	@Autowired
	RightRepository rightRepository;
	
	private List<RightDTO> getRightsByRoleId(long roleId) {
		
		List<RightDTO> rightDTOs = new ArrayList<RightDTO>();
		
		List<Role2Right> role2Rights = this.role2RightRepository.findByRoleId(roleId);
		
		for(Role2Right role2Right: role2Rights) {
			Right right = this.rightRepository.findOne(role2Right.getRightId());
			Right parent = this.rightRepository.findOne(right.getParent());
				
			rightDTOs.add(ConvertTo.convertToRightDTO(right, parent));
		}
		
		return rightDTOs;
	}
	
	public RoleDTO getRoleById(long id) {
		
		Role role = this.roleRepository.findOne(id);
				
		return ConvertTo.convertToRoleDTO(role, getRightsByRoleId(id));
	}
	
	public List<RoleDTO> getAllRoles() {
		
		List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
		
		for(Role role: roleRepository.findAll()) {
			
			roleDTOs.add(ConvertTo.convertToRoleDTO(role, getRightsByRoleId(role.getId())));
		}
		
		return roleDTOs;
	}
	
	public RoleDTO createRole(RoleDTO roleDTO) {
		
		Role newRole = roleRepository.save(ConvertTo.convertToRole(roleDTO));
		
		for(RightDTO rightDTO: roleDTO.getRights()) {
			
			Role2Right role2Right = new Role2Right();
			
			role2Right.setRoleId(newRole.getId());
			role2Right.setRightId(rightDTO.getId());
			
			this.role2RightRepository.save(role2Right);
		}
		
		return ConvertTo.convertToRoleDTO(newRole, null);
	}
	
	public RoleDTO updateRole(RoleDTO roleDTO) {
		
		Role newRole = roleRepository.save(ConvertTo.convertToRole(roleDTO));
		
		this.role2RightRepository.deleteByRoleId(roleDTO.getId());
		
		for(RightDTO rightDTO: roleDTO.getRights()) {
			
			Role2Right role2Right = new Role2Right();
			
			role2Right.setRoleId(newRole.getId());
			role2Right.setRightId(rightDTO.getId());
			
			this.role2RightRepository.save(role2Right);
		}
		
		return ConvertTo.convertToRoleDTO(newRole, null);
	}
	
	public RoleDTO deleteRole(long id) {
		
		Role deletedRole = roleRepository.findOne(id);
		
		if(deletedRole == null) {
			return null;
		}
		
		RoleDTO deletedRoleDTO = ConvertTo.convertToRoleDTO(deletedRole, null);
		
		roleRepository.delete(id);

		this.role2RightRepository.deleteByRoleId(id);

		return deletedRoleDTO;		
	}
}
