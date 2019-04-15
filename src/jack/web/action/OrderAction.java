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
		//获得所有的order对象，展示，
		//记住user
		User user = (User) ActionContext.getContext().getSession().get("user");
		List<Order> orderList = orderService.getOrderListByUid(user.getUid());
		
		
		//放入session
		ActionContext.getContext().put("AllOrderList", orderList);
		
		//跳转页面
		return "toOrderList";
	}


	public String confirmOrder() throws Exception {
		//获得前台的数据，进行更新
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		//前台表单的数据进行获取，先更新数据库
		Map<String, String[]> map = request.getParameterMap();
		Order order = new Order();
		try {
			BeanUtils.populate(order, map);
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		//功能1：先完善order的信息
		String oid = request.getParameter("oid");
		orderService.updateOrder(order,oid);
		
		return "toOrderListAction";
		
		
		
	}


	public String submitOrder() throws Exception {
		
		//得到user和cart
		User user = (User) ActionContext.getContext().getSession().get("user");
		Cart cart = (Cart) ActionContext.getContext().getSession().get("cart");
		
		//封装order
		order.setOid(CommonUtils.getUUID());
		
		//2.封装下单时间
		order.setOrderTime(new Date());
		
		//3.封装总金额
		order.setTotal(cart.getTotal());
		
		//4.设置支付状态
		order.setState(0);
		order.setName(null);
		order.setAddress(null);
		order.setTelephone(null);
		order.setUser(user);
		
		//封装订单项
		List<OrderItem> orderItems = order.getOrderItems();
		
		//对orderItem进行封装
		//利用购物车的一一对应
		Map<String, CartItem> cartItems = cart.getCartItems();
		//这里不要pid的String，键，我们只要值的一一对应
		for (Map.Entry<String,CartItem> entry : cartItems.entrySet()) {
			OrderItem orderItem = new OrderItem();
			//1.设置订单项id
			orderItem.setItemid(CommonUtils.getUUID());
			//2.设置订单项内购买的数量
			orderItem.setCount(entry.getValue().getBuyNums());
			//3.订单项小计
			orderItem.setSubTotal(entry.getValue().getSubTotal());
			//4.订单项中的商品
			orderItem.setProduct(entry.getValue().getProduct());
			//5.订单项属于哪个订单
			orderItem.setOrder(order);
			
			orderItems.add(orderItem);
		}
		
		//吧order封装好，
		//把表单和表单项放入数据库中
		orderService.saveOrder(order);
		orderService.saveOrderItem(orderItems);
		
		//放入session
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
