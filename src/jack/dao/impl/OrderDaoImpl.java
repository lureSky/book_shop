package jack.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import jack.dao.OrderDao;
import jack.domain.Order;
import jack.domain.OrderItem;

public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao{

	public void saveOrder(Order order) {

		super.save(order);
	}

	public void saveOrderItem(List<OrderItem> orderItems) {

		for (OrderItem orderItem : orderItems) {
			//≤Â»Î”Ôæ‰
			String sql = "INSERT INTO orderitem VALUES(?,?,?,?,?)";
			SQLQuery query = currentSession().createSQLQuery(sql);
			query.setParameter(0, orderItem.getItemid());
			query.setParameter(1, orderItem.getCount());
			query.setParameter(2, orderItem.getSubTotal());
			query.setParameter(3, orderItem.getProduct().getPid());
			query.setParameter(4, orderItem.getOrder().getOid());
			query.executeUpdate();
		
		}
	}

	public void updateOrder(Order order, String oid) {
		// TODO Auto-generated method stub
		String hql = "update Order o set o.address=?,o.name=?,o.telephone=? where o.oid = ?";
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, order.getAddress());
		query.setParameter(1, order.getName());
		query.setParameter(2, order.getTelephone());
		query.setParameter(3, oid);
		query.executeUpdate();
		
		
	}

	public List<Order> getOrderListByUid(String uid) {

		String hql = "from Order order where uid = ?";
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, uid);
		List<Order> list = query.list();
		
		
		return list;
	}

}
