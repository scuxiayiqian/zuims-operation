package ms.zui.operation.datamodel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ms.zui.operation.datamodel.domain.Role2Right;

public interface Role2RightRepository extends CrudRepository<Role2Right, String>{
	
	List<Role2Right> findByRoleName(String roleName);

	@Transactional
	@Modifying
	@Query("delete from Role2Right r where r.roleName=:roleName")
	int deleteByRoleName(@Param("roleName") String roleName);
}
