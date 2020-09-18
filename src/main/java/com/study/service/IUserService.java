package com.study.service;

import com.study.pojo.User;

public interface IUserService {
	public User addUser(User user);
	public void deleteUser(String userId);
	public User updateUser(User user);
	public User queryUser(int userId);

	
}
