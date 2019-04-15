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
		//��װ��һ��PageBean����
		PageBean pageBean = new PageBean();
		
		//�������ݳ�ʼλ��
		int index = (currentPage-1)*currentCount;
		int totalCount = 0;
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		totalCount = adminDao.getTotalCount(dc);
		
		List<User> list = adminDao.findUserList(dc,index,currentCount);
		
		//��װ��ҳ��
		int totalPage = (int)Math.ceil(1.0*totalCount/currentCount);
		
		//��pageBean���а�װ
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
		//ɾ��
		adminDao.delUserByUid(uid);
		
	}
	public PageBean<User> findUserListByUsername(String username, int currentPage, int currentCount) {
		//��װ��һ��PageBean����
		PageBean pageBean = new PageBean();
		
		//�������ݳ�ʼλ��
		int index = (currentPage-1)*currentCount;
		int totalCount = 0;
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("username", username));
		totalCount = adminDao.getTotalCount(dc);
		
		List<User> list = adminDao.findUserList(dc,index,currentCount);
		
		//��װ��ҳ��
		int totalPage = (int)Math.ceil(1.0*totalCount/currentCount);
		
		//��pageBean���а�װ
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
