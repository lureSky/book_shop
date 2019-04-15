package jack.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import jack.domain.Category;
import jack.domain.Product;

public interface ProductDao {

	List<Product> getHotEbook();

	List<Product> getHotCbook();

	List<Product> getNewBook();

	List<Product> searchListByWord(String word);

	Product searchProductByPname(DetachedCriteria dc);

	String getCnameByCid(int cid);

	int getTotalCount(int cid);

	List<Product> findProductListByPage(int cid, int index, int currentCount);

	

}
