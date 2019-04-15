package jack.service.impl;

import java.util.List;

import jack.dao.NavDao;
import jack.domain.Category;
import jack.service.NavService;

public class NavServiceImpl implements NavService{

	private NavDao navDao;
	
	public List<Category> getCategorys() {
		// TODO Auto-generated method stub
		return navDao.getCategorys();
	}
	public void setNavDao(NavDao navDao) {
		this.navDao = navDao;
	}
}
