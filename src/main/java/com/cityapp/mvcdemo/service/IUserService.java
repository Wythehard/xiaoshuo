package com.cityapp.mvcdemo.service;

import com.cityapp.mvcdemo.domain.User;

public interface IUserService {
	public User getUserById(int userId);
	public int insert(User user);
	public int update(User user);
}
