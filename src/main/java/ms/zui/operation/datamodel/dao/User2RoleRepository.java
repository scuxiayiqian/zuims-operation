package ms.zui.operation.datamodel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ms.zui.operation.datamodel.domain.User2Role;

public interface User2RoleRepository extends CrudRepository<User2Role, Long>{
	
	List<User2Role> findByUserId(long userId);
	List<User2Role> findByRoleId(long roleId);
	
	@Transactional
	@Modifying
	@Query("delete from User2Role u where u.userId=:userId")
	int deleteByUserId(@Param("userId") long userId);
}
