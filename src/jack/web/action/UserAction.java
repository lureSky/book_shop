package jack.web.action;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import jack.domain.User;
import jack.service.UserService;
import jack.utils.CommonUtils;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user = new User();
	private UserService userService;
	
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String register() throws Exception{
		//已经封装好了user
		//后台保存即可
		user.setUid(UUID.randomUUID().toString());
		
		//设计激活码
		user.setCode(UUID.randomUUID().toString());
		
		userService.save(user);
		
		return "toLogin";
	}
	public String forgetPassword() throws Exception{
		//收到邮箱的校验码
		//验证码正确后进入
		String email = ServletActionContext.getRequest().getParameter("email");
		
		User forgotUser = userService.getUserByEmail(email);
		
		ActionContext.getContext().put("forgotUser", forgotUser);
		
		return "toForgot";
		
		
	}
	public String updatePassword() throws Exception{
		
		
		//得到对象
		
		userService.updatePassByEmail(user);	
		
		
		return "toLogin";
	}
	public String checkEmail() throws Exception{
		//获得response
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		//确定username没问题
		//1.前台传来了username的数据，我们访问数据库看该username是否已经存在 username已经封装到user中了
		String email = request.getParameter("email");
		System.out.println(email);
		boolean isExist = userService.checkEmail(email);
		//写回数据
		Gson gson = new Gson();
		String json = gson.toJson(isExist);
		System.out.println(json);
		
		//写回
		response.getWriter().write(json);
	
		return null;
		
	}
	public String login() throws Exception{
		//登录
		user = userService.checkUserByPassword(user);
		String msg = null;
		if(user==null) {
			msg="邮箱或密码输入错误";
			ActionContext.getContext().put("msg", msg);
			return "toLogin";
		}
		ActionContext.getContext().getSession().put("user", user);
		
		return "toHome";
	}
	public String logout() throws Exception{
		//退出只需要清除session，，然后返回主页即可
		ActionContext.getContext().getSession().put("user", null);
		//退出我也把购物车给清了
		ActionContext.getContext().getSession().put("cart", null);
		return "toHome";
	}


	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

}
