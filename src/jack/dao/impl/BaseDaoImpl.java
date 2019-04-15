package jack.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import jack.dao.BaseDao;
import jack.domain.User;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{

	private Class clazz;//接收传入T的具体类型
	
	
	public BaseDaoImpl() {
		ParameterizedType ptClass = (ParameterizedType) this.getClass().getGenericSuperclass();//获得当前泛型类型的父类
		clazz = (Class) ptClass.getActualTypeArguments()[0];//泛型的可能很多
		//则这就是运行时期的数据类型
		
		
	}

	public void save(T t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(t);
	}

	public void delete(T t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(t);
	}

	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		//先获取对象，在删除
		T t = this.getById(id);
		getHibernateTemplate().delete(t);
	}

	public void update(T t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(t);
	}

	public T getById(Serializable id) {
		// TODO Auto-generated method stub
		//已经获取到了当前的类
		T t = (T) getHibernateTemplate().get(clazz.getClass(), id);
		return t; 
	}

	public Integer getTotalCount(DetachedCriteria dc) {

		//设置查询的聚合函数
		dc.setProjection(Projections.rowCount());
		//2.利用模版找
		List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(dc);// 设置后查询的结果只剩下rowcount
		//清空设置好的集合函数（下面的dao方法用到了同一个dc）
		dc.setProjection(null);
		if(list!=null && list.size()>0) {
			Long count = list.get(0);
			return count.intValue();
		}else {
			return null;
		}
		
	}

	public List<T> getPageList(DetachedCriteria dc, Integer start, Integer pageSize) {
		//模版中专门做了分页的模板
		List<T> list = (List<T>) getHibernateTemplate().findByCriteria(dc, start, pageSize);
		return list;
	}

	public void saveOrUpdate(T t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(t);
	}

	public User checkUserByPassword(User user) {
		String hql = "from User user where user.email=? AND user.password=?";
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, user.getEmail());
		query.setParameter(1, user.getPassword());
		
		User u = (User) query.uniqueResult();
		return u;
	}

}
