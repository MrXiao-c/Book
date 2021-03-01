package dao;

import entity.User;

public interface UserDao {

	void regist(User user);

	User findByCode(String code);

	void update(User user);
	User findUser(User user);
	void updatePassword(User user);
	public String findEmail(String email);
	public boolean findbyUser(String username);
}
