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

	private Class clazz;//���մ���T�ľ�������
	
	
	public BaseDaoImpl() {
		ParameterizedType ptClass = (ParameterizedType) this.getClass().getGenericSuperclass();//��õ�ǰ�������͵ĸ���
		clazz = (Class) ptClass.getActualTypeArguments()[0];//���͵Ŀ��ܺܶ�
		//�����������ʱ�ڵ���������
		
		
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
		//�Ȼ�ȡ������ɾ��
		T t = this.getById(id);
		getHibernateTemplate().delete(t);
	}

	public void update(T t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(t);
	}

	public T getById(Serializable id) {
		// TODO Auto-generated method stub
		//�Ѿ���ȡ���˵�ǰ����
		T t = (T) getHibernateTemplate().get(clazz.getClass(), id);
		return t; 
	}

	public Integer getTotalCount(DetachedCriteria dc) {

		//���ò�ѯ�ľۺϺ���
		dc.setProjection(Projections.rowCount());
		//2.����ģ����
		List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(dc);// ���ú��ѯ�Ľ��ֻʣ��rowcount
		//������úõļ��Ϻ����������dao�����õ���ͬһ��dc��
		dc.setProjection(null);
		if(list!=null && list.size()>0) {
			Long count = list.get(0);
			return count.intValue();
		}else {
			return null;
		}
		
	}

	public List<T> getPageList(DetachedCriteria dc, Integer start, Integer pageSize) {
		//ģ����ר�����˷�ҳ��ģ��
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
