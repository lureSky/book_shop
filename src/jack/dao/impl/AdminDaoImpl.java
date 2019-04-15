package jack.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import jack.dao.AdminDao;
import jack.domain.Product;
import jack.domain.User;

public class AdminDaoImpl extends BaseDaoImpl<User> implements AdminDao{

	public User checkUserFromAdmin(User user) {
		return super.checkUserByPassword(user);
	}



	public Integer getTotalCount(DetachedCriteria dc) {
		return super.getTotalCount(dc);
	}

	public List<User> findUserList(DetachedCriteria dc, int index, int currentCount) {
		return super.getPageList(dc, index, currentCount);
	}



	public List<Product> searchListByWord(String word) {
		String hql="select u.username from User u where u.username like :name";
		Query query = currentSession().createQuery(hql);
		query.setString("name", "%"+word+"%");
		query.setMaxResults(4);
		
		return query.list();
	}



	public void delUserByUid(String uid) {
		super.delete(uid);
		
	}



	public void saveUser(User user) {
		getHibernateTemplate().save(user);
		
	}

	

}
