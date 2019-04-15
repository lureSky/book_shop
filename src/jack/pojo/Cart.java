package jack.pojo;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	//购物车的数据我们不保存到数据库，所以使用pojo包，不使用bean包
	private Map<String,CartItem> cartItems = new HashMap<String, CartItem>();
	
	//还要有总金额
	private double total;

	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
