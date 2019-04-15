package jack.service;

import jack.domain.Product;

public interface CartService {
	Product findProductByPid(String pid);
}
