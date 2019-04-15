package jack.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import jack.dao.NavDao;
import jack.domain.Category;

public class NavDaoImpl extends HibernateDaoSupport implements NavDao {

	//默认存在session
	public List<Category> getCategorys() {
		// TODO Auto-generated method stub
		System.out.println("得到所有的分类");
		return getHibernateTemplate().loadAll(Category.class);
		
		
	}

}
