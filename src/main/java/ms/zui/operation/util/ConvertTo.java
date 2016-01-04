package ms.zui.operation.util;

import ms.zui.operation.datamodel.domain.Right;
import ms.zui.operation.datamodel.dto.RightDTO;

public class ConvertTo {
	
	public static RightDTO convertToRightDTO(Right right, Right  parent) {
		RightDTO rightDTO = new RightDTO();
		
		rightDTO.setId(right.getId());
		rightDTO.setName(right.getName());
		
		if(parent != null) {
			rightDTO.setParent(parent.getName());
		}
		else {
			
			rightDTO.setParent("");
		}
		
		return rightDTO;
	}
	
	public static Right convertToRight(RightDTO rightDTO, RightDTO parent) {
		Right right = new Right();
		
		right.setId(rightDTO.getId());
		right.setName(rightDTO.getName());
		
		if(parent != null) {
			
			right.setParent(parent.getId());
		}
		else {
			right.setParent(-1);
		}
		
		return right;
	}
}
