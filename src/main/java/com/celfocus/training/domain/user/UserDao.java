package com.celfocus.training.domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.celfocus.training.domain.AbstractDao;
import com.celfocus.training.util.Utils;

public class UserDao implements AbstractDao<User> {

	private List<User> users = new ArrayList<>();

	@Override
	public List<User> getAll() {
		return users;
	}

	public User upsert(User user) {
		Objects.requireNonNull(user);
		return userExists(user.getName()) ? update(user) : create(user);
	}

	public User update(User user) {
		Objects.requireNonNull(user);
		User savedUser = findUserByName(user.getName());
		if (savedUser != null) {
			savedUser.setBirthDate(user.getBirthDate());
			savedUser.setSenior(user.isSenior());
		}
		return savedUser;
	}

	@Override
	public User create(User user) {
		Objects.requireNonNull(user);
		users.add(user);
		return user;
	}

	@Override
	public void delete(String userName) {
		if (userExists(userName)) {
			users.remove(findUserByName(userName));
		}
	}

	public boolean userExists(String name) {
		return findUserByName(name) != null;
	}

	public User findUserByName(String name) {
		if (Utils.isNullOrEmpty(name)) {
			return null;
		}
		for (User user : users) {
			if (name.equals(user.getName())) {
				return user;
			}
		}
		return null;
	}

}
