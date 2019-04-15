package jack.dao;

import jack.domain.Product;
import jack.pojo.Cart;

public interface CartDao{

	Product findProductByPid(String pid);

}
