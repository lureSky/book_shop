package jack.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import jack.domain.Category;
import jack.domain.Product;
import jack.pojo.PageBean;

public interface ProductService {

	List<Product> getHotEbook();

	List<Product> getHotCbook();

	List<Product> getNewBook();

	List<Product> searchListByWord(String word);

	Product searchProductByPname(DetachedCriteria dc);

	String getCnameByCid(int cid);

	PageBean findProductListByCid(int cid, int currentPage, int curentCount);

	

}
