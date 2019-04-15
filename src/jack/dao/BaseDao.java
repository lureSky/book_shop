package jack.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import jack.domain.User;


/**
 * ����������ɾ�Ĳ�������
 * ��Ϊ�ǹ����ӿڣ���������Ҫ���ͻ�
 * @author C_F
 *
 */
public interface BaseDao<T>{

	//����ɾ
	void saveOrUpdate(T t);
	//��
	void save(T t);
	//ɾ
	void delete(T t);
	//ɾ��id��
	void delete(Serializable id);//id��ʶ��
	//��
	void update(T t);
	//��
	T getById(Serializable id);
	//���ܼ�¼��
	Integer getTotalCount(DetachedCriteria dc);
	//���ҳ����
	List<T> getPageList(DetachedCriteria dc,Integer start,Integer pageSize);
	
	//����������������У��
	User checkUserByPassword(User user);
}
