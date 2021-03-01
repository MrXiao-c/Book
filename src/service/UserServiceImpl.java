package service;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;
import service.UserService;
import util.MailUtils;
import util.MailUtils;

public class UserServiceImpl implements UserService {
   //将数据存入数据库
	public void regist(User user) {
		// TODO Auto-generated method stub
		UserDao userDao =new UserDaoImpl();
		userDao.regist(user);
		//发送一封激活确认邮件
		try {
			MailUtils.sendMail(user.getEmail(), user.getCode());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void resetpwd(User user) {
		// TODO Auto-generated method stub
		UserDao userDao =new UserDaoImpl();
		userDao.updatePassword(user);
		
		
	}

public User findByCode(String code) {
	// TODO Auto-generated method stub
	UserDao userDao =new UserDaoImpl();
	
	return userDao.findByCode(code);
}
public String findByEmail(String email) {
	// TODO Auto-generated method stub
	UserDao userDao =new UserDaoImpl();
	
	return userDao.findEmail(email);
}
public void update(User user) {
	// TODO Auto-generated method stub
	UserDao userDao=new UserDaoImpl();
	userDao.update(user);
}



@Override
public User loginUser(User user) {
	UserDao userDao=new UserDaoImpl();
	System.out.println("loginimpl" + userDao.findUser(user));
	return userDao.findUser(user);
}

}
