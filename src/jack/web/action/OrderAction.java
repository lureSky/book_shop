package jack.web.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import jack.domain.Order;
import jack.domain.User;
import jack.pojo.Cart;
import jack.utils.CommonUtils;
import jack.pojo.CartItem;
import jack.service.OrderService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import jack.domain.OrderItem;

public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	private Order order = new Order();
	private OrderService orderService;
	
	
	
	
	public String getOrderList() throws Exception {
		//������е�order����չʾ��
		//��סuser
		User user = (User) ActionContext.getContext().getSession().get("user");
		List<Order> orderList = orderService.getOrderListByUid(user.getUid());
		
		
		//����session
		ActionContext.getContext().put("AllOrderList", orderList);
		
		//��תҳ��
		return "toOrderList";
	}


	public String confirmOrder() throws Exception {
		//���ǰ̨�����ݣ����и���
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		//ǰ̨�������ݽ��л�ȡ���ȸ������ݿ�
		Map<String, String[]> map = request.getParameterMap();
		Order order = new Order();
		try {
			BeanUtils.populate(order, map);
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		//����1��������order����Ϣ
		String oid = request.getParameter("oid");
		orderService.updateOrder(order,oid);
		
		return "toOrderListAction";
		
		
		
	}


	public String submitOrder() throws Exception {
		
		//�õ�user��cart
		User user = (User) ActionContext.getContext().getSession().get("user");
		Cart cart = (Cart) ActionContext.getContext().getSession().get("cart");
		
		//��װorder
		order.setOid(CommonUtils.getUUID());
		
		//2.��װ�µ�ʱ��
		order.setOrderTime(new Date());
		
		//3.��װ�ܽ��
		order.setTotal(cart.getTotal());
		
		//4.����֧��״̬
		order.setState(0);
		order.setName(null);
		order.setAddress(null);
		order.setTelephone(null);
		order.setUser(user);
		
		//��װ������
		List<OrderItem> orderItems = order.getOrderItems();
		
		//��orderItem���з�װ
		//���ù��ﳵ��һһ��Ӧ
		Map<String, CartItem> cartItems = cart.getCartItems();
		//���ﲻҪpid��String����������ֻҪֵ��һһ��Ӧ
		for (Map.Entry<String,CartItem> entry : cartItems.entrySet()) {
			OrderItem orderItem = new OrderItem();
			//1.���ö�����id
			orderItem.setItemid(CommonUtils.getUUID());
			//2.���ö������ڹ��������
			orderItem.setCount(entry.getValue().getBuyNums());
			//3.������С��
			orderItem.setSubTotal(entry.getValue().getSubTotal());
			//4.�������е���Ʒ
			orderItem.setProduct(entry.getValue().getProduct());
			//5.�����������ĸ�����
			orderItem.setOrder(order);
			
			orderItems.add(orderItem);
		}
		
		//��order��װ�ã�
		//�ѱ��ͱ���������ݿ���
		orderService.saveOrder(order);
		orderService.saveOrderItem(orderItems);
		
		//����session
		ActionContext.getContext().getSession().put("order", order);
		
		
		return "toOrder";
		
	}


	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}


	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	
	

}
