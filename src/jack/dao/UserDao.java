package jack.dao;

import jack.domain.User;

public interface UserDao extends BaseDao<User>{

	User checkEmail(String email);

	void save(User user);

	User checkUserByPassword(User user);

	User getUserByEmail(String email);

	void updatePassByEmail(User user);

}
