package jack.web.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import jack.domain.Product;
import jack.domain.User;
import jack.pojo.PageBean;
import jack.service.AdminService;
import jack.utils.CommonUtils;

public class AdminAction extends ActionSupport implements ModelDriven<User>{
	private User user = new User();
	private AdminService adminService;
	
	public String login() throws Exception{
		//��¼
		System.out.println(user.getEmail());
		User adminUser = adminService.checkUserFromAdmin(user);
		String msg = null;
		if(adminUser==null) {
			msg="����������������";
			ActionContext.getContext().put("msg", msg);
			return "toAdminLogin";
		}
		ActionContext.getContext().getSession().put("adminUser", adminUser);
		
		return "toHome";
	}
	
	public String AddUser() throws Exception{
		
//		����ǰ̨��ֵ�����½�
		user.setUid(CommonUtils.getUUID());
		adminService.saveUser(user);
		
		return "toGetUserList";
	}
	public String logOut() throws Exception{
		//�˳��û�
		
		ActionContext.getContext().getSession().put("adminUser", "");
		return "toAdminLogin";
	}
	
	public String searchUserList() throws Exception{
		//�Ѿ���������һ��word�����������߲�ѯ������д�ֵ����
		//����request���н���
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String word = request.getParameter("word");
		//1��õ�ǰ̨�����ݣ�û������
		//2.�������߲�ѯ����
		/*DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		//ǰ��ģ����ѯ����
		dc.add(Restrictions.like("pname", "%"+word+"%"));*/
		
		//����ֻ��Ҫpname��pid����Ҫ��װ������
		List<Product> list = adminService.searchListByWord(word);
		
		//תΪjson���ݣ�����
		Gson gson = new Gson();
		//�������������ʹ��
		String json = gson.toJson(list);
		System.out.println(json);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
				
				
		return null;
		
	}
	public String delUserByUid() throws Exception{
		
		//���Ǹ���idȥɾ��user
		String uid = ServletActionContext.getRequest().getParameter("uid");
		
		adminService.delUserByUid(uid);
		
		return "toGetUserList";
		
	}
	public String getUserList() throws Exception{
		//���Ǵ��ڷ�ҳ��������������Ҫ��װ��һ��PageBean
		//���ǻ�ȡ��ǰҳ
		String currentPageStr = ServletActionContext.getRequest().getParameter("currentPage");
		if(currentPageStr == null) {
			currentPageStr = 1+"";
		}
		//תint
		int currentPage = Integer.parseInt(currentPageStr);
		
		//����һҳ��ʾ����
		int currentCount = 15;
		PageBean<User> pageBean = adminService.findUserList(currentPage,currentCount);
		
		
		//����requset����
		ActionContext.getContext().put("pageBean", pageBean);
		
		return "toMemberList";
	}
	public String searchUserByUserName() throws Exception{
		String username = ServletActionContext.getRequest().getParameter("username");
		//���Ǵ��ڷ�ҳ��������������Ҫ��װ��һ��PageBean
		//���ǻ�ȡ��ǰҳ
		String currentPageStr = ServletActionContext.getRequest().getParameter("currentPage");
		if(currentPageStr == null) {
			currentPageStr = 1+"";
		}
		//תint
		int currentPage = Integer.parseInt(currentPageStr);
		
		//����һҳ��ʾ����
		int currentCount = 15;
		PageBean<User> pageBean = adminService.findUserListByUsername(username,currentPage,currentCount);
		
		
		//����requset����
		ActionContext.getContext().put("pageBean", pageBean);
		
		return "toMemberList";
	}
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	

}
