package jack.pojo;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	//���ﳵ���������ǲ����浽���ݿ⣬����ʹ��pojo������ʹ��bean��
	private Map<String,CartItem> cartItems = new HashMap<String, CartItem>();
	
	//��Ҫ���ܽ��
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
