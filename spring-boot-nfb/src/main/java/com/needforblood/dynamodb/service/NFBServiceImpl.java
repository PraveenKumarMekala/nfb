package com.needforblood.dynamodb.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Service;

import com.needforblood.dynamodb.model.BBDetialsTO;

@EntityScan
@Service("nfbService")
public class NFBServiceImpl implements NFBService {
	private static final AtomicLong counter = new AtomicLong();

	private static List<BBDetialsTO> users;

	static {
		users = populateDummyUsers();
	}

	public List<BBDetialsTO> findAllUsers() {
		return users;
	}

	public BBDetialsTO findById(String id) {
		for (BBDetialsTO user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public BBDetialsTO findByName(String name) {
		for (BBDetialsTO user : users) {
			if (user.getName().equalsIgnoreCase(name)) {
				return user;
			}
		}
		return null;
	}

	public void saveUser(BBDetialsTO user) {
//		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	public void updateUser(BBDetialsTO user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public void deleteUserById(String id) {

		for (Iterator<BBDetialsTO> iterator = users.iterator(); iterator.hasNext();) {
			BBDetialsTO user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
			}
		}
	}

	public boolean isUserExist(BBDetialsTO user) {
		return findByName(user.getName()) != null;
	}

	public void deleteAllUsers() {
		users.clear();
	}

	private static List<BBDetialsTO> populateDummyUsers() {
		List<BBDetialsTO> users = new ArrayList<BBDetialsTO>();
		users.add(new BBDetialsTO("Red-Cross", "E-City", "KA", "Bng"));
		users.add(new BBDetialsTO("Blue-Cross", "JP-Nagar", "KA", "Bng"));
		users.add(new BBDetialsTO("Green-Cross", "Majestic", "KA", "Bng"));
		
		return users;
	}
}
