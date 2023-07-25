package com.damddos.service;

import com.damddos.entity.User;
import com.damddos.utils.MD5;
import com.damddos.commons.ResponseCode;
import com.damddos.commons.exception.Assert;
import com.damddos.dao.UserDao;

public class UserService {
	public static User login(User user) {
		User resUser = new User();
		
		int id = user.getId();
		String userName = user.getUserName();
		String password = user.getPassword();
		
		User dbUser = UserDao.getUserById(id);
//		判断用户是否存在
		Assert.notNull(dbUser, ResponseCode.LOGIN_MOBILE_ERROR);
//		判断密码是否正确
		Assert.equals(MD5.encrypt(password), dbUser.getPassword(), ResponseCode.LOGIN_PASSWORD_ERROR);
		
//		封装UserVO
		resUser.setUserName(userName);
//		resUser.setPassword(password);
		return resUser;		
	}
}
