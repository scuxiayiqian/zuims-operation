package ms.zui.operation.service;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import ms.zui.operation.Application;
import ms.zui.operation.datamodel.dao.RightRepository;
import ms.zui.operation.datamodel.domain.Right;;

@Service
public class RightService {

	@Autowired
	RightRepository rightRepository;
	
	public Right getRight(long id) {
		
		return rightRepository.findOne(id);
	}
	
	public List<Right> getRights() {
		
		return (List<Right>) rightRepository.findAll();
	}
	
	public List<Right> getRightByName(String name) {
		return rightRepository.findByName(name);
	}
	
	public Right createRight(Right right) {
		return rightRepository.save(right);
	}
	
	public Right updateRight(Right right) {
		return rightRepository.save(right);
	}
	
	public Right deleteRight(long id) {
		Right right = rightRepository.findOne(id);
		
		if (right != null) {
			
			rightRepository.delete(right);

			if (rightRepository.findOne(id) == null) {
				return right;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
		
	}
	/*
	private HashMap<String, Right> 	repoRight;	
	private ObjectMapper mapper = new ObjectMapper();
	
	public RightService() {
		
		Right[] rights = null;
		
		try {
			rights = mapper.readValue(new File(Application.dataPath + "right.json"), Right[].class);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		repoRight = new HashMap<String, Right>();
		
		for (int nIndex = 0; nIndex < rights.length; nIndex++) {
			repoRight.put(rights[nIndex].getName(), rights[nIndex]);
		}
	}
	
	public Right getRightByName(String name) {
		
		return repoRight.get(name);
	}
	
	public Collection<Right> getAllRights() {
		return repoRight.values();
	}
	
	public Right createRight(Right right) {
		
		repoRight.put(right.getName(), right);
		
	   	try {
	   		mapper.writeValue(new File(Application.dataPath + "right.json"), repoRight.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return right;
	}
	
	public Right updateRight(Right right) {
		
		Right returnRight = repoRight.put(right.getName(), right);
		
	   	try {
        	mapper.writeValue(new File(Application.dataPath + "right.json"), repoRight.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return returnRight;
	}

	public Right deleteRight(String name) {
		
		Right returnRight = repoRight.remove(name);
		
	   	try {
        	mapper.writeValue(new File(Application.dataPath + "right.json"), repoRight.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return returnRight;
	}*/
}
