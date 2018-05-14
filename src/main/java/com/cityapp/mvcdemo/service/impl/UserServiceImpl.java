package com.cityapp.mvcdemo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cityapp.mvcdemo.IDao.UserMapper;
import com.cityapp.mvcdemo.domain.User;
import com.cityapp.mvcdemo.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private UserMapper userDao;
	public User getUserById(int userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}
	public int insert(User newuser)
	{
		
		return this.userDao.insert(newuser);
		
	}
	public int update(User newuser)
	{
		
		return this.userDao.updateByPrimaryKey(newuser);
		
	}

}