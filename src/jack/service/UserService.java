package jack.service;

import jack.domain.User;

public interface UserService {


	void save(User user);

	User checkUserByPassword(User user);

	boolean checkEmail(String email);

	User getUserByEmail(String email);

	void updatePassByEmail(User user);

}
