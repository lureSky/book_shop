package jack.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import jack.dao.NavDao;
import jack.domain.Category;

public class NavDaoImpl extends HibernateDaoSupport implements NavDao {

	//Ĭ�ϴ���session
	public List<Category> getCategorys() {
		// TODO Auto-generated method stub
		System.out.println("�õ����еķ���");
		return getHibernateTemplate().loadAll(Category.class);
		
		
	}

}
