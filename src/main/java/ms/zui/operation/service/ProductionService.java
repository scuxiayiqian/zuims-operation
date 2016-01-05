package ms.zui.operation.service;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import ms.zui.operation.Application;
import ms.zui.operation.datamodel.domain.Production;

public class ProductionService extends BaseService{
	
	private HashMap<String, Production> repoProduction;
	private ObjectMapper mapper = new ObjectMapper();
	
	public ProductionService() {
		
		Production[] productions = null;
		
		try {
			productions = mapper.readValue(new File(Application.dataPath + "production.json"), Production[].class);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		repoProduction = new HashMap<String, Production>();
		
		for (int nIndex = 0; nIndex < productions.length; nIndex++) {
			repoProduction.put(productions[nIndex].getName(), productions[nIndex]);
		}
	
	}
	
	public Production getProductionByName(String name) {
		
		return repoProduction.get(name);
	}
	
	public Collection<Production> getAllProductions() {
		
		return repoProduction.values();
	}
	
	public Production createProduction(Production production) {
		
		repoProduction.put(production.getName(), production);
		
		try {
	   		mapper.writeValue(new File(Application.dataPath + "production.json"), repoProduction.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return production;
		
	}
	
	public Production updateProduction(Production production) {
		
		Production returnProduction = repoProduction.put(production.getName(), production);
		
		try {
			mapper.writeValue(new File(Application.dataPath + "production.json"), repoProduction.values());
		} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return returnProduction;
	}
	
	public Production deletProduction(String name) {

		Production returnProduction = repoProduction.remove(name);
		
		try {
			mapper.writeValue(new File(Application.dataPath + "production.json"), repoProduction.values());
		} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return returnProduction;
	}
	
}
