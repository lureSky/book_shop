package jack.service.impl;

import jack.dao.CartDao;
import jack.domain.Product;
import jack.service.CartService;

public class CartServiceImpl implements CartService {

	private CartDao cartDao;
	public Product findProductByPid(String pid) {
		return cartDao.findProductByPid(pid);
	}
	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}
	
	

}
