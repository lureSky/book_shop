package jack.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import jack.dao.AdminDao;
import jack.domain.Product;
import jack.domain.User;
import jack.pojo.PageBean;
import jack.service.AdminService;

public class AdminServiceImpl implements AdminService{

	private AdminDao adminDao;
	public User checkUserFromAdmin(User user) {
		// TODO Auto-generated method stub
		return adminDao.checkUserFromAdmin(user);
	}
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	public PageBean<User> findUserList(int currentPage, int currentCount) {
		//包装好一个PageBean返回
		PageBean pageBean = new PageBean();
		
		//设置数据初始位置
		int index = (currentPage-1)*currentCount;
		int totalCount = 0;
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		totalCount = adminDao.getTotalCount(dc);
		
		List<User> list = adminDao.findUserList(dc,index,currentCount);
		
		//包装总页数
		int totalPage = (int)Math.ceil(1.0*totalCount/currentCount);
		
		//将pageBean进行包装
		pageBean.setCurrentCount(currentCount);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		
		
		
		return pageBean;
	}
	public List<Product> searchListByWord(String word) {
		// TODO Auto-generated method stub
		return adminDao.searchListByWord(word);
	}
	public void delUserByUid(String uid) {
		//删除
		adminDao.delUserByUid(uid);
		
	}
	public PageBean<User> findUserListByUsername(String username, int currentPage, int currentCount) {
		//包装好一个PageBean返回
		PageBean pageBean = new PageBean();
		
		//设置数据初始位置
		int index = (currentPage-1)*currentCount;
		int totalCount = 0;
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("username", username));
		totalCount = adminDao.getTotalCount(dc);
		
		List<User> list = adminDao.findUserList(dc,index,currentCount);
		
		//包装总页数
		int totalPage = (int)Math.ceil(1.0*totalCount/currentCount);
		
		//将pageBean进行包装
		pageBean.setCurrentCount(currentCount);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		
		
		
		return pageBean;
	}
	public void saveUser(User user) {
		adminDao.saveUser(user);
		
	}
	
	

}
