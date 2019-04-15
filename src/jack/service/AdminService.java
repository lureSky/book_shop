package jack.service;

import java.util.List;

import jack.domain.Product;
import jack.domain.User;
import jack.pojo.PageBean;

public interface AdminService {

	User checkUserFromAdmin(User user);


	PageBean<User> findUserList(int currentPage, int currentCount);


	List<Product> searchListByWord(String word);


	void delUserByUid(String uid);


	PageBean<User> findUserListByUsername(String username, int currentPage, int currentCount);


	void saveUser(User user);

	
}
