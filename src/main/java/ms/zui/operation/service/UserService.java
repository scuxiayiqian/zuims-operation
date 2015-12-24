package ms.zui.operation.service;

import java.io.File;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import ms.zui.operation.Application;
import ms.zui.operation.datamodel.domain.User;

import java.util.ArrayList;
import java.util.Collection;

public class UserService {

	private HashMap<String, User> repoUser;
	private ObjectMapper mapper = new ObjectMapper();
	
	public UserService() {

		User[] users = null;
		
		try {
			users = mapper.readValue(new File(System.getProperty("user.dir") + "/" + Application.dataPath + "user.json"), User[].class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		repoUser = new HashMap<String, User>();
		
		for (int nIndex = 0; nIndex < users.length; nIndex++) {
			repoUser.put(users[nIndex].getName(), users[nIndex]);
		}		
	}
	
	public User getUserByName(String name) {
		
		return repoUser.get(name);
	}
	
	public Collection<User> getAllUsers() {
		return repoUser.values();
	}
	
	public Collection<User> getUsersByRole(String role) {
		
		ArrayList<User> users = new ArrayList<User>();
		
		for (User user: repoUser.values()) {
			
			if (user.hasRole(role)) {
				
				users.add(user);
			}
		}
		return users;
	}
	public User createUser(User user) {
		
		repoUser.put(user.getName(), user);
		
	   	try {
        	mapper.writeValue(new File(Application.dataPath + "user.json"), repoUser.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return user;
	}
	
	public User updateUser(User user) {
		
		User returnUser = repoUser.put(user.getName(), user);
		
	   	try {
        	mapper.writeValue(new File(Application.dataPath + "user.json"), repoUser.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return returnUser;
	}

	public User deleteUser(String name) {
		
		User returnUser = repoUser.remove(name);
		
	   	try {
        	mapper.writeValue(new File(Application.dataPath + "user.json"), repoUser.values());    		
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
 
		return returnUser;
	}
	
	public boolean login(User user) {

		if (repoUser.get(user.getName()).getPassword().equals(user.getPassword())) {
    		return true;
    	}
    	else {
    		return false;
    	}
	}

}
