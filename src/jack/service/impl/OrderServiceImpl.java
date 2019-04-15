package jack.service.impl;

import java.util.List;

import jack.dao.OrderDao;
import jack.domain.Order;
import jack.domain.OrderItem;
import jack.service.OrderService;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao;
	
	public void saveOrder(Order order) {

		orderDao.saveOrder(order);

	}

	public void saveOrderItem(List<OrderItem> orderItems) {

		orderDao.saveOrderItem(orderItems);
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void updateOrder(Order order, String oid) {
		orderDao.updateOrder(order,oid);
		
	}

	public List<Order> getOrderListByUid(String uid) {
		// TODO Auto-generated method stub
		return orderDao.getOrderListByUid(uid);
	}

	
}
