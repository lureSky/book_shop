package jack.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import jack.domain.User;


/**
 * 将公共的增删改查抽离出来
 * 因为是公共接口，所以我们要泛型化
 * @author C_F
 *
 */
public interface BaseDao<T>{

	//增或删
	void saveOrUpdate(T t);
	//增
	void save(T t);
	//删
	void delete(T t);
	//删（id）
	void delete(Serializable id);//id标识符
	//改
	void update(T t);
	//查
	T getById(Serializable id);
	//查总记录数
	Integer getTotalCount(DetachedCriteria dc);
	//查分页数据
	List<T> getPageList(DetachedCriteria dc,Integer start,Integer pageSize);
	
	//根据邮箱和密码进行校验
	User checkUserByPassword(User user);
}
