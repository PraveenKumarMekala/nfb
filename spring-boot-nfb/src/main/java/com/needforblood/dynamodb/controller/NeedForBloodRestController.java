package com.needforblood.dynamodb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.needforblood.dynamodb.exception.CustomErrorType;
import com.needforblood.dynamodb.model.BBDetialsTO;
import com.needforblood.dynamodb.service.NFBService;


@RestController
@RequestMapping("/nfb")
public class NeedForBloodRestController {
	


	public static final Logger logger = LoggerFactory.getLogger(NeedForBloodRestController.class);

	@Autowired
	NFBService userService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Users---------------------------------------------

	@RequestMapping(value = "/nfbuser/", method = RequestMethod.GET)
	public ResponseEntity<List<BBDetialsTO>> listAllUsers() {
		List<BBDetialsTO> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<BBDetialsTO>>(users, HttpStatus.OK);
	}

	// -------------------Retrieve Single User------------------------------------------

	@RequestMapping(value = "/nfbuser/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") String id) {
		logger.info("Fetching User with id {}", id);
		BBDetialsTO user = userService.findById(id);
		if (user == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("User with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BBDetialsTO>(user, HttpStatus.OK);
	}

	// -------------------Create a User-------------------------------------------

	@RequestMapping(value = "/nfbuser/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody BBDetialsTO user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", user);

		if (userService.isUserExist(user)) {
			logger.error("Unable to create. A User with name {} already exist", user.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
			user.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a User ------------------------------------------------

	@RequestMapping(value = "/nfbuser/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") String id, @RequestBody BBDetialsTO bbuser) {
		logger.info("Updating User with id {}", id);

		BBDetialsTO bbdetails = userService.findById(id);

		if (bbdetails == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		bbdetails.setName(bbuser.getName());
		bbdetails.setPlace(bbuser.getPlace());
		bbdetails.setState(bbuser.getState());
		bbdetails.setCountry(bbuser.getCountry());
		userService.updateUser(bbdetails);
		return new ResponseEntity<BBDetialsTO>(bbdetails, HttpStatus.OK);
	}

	// ------------------- Delete a User-----------------------------------------

	@RequestMapping(value = "/nfbuser/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
		logger.info("Fetching & Deleting User with id {}", id);

		BBDetialsTO user = userService.findById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<BBDetialsTO>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Users-----------------------------

	@RequestMapping(value = "/nfbuser/", method = RequestMethod.DELETE)
	public ResponseEntity<BBDetialsTO> deleteAllUsers() {
		logger.info("Deleting All Users");

		userService.deleteAllUsers();
		return new ResponseEntity<BBDetialsTO>(HttpStatus.NO_CONTENT);
	}



}
