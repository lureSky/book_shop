package jack.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import jack.dao.UserDao;
import jack.domain.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public User checkEmail(String email) {
		//利用离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("email", email));
		List<User> list = (List<User>) getHibernateTemplate().findByCriteria(dc);
		if(list!=null&&list.size()>0) {
			return list.get(0);
		}else {
			return null; 
		}
	}

	public void save(User user) {
		super.save(user);
		
	}

	public User checkUserByPassword(User user) {
		return super.checkUserByPassword(user);
		
	}

	public User getUserByEmail(String email) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("email", email));
		List<User> list = (List<User>) getHibernateTemplate().findByCriteria(dc);
			
		return list.get(0);
	}

	public void updatePassByEmail(User user) {
		String hql = "update User user set user.password = ? where user.email = ?";
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, user.getPassword());
		query.setParameter(1, user.getEmail());
		
		query.executeUpdate();
		
	}

}
