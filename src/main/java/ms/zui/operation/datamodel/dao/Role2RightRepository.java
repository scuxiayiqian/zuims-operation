package ms.zui.operation.datamodel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ms.zui.operation.datamodel.domain.Role2Right;

public interface Role2RightRepository extends CrudRepository<Role2Right, Long>{
	
	List<Role2Right> findByRoleId(long roleId);

	@Transactional
	@Modifying
	@Query("delete from Role2Right r where r.roleId=:roleId")
	int deleteByRoleId(@Param("roleId") long roleId);
}
