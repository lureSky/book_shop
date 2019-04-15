package jack.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import jack.dao.CartDao;
import jack.domain.Product;

public class CartDaoImpl extends HibernateDaoSupport implements CartDao{

	public Product findProductByPid(String pid) {
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.add(Restrictions.eq("pid", pid));
		
		List<Product> list = (List<Product>) getHibernateTemplate().findByCriteria(dc);
		
		if(list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
		
	}
}
