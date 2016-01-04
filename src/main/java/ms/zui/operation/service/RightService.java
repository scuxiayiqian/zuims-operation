package ms.zui.operation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ms.zui.operation.datamodel.dao.RightRepository;
import ms.zui.operation.datamodel.domain.Right;
import ms.zui.operation.datamodel.dto.RightDTO;
import ms.zui.operation.util.ConvertTo;;

@Service
public class RightService {

	@Autowired
	RightRepository rightRepository;
	
	public RightDTO getRight(long id) {
		
		Right right = rightRepository.findOne(id);
				
		return ConvertTo.convertToRightDTO(right, rightRepository.findOne(right.getParent()));
	}
	
	public List<RightDTO> getRights() {
		
		ArrayList<RightDTO> rightDTOs = new ArrayList<RightDTO>();
		
		for(Right right: rightRepository.findAll()) {
			rightDTOs.add(ConvertTo.convertToRightDTO(right, rightRepository.findOne(right.getParent())));
		}
		
		return rightDTOs;
	}
	
	public RightDTO getRightByName(String name) {
		
		RightDTO rightDTO = null;
		
		for(Right right: rightRepository.findByName(name)) {
			
			if(name.equals(right.getName())) {
				rightDTO = ConvertTo.convertToRightDTO(right, rightRepository.findOne(right.getParent()));
				
				break;
			}
		}
		
		return rightDTO;
	}
		
	public RightDTO createRight(RightDTO rightDTO) {
		
		Right result = rightRepository.save(ConvertTo.convertToRight(rightDTO, getRightByName(rightDTO.getParent())));
		
		if (result != null) {
			
			return ConvertTo.convertToRightDTO(result, rightRepository.findOne(result.getParent()));
		}
		else {
			return null;
		}
	}
	
	public RightDTO updateRight(RightDTO rightDTO) {
		
		Right right = ConvertTo.convertToRight(rightDTO, getRightByName(rightDTO.getParent()));
		Right result = rightRepository.save(right);
		
		if (result != null) {
			return ConvertTo.convertToRightDTO(result, rightRepository.findOne(right.getParent()));
		}
		else {
			return null;
		}
	}
	
	public RightDTO deleteRight(long id) {
		Right right = rightRepository.findOne(id);
		
		if (right != null) {
			
			rightRepository.delete(right);

			if (rightRepository.findOne(id) == null) {
				return ConvertTo.convertToRightDTO(right, rightRepository.findOne(right.getParent()));
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
