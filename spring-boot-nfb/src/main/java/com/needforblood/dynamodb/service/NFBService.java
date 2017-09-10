package com.needforblood.dynamodb.service;

import java.util.List;

import com.needforblood.dynamodb.model.BBDetialsTO;

public interface NFBService {

	BBDetialsTO findById(String id);
	
	BBDetialsTO findByName(String name);
	
	void saveUser(BBDetialsTO user);
	
	void updateUser(BBDetialsTO user);
	
	void deleteUserById(String id);

	List<BBDetialsTO> findAllUsers();
	
	void deleteAllUsers();
	
	boolean isUserExist(BBDetialsTO user);

}
