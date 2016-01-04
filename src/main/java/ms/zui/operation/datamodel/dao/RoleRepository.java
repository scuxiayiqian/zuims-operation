package ms.zui.operation.datamodel.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ms.zui.operation.datamodel.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	
	List<Role> findByName(String name);
}
