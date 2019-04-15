package jack.service.impl;

import java.sql.SQLException;

import jack.dao.UserDao;
import jack.domain.User;
import jack.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;
	public boolean checkEmail(String email) {
		//校验用户名
		User user = new User();
		user = userDao.checkEmail(email);
		if(user!=null) {
			return true;
		}else {
			return false;
		}
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void save(User user) {
		userDao.save(user);
		
	}
	public User checkUserByPassword(User user) {
		// TODO Auto-generated method stub
		return userDao.checkUserByPassword(user);
	}
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.getUserByEmail(email);
	}
	public void updatePassByEmail(User user) {
		// TODO Auto-generated method stub
		userDao.updatePassByEmail(user);
	}
	

}
