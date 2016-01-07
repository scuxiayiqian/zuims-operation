package ms.zui.operation.datamodel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ms.zui.operation.datamodel.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{

	List<User> findByName(String name);
	
	@Query("select u from User u join u.roles r where r.name =:roleName")
	List<User> findByRoleName(@Param("roleName") String roleName);
}
