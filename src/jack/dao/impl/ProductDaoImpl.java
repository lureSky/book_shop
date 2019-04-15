package jack.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import jack.dao.ProductDao;
import jack.domain.Category;
import jack.domain.Product;

public class ProductDaoImpl extends HibernateDaoSupport implements ProductDao{

	public List<Product> getHotEbook() {
		String hql = "from Product product where is_hot = 1 and cid = 2";
		Query query = currentSession().createQuery(hql);
		query.setMaxResults(6);
		return query.list();
	}
	public List<Product> getHotCbook() {
		String hql = "from Product product where is_hot = 1 and cid = 8";
		Query query = currentSession().createQuery(hql);
		query.setMaxResults(6);
		return query.list();
	}
	public List<Product> getNewBook() {
		String hql = "from Product product where is_new = 1";
		Query query = currentSession().createQuery(hql);
		query.setMaxResults(4);
		return query.list();
	}
	public List<Product> searchListByWord(String word) {

		String hql="select p.pname from Product p where p.pname like :name";
		Query query = currentSession().createQuery(hql);
		query.setString("name", "%"+word+"%");
		query.setMaxResults(4);
		
		return query.list();
		
	}
	public Product searchProductByPname(DetachedCriteria dc) {
		List<Product> list = (List<Product>) getHibernateTemplate().findByCriteria(dc);
		return list.size()>0?list.get(0):null;
	}
	public String getCnameByCid(int cid) {
		String hql = "select c.cname from Category c where c.cid = ?";
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, cid);
	
		String cname = (String) query.uniqueResult();
		return cname;
	}
	public int getTotalCount(int  cid) {
		
		//根据cid获得总条数
		String hql = "select count(*) from Product product where cid = ? ";
		Query query = currentSession().createQuery(hql);
		
		query.setParameter(0, cid);
		Long total = (Long) query.uniqueResult();
		
		return total.intValue();

		
		
	}
	public List<Product> findProductListByPage(int cid, int index, int currentCount) {
		// TODO Auto-generated method stub
		
		String hql = "from Product product where cid = ?";
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, cid);
		query.setFirstResult(index);
		query.setMaxResults(currentCount);
		
		return query.list();
	
	}
	

	
}
