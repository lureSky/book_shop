package jack.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import jack.domain.Product;
import jack.pojo.Cart;
import jack.pojo.CartItem;
import jack.service.CartService;

public class CartAction extends ActionSupport implements ModelDriven<CartItem>{
	private CartItem cartItem = new CartItem();
	private CartService cartService;
	
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}


	public String deleteProductFromCart() throws Exception {
		
		//������һ��pid
		String pid = ServletActionContext.getRequest().getParameter("pid");
		//���ǵ�map�Ǹ���pid��cartItems�ŵ�
		//���Ǹ���pidɾ��һ��cartItems
		Cart cart = (Cart) ActionContext.getContext().getSession().get("cart");
		
		if(cart!= null) {
			Map<String, CartItem> cartItems = cart.getCartItems();
			
			//�û�ø���Ľ��
			double subTotal = 0.0;
			if(cartItems.containsKey(pid)) {
				CartItem cartItem = cartItems.get(pid);
				subTotal = cartItem.getSubTotal();
				cartItems.remove(pid);
			}
			double total = cart.getTotal();
			total -= subTotal;
			
			cart.setCartItems(cartItems);
			cart.setTotal(total);
		}
		
		ActionContext.getContext().getSession().put("cart", cart);
		
		return "toCart";
	}
	
	
	public String clearCart() throws Exception {
		//ֻ��Ҫ�����ﳵsession��ռ���
		ActionContext.getContext().getSession().put("cart", null);
		return "toHome";
	}



	public String addProductToCart() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		//1.�Ȼ��request�е�pid
		String pid = request.getParameter("pid");
		String buyNumStr = request.getParameter("buyNum");
		int buyNum = Integer.parseInt(buyNumStr);
		
		//2.��ȡpid��Ϣ
		Product product = cartService.findProductByPid(pid);
		
		//3.��ѯ����Ʒ���ܽ��
		Double subTotal = buyNum*product.getShop_price();
		
		//4.��װ��CartItem
		cartItem.setProduct(product);
		cartItem.setBuyNums(buyNum);
		cartItem.setSubTotal(subTotal);
		
		//��Ϊ���ǹ��ﳵ�����û��ߣ���session��
		Cart cart = (Cart) ActionContext.getContext().getSession().get("cart");
		if(cart == null) {
			cart = new Cart();
		}
		
		//����Ҫ��cart��װ��
		Map<String, CartItem> cartItems = cart.getCartItems();
		
		//�����������������Ʒ�����ڵ����ӣ�����ֱ�Ӽ�
		if(cartItems.containsKey(product.getPid())) {
			//�Ȼ����Ʒ��
			CartItem item = cartItems.get(product.getPid());
			//�����޸�
			int buyNums = item.getBuyNums();
			buyNums += buyNum;
			item.setBuyNums(buyNums);
			
			double SubTotal = item.getSubTotal();
			SubTotal += subTotal;
			
			//������������
			SubTotal = (double)Math.round(SubTotal*100)/100;
			
			
			item.setSubTotal(SubTotal);
			
			//������Ż�map��
			cartItems.put(product.getPid(), item);
		}else {
			//���û�и���ֱ�ӷ�
			cartItems.put(product.getPid(), cartItem);
		}
		
		//���ﳵ���ܽ��
		double total = cart.getTotal();
		total = total +subTotal;
		
		total = (double)Math.round(total*100)/100;
		cart.setTotal(total);
		
		ActionContext.getContext().getSession().put("cart", cart);
		
		
		return "toCart";
		
	}





	public CartItem getModel() {
		// TODO Auto-generated method stub
		return cartItem;
	}

}
