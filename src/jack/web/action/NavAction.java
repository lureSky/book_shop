package jack.web.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import jack.domain.Category;
import jack.service.NavService;
import jack.utils.JedisUtils;
import redis.clients.jedis.Jedis;



public class NavAction extends ActionSupport{
	private NavService navService;
	
	public String getCategory() throws Exception {
		//ʹ��Ĭ�Ϸ�����������еķ���
		//ʹ��redis  nosql���ݿ� 
		
		//���һ��response����
		HttpServletResponse response = ServletActionContext.getResponse();
		//1.���redis����
		Jedis jedis = JedisUtils.getJedis();
		//2.�ж�redis���Ƿ������category
		String categoryListJson = jedis.get("categoryListJson");
		//3.�ж��Ƿ����
		if(categoryListJson==null) {
			//����Ϊ�գ�����Ҫ����һ�����ݿ���뻺����
			List<Category> categoryList = navService.getCategorys();
			
			//תΪjson��ʽ
			Gson gson = new Gson();
			categoryListJson = gson.toJson(categoryList);
			//�Ż�jedis
			jedis.set("categoryListJson", categoryListJson);
			
		}
		//��Ϊ�գ�ֱ��ͨ��redis����ǰ̨
		//����ע��json�ĸ�ʽ
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(categoryListJson);
		
		return null;
	}
	public void setNavService(NavService navService) {
		this.navService = navService;
	}
	

}
