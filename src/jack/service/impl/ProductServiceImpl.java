package jack.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import jack.dao.ProductDao;
import jack.domain.Category;
import jack.domain.Product;
import jack.pojo.PageBean;
import jack.service.ProductService;

public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	public List<Product> getHotEbook() {
		// TODO Auto-generated method stub
		return productDao.getHotEbook();
	}
	
	public List<Product> getHotCbook() {
		// TODO Auto-generated method stub
		return productDao.getHotCbook();
	}
	public List<Product> getNewBook() {
		// TODO Auto-generated method stub
		return productDao.getNewBook();
	}
	

	public List<Product> searchListByWord(String word) {
		// TODO Auto-generated method stub
		return productDao.searchListByWord(word);
	}
	public Product searchProductByPname(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return productDao.searchProductByPname(dc);
	}
	public String getCnameByCid(int cid) {
		// TODO Auto-generated method stub
		return productDao.getCnameByCid(cid);
	}
	public PageBean findProductListByCid(int  cid,int currentPage,int currentCount) {
		//在这里吧pageBean包装一下
		PageBean pageBean = new PageBean();
		
		//设置当前的起始数据位置
		int index = (currentPage-1)*currentCount;
		int totalCount = 0;
		//进行数据库访问，得到总条数
		totalCount = productDao.getTotalCount(cid);
		List<Product> list = (List<Product>)productDao.findProductListByPage(cid,index,currentCount);
	
		//包装总页数(需要去上线
		int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);
		
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentCount);
		pageBean.setList(list);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		
		return pageBean;
	}
	
	

}
