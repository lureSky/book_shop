package jack.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import jack.domain.Product;
import jack.domain.User;

public interface AdminDao extends BaseDao<User>{

	User checkUserFromAdmin(User user);


	Integer getTotalCount(DetachedCriteria dc);

	List<User> findUserList(DetachedCriteria dc, int index, int currentCount);


	List<Product> searchListByWord(String word);


	void delUserByUid(String uid);


	void saveUser(User user);

}
