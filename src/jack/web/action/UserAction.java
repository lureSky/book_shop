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
		//�Ѿ���װ����user
		//��̨���漴��
		user.setUid(UUID.randomUUID().toString());
		
		//��Ƽ�����
		user.setCode(UUID.randomUUID().toString());
		
		userService.save(user);
		
		return "toLogin";
	}
	public String forgetPassword() throws Exception{
		//�յ������У����
		//��֤����ȷ�����
		String email = ServletActionContext.getRequest().getParameter("email");
		
		User forgotUser = userService.getUserByEmail(email);
		
		ActionContext.getContext().put("forgotUser", forgotUser);
		
		return "toForgot";
		
		
	}
	public String updatePassword() throws Exception{
		
		
		//�õ�����
		
		userService.updatePassByEmail(user);	
		
		
		return "toLogin";
	}
	public String checkEmail() throws Exception{
		//���response
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		//ȷ��usernameû����
		//1.ǰ̨������username�����ݣ����Ƿ������ݿ⿴��username�Ƿ��Ѿ����� username�Ѿ���װ��user����
		String email = request.getParameter("email");
		System.out.println(email);
		boolean isExist = userService.checkEmail(email);
		//д������
		Gson gson = new Gson();
		String json = gson.toJson(isExist);
		System.out.println(json);
		
		//д��
		response.getWriter().write(json);
	
		return null;
		
	}
	public String login() throws Exception{
		//��¼
		user = userService.checkUserByPassword(user);
		String msg = null;
		if(user==null) {
			msg="����������������";
			ActionContext.getContext().put("msg", msg);
			return "toLogin";
		}
		ActionContext.getContext().getSession().put("user", user);
		
		return "toHome";
	}
	public String logout() throws Exception{
		//�˳�ֻ��Ҫ���session����Ȼ�󷵻���ҳ����
		ActionContext.getContext().getSession().put("user", null);
		//�˳���Ҳ�ѹ��ﳵ������
		ActionContext.getContext().getSession().put("cart", null);
		return "toHome";
	}


	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

}
