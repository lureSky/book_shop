package jack.pojo;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable {
	//��ҳ��5Ҫ��
	private int currentPage;//��ǰҳ
	private int totalPage;	//��ҳ��
	private int currentCount;//��ǰ����
	private int totalCount;	//������
	
	private List<T> list;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	
	

}
