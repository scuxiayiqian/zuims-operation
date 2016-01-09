package ms.zui.operation.datamodel.dao;

import org.springframework.data.repository.CrudRepository;

import ms.zui.operation.datamodel.domain.Production;

public interface ProductionRepository extends CrudRepository<Production, Long>{
	
}
