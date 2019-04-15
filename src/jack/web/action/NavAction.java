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
		//使用默认方法，获得所有的分类
		//使用redis  nosql数据库 
		
		//获得一个response对象
		HttpServletResponse response = ServletActionContext.getResponse();
		//1.获得redis对象
		Jedis jedis = JedisUtils.getJedis();
		//2.判断redis中是否存在了category
		String categoryListJson = jedis.get("categoryListJson");
		//3.判断是否存在
		if(categoryListJson==null) {
			//缓存为空，我们要访问一次数据库放入缓存中
			List<Category> categoryList = navService.getCategorys();
			
			//转为json格式
			Gson gson = new Gson();
			categoryListJson = gson.toJson(categoryList);
			//放回jedis
			jedis.set("categoryListJson", categoryListJson);
			
		}
		//不为空，直接通过redis传回前台
		//我们注意json的格式
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(categoryListJson);
		
		return null;
	}
	public void setNavService(NavService navService) {
		this.navService = navService;
	}
	

}
