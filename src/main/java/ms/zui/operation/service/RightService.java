package ms.zui.operation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ms.zui.operation.datamodel.dao.RightRepository;
import ms.zui.operation.datamodel.domain.Right;

@Service
public class RightService extends BaseService{

	@Autowired
	RightRepository rightRepository;
	
	public Right getRight(long id) {
		
		return rightRepository.findOne(id);
	}
	
	public List<Right> getRights() {
		
		return (List<Right>) rightRepository.findAll();
	}
	
	public Right createRight(Right right) {
		
		return this.rightRepository.save(right);
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
}
