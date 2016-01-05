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
public class RightService extends BaseService{

	@Autowired
	RightRepository rightRepository;
	
	public RightDTO getRight(String name) {
		
		Right right = rightRepository.findOne(name);
				
		return ConvertTo.convertToRightDTO(right);
	}
	
	public List<RightDTO> getRights() {
		
		ArrayList<RightDTO> rightDTOs = new ArrayList<RightDTO>();
		
		for(Right right: rightRepository.findAll()) {
			rightDTOs.add(ConvertTo.convertToRightDTO(right));
		}
		
		return rightDTOs;
	}
	
	public RightDTO createRight(RightDTO rightDTO) {
		
		Right result = rightRepository.save(ConvertTo.convertToRight(rightDTO));
		
		if (result != null) {
			
			return ConvertTo.convertToRightDTO(result);
		}
		else {
			return null;
		}
	}
	
	public RightDTO updateRight(RightDTO rightDTO) {
		
		Right right = ConvertTo.convertToRight(rightDTO);
		
		Right result = rightRepository.save(right);
		
		if (result != null) {
			return ConvertTo.convertToRightDTO(result);
		}
		else {
			return null;
		}
	}
	
	public RightDTO deleteRight(String name) {
		Right right = rightRepository.findOne(name);
		
		if (right != null) {
			
			rightRepository.delete(right);

			if (rightRepository.findOne(name) == null) {
				return ConvertTo.convertToRightDTO(right);
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
		
	}
}
