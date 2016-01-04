package ms.zui.operation.datamodel.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ms.zui.operation.datamodel.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{

	List<User> findByName(String name);
}
