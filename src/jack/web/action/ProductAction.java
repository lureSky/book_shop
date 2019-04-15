package jack.web.action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import jack.domain.Category;
import jack.domain.Product;
import jack.pojo.PageBean;
import jack.service.ProductService;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product = new Product();
	private int cid;
	private String currentPageStr;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	private ProductService productService;
	
	public String home() throws Exception {
		//获得所有的热门ebook，转为json传给前台
		List<Product> hotEbookList = productService.getHotEbook();
		List<Product> hotCbookList = productService.getHotCbook();
		List<Product> newBookList = productService.getNewBook();
		
		
		ActionContext.getContext().getSession().put("hotEbookList", hotEbookList);
		ActionContext.getContext().getSession().put("hotCbookList", hotCbookList);
		ActionContext.getContext().getSession().put("newBookList", newBookList);
		
		return "toHome";
		
	}
	
	public String getList() throws Exception {
		//1.获得传来的cid,通过变量直接复制已经得到了
		//通过得到的CID获得所有的products
		
		String cname = productService.getCnameByCid(cid);
		
		//加一个分页技术的实现
		//因为我们从前台传来的数据获取的时候都是String类型的，所以我们用str
		String currentPageStr = ServletActionContext.getRequest().getParameter("currentPage");
		//如果当前页时空，说明是第一次访问，我们赋值为1
		if(currentPageStr==null) {
			//字符串处理加一
			currentPageStr = 1+"";
		}
		//转为int
		int currentPage = Integer.parseInt(currentPageStr);
		
	
		
		
		int curentCount = 6;//设置当前一页显示6条，传到后面
		PageBean pageBean = productService.findProductListByCid(cid,currentPage,curentCount);
		
		
		//我们转发即可，不用放在session，放在request中
		ActionContext.getContext().put("pageBean", pageBean);
		ActionContext.getContext().put("cname", cname);
		ActionContext.getContext().put("cid", cid);
		return "toProductList";
	}
	public String searchList() throws Exception {
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
		List<Product> list = productService.searchListByWord(word);
		
		//转为json数据，传回
		Gson gson = new Gson();
		//存在外键不允许使用
		String json = gson.toJson(list);
		System.out.println(json);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
		
		
		return null;
	}

	public String searchProductByPname() throws Exception {
		//1.前台得到pname
		String pname = ServletActionContext.getRequest().getParameter("pname");
		
		//转码
		pname = new String(pname.getBytes("ISO8859-1"),"UTF-8");
		//2.去数据库得到product
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.add(Restrictions.eq("pname", pname));
		Product p = productService.searchProductByPname(dc);
		
		
		int cid = p.getCategory().getCid();
		//3.通过cid获得cname
		String cname = productService.getCnameByCid(cid);
		//3.传入页面
		ActionContext.getContext().put("searchProduct", p);
		ActionContext.getContext().put("cname", cname);
		return "toProduct";
	}

	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCurrentPageStr() {
		return currentPageStr;
	}

	public void setCurrentPageStr(String currentPageStr) {
		this.currentPageStr = currentPageStr;
	}




	
	
	

	
	

}
