package service;

import entity.User;

public interface UserService {

	void regist(User user);
	void resetpwd(User user);
	User findByCode(String code);

	void update(User user);
	User loginUser(User user);

	public String findByEmail(String email);

}
