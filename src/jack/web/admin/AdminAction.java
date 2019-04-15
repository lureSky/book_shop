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
		//登录
		System.out.println(user.getEmail());
		User adminUser = adminService.checkUserFromAdmin(user);
		String msg = null;
		if(adminUser==null) {
			msg="邮箱或密码输入错误";
			ActionContext.getContext().put("msg", msg);
			return "toAdminLogin";
		}
		ActionContext.getContext().getSession().put("adminUser", adminUser);
		
		return "toHome";
	}
	
	public String AddUser() throws Exception{
		
//		根据前台传值进行新建
		user.setUid(CommonUtils.getUUID());
		adminService.saveUser(user);
		
		return "toGetUserList";
	}
	public String logOut() throws Exception{
		//退出用户
		
		ActionContext.getContext().getSession().put("adminUser", "");
		return "toAdminLogin";
	}
	
	public String searchUserList() throws Exception{
		//已经传过来了一个word，我们用离线查询对象进行传值即可
		//利用request进行接收
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String word = request.getParameter("word");
		//1获得到前台的数据，没有乱码
		//2.建立离线查询放入
		/*DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		//前后模糊查询定义
		dc.add(Restrictions.like("pname", "%"+word+"%"));*/
		
		//我们只需要pname和pid不需要包装其他的
		List<Product> list = adminService.searchListByWord(word);
		
		//转为json数据，传回
		Gson gson = new Gson();
		//存在外键不允许使用
		String json = gson.toJson(list);
		System.out.println(json);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
				
				
		return null;
		
	}
	public String delUserByUid() throws Exception{
		
		//我们根据id去删除user
		String uid = ServletActionContext.getRequest().getParameter("uid");
		
		adminService.delUserByUid(uid);
		
		return "toGetUserList";
		
	}
	public String getUserList() throws Exception{
		//我们存在分页技术，所以我们要包装好一个PageBean
		//我们获取当前页
		String currentPageStr = ServletActionContext.getRequest().getParameter("currentPage");
		if(currentPageStr == null) {
			currentPageStr = 1+"";
		}
		//转int
		int currentPage = Integer.parseInt(currentPageStr);
		
		//设置一页显示多少
		int currentCount = 15;
		PageBean<User> pageBean = adminService.findUserList(currentPage,currentCount);
		
		
		//放入requset域中
		ActionContext.getContext().put("pageBean", pageBean);
		
		return "toMemberList";
	}
	public String searchUserByUserName() throws Exception{
		String username = ServletActionContext.getRequest().getParameter("username");
		//我们存在分页技术，所以我们要包装好一个PageBean
		//我们获取当前页
		String currentPageStr = ServletActionContext.getRequest().getParameter("currentPage");
		if(currentPageStr == null) {
			currentPageStr = 1+"";
		}
		//转int
		int currentPage = Integer.parseInt(currentPageStr);
		
		//设置一页显示多少
		int currentCount = 15;
		PageBean<User> pageBean = adminService.findUserListByUsername(username,currentPage,currentCount);
		
		
		//放入requset域中
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
