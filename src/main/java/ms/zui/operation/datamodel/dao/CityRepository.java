package ms.zui.operation.datamodel.dao;

import org.springframework.data.repository.CrudRepository;

import ms.zui.operation.datamodel.domain.City;

public interface CityRepository extends CrudRepository<City, String>{
	
}
