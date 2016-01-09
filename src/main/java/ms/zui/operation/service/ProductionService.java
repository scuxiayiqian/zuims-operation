package ms.zui.operation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ms.zui.operation.datamodel.dao.ProductionRepository;
import ms.zui.operation.datamodel.domain.Production;

@Service
public class ProductionService extends BaseService{
	
	@Autowired
	ProductionRepository productionRepository;
	
	public Production getProductionById(long id) {
		
		return this.productionRepository.findOne(id);
	}
	
	public List<Production> getAllProductions() {
		
		return (List<Production>) this.productionRepository.findAll();
	}
	
	public Production createProduction(Production production) {
		
		return this.productionRepository.save(production);
		
	}
	
	public Production updateProduction(Production production) {
		
		return this.productionRepository.save(production);
	}
	
	public Production deletProduction(long id) {

		Production deletedProduction = this.productionRepository.findOne(id);
		
		this.productionRepository.delete(id);

		return deletedProduction;		
	}
	
}
