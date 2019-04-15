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
		//������е�����ebook��תΪjson����ǰ̨
		List<Product> hotEbookList = productService.getHotEbook();
		List<Product> hotCbookList = productService.getHotCbook();
		List<Product> newBookList = productService.getNewBook();
		
		
		ActionContext.getContext().getSession().put("hotEbookList", hotEbookList);
		ActionContext.getContext().getSession().put("hotCbookList", hotCbookList);
		ActionContext.getContext().getSession().put("newBookList", newBookList);
		
		return "toHome";
		
	}
	
	public String getList() throws Exception {
		//1.��ô�����cid,ͨ������ֱ�Ӹ����Ѿ��õ���
		//ͨ���õ���CID������е�products
		
		String cname = productService.getCnameByCid(cid);
		
		//��һ����ҳ������ʵ��
		//��Ϊ���Ǵ�ǰ̨���������ݻ�ȡ��ʱ����String���͵ģ�����������str
		String currentPageStr = ServletActionContext.getRequest().getParameter("currentPage");
		//�����ǰҳʱ�գ�˵���ǵ�һ�η��ʣ����Ǹ�ֵΪ1
		if(currentPageStr==null) {
			//�ַ��������һ
			currentPageStr = 1+"";
		}
		//תΪint
		int currentPage = Integer.parseInt(currentPageStr);
		
	
		
		
		int curentCount = 6;//���õ�ǰһҳ��ʾ6������������
		PageBean pageBean = productService.findProductListByCid(cid,currentPage,curentCount);
		
		
		//����ת�����ɣ����÷���session������request��
		ActionContext.getContext().put("pageBean", pageBean);
		ActionContext.getContext().put("cname", cname);
		ActionContext.getContext().put("cid", cid);
		return "toProductList";
	}
	public String searchList() throws Exception {
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
		List<Product> list = productService.searchListByWord(word);
		
		//תΪjson���ݣ�����
		Gson gson = new Gson();
		//�������������ʹ��
		String json = gson.toJson(list);
		System.out.println(json);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
		
		
		return null;
	}

	public String searchProductByPname() throws Exception {
		//1.ǰ̨�õ�pname
		String pname = ServletActionContext.getRequest().getParameter("pname");
		
		//ת��
		pname = new String(pname.getBytes("ISO8859-1"),"UTF-8");
		//2.ȥ���ݿ�õ�product
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.add(Restrictions.eq("pname", pname));
		Product p = productService.searchProductByPname(dc);
		
		
		int cid = p.getCategory().getCid();
		//3.ͨ��cid���cname
		String cname = productService.getCnameByCid(cid);
		//3.����ҳ��
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
