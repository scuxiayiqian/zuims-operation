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
}
