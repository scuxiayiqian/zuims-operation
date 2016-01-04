package ms.zui.operation.datamodel.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ms.zui.operation.datamodel.domain.Right;

public interface RightRepository extends CrudRepository<Right, Long>{
	
	List<Right> findByName(String name);
}
