package ms.zui.operation.service;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import ms.zui.operation.Application;
import ms.zui.operation.datamodel.domain.Guest;

public class GuestService extends BaseService{

	private HashMap<String, Guest> 	repoGuest;	
	private ObjectMapper mapper = new ObjectMapper();
	
	public GuestService() {
		
		Guest[] guests = null;
		
		try {
			guests = mapper.readValue(new File(Application.dataPath + "guest.json"), Guest[].class);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		repoGuest = new HashMap<String, Guest>();
		
		for (int nIndex = 0; nIndex < guests.length; nIndex++) {
			repoGuest.put(guests[nIndex].getName(), guests[nIndex]);
		}
	}
	
	public Guest getGuestByName(String name) {
		
		return repoGuest.get(name);
	}
	
	public Collection<Guest> getAllGuests() {
		return repoGuest.values();
	}
	
	public Guest createGuest(Guest guest) {
		
		repoGuest.put(guest.getName(), guest);
		
	   	try {
	   		mapper.writeValue(new File(Application.dataPath + "guest.json"), repoGuest.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return guest;
	}
	
	public Guest updateGuest(Guest guest) {
		
		Guest returnGuest = repoGuest.put(guest.getName(), guest);
		
	   	try {
        	mapper.writeValue(new File(Application.dataPath + "guest.json"), repoGuest.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return returnGuest;
	}

	public Guest deleteGuest(String name) {
		
		Guest returnGuest = repoGuest.remove(name);
		
	   	try {
        	mapper.writeValue(new File(Application.dataPath + "guest.json"), repoGuest.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return returnGuest;
	}
}
