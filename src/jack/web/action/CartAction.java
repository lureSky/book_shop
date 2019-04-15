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
		
		//传来了一个pid
		String pid = ServletActionContext.getRequest().getParameter("pid");
		//我们的map是根据pid和cartItems放的
		//我们根据pid删除一条cartItems
		Cart cart = (Cart) ActionContext.getContext().getSession().get("cart");
		
		if(cart!= null) {
			Map<String, CartItem> cartItems = cart.getCartItems();
			
			//得获得该项的金额
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
		//只需要将购物车session清空即可
		ActionContext.getContext().getSession().put("cart", null);
		return "toHome";
	}



	public String addProductToCart() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		//1.先获得request中的pid
		String pid = request.getParameter("pid");
		String buyNumStr = request.getParameter("buyNum");
		int buyNum = Integer.parseInt(buyNumStr);
		
		//2.获取pid信息
		Product product = cartService.findProductByPid(pid);
		
		//3.查询该商品的总金额
		Double subTotal = buyNum*product.getShop_price();
		
		//4.封装进CartItem
		cartItem.setProduct(product);
		cartItem.setBuyNums(buyNum);
		cartItem.setSubTotal(subTotal);
		
		//因为我们购物车跟着用户走，放session中
		Cart cart = (Cart) ActionContext.getContext().getSession().get("cart");
		if(cart == null) {
			cart = new Cart();
		}
		
		//我们要把cart封装好
		Map<String, CartItem> cartItems = cart.getCartItems();
		
		//对其中如果包含有商品，我在点击添加，就是直接加
		if(cartItems.containsKey(product.getPid())) {
			//先获得商品项
			CartItem item = cartItems.get(product.getPid());
			//进行修改
			int buyNums = item.getBuyNums();
			buyNums += buyNum;
			item.setBuyNums(buyNums);
			
			double SubTotal = item.getSubTotal();
			SubTotal += subTotal;
			
			//在这四舍五入
			SubTotal = (double)Math.round(SubTotal*100)/100;
			
			
			item.setSubTotal(SubTotal);
			
			//将该项放回map中
			cartItems.put(product.getPid(), item);
		}else {
			//如果没有该项直接放
			cartItems.put(product.getPid(), cartItem);
		}
		
		//购物车的总金额
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
