package jack.dao;

import java.util.List;

import jack.domain.Order;
import jack.domain.OrderItem;

public interface OrderDao extends BaseDao<Order>{

	void saveOrder(Order order);

	void saveOrderItem(List<OrderItem> orderItems);

	void updateOrder(Order order, String oid);

	List<Order> getOrderListByUid(String uid);

}
