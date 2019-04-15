package jack.domain;

import java.util.Date;


public class Product {

	private String pid;
	private String pname;
	private double shop_price;
	private String pimage;
	private Date pdate;
	private int is_new;
	private int is_hot;
	private String pdesc;
	private int pflag;
	private String article;
	private String published;
	private String words;
	
	
	private Category category;


	public String getPid() {
		return pid;
	}


	public void setPid(String pid) {
		this.pid = pid;
	}


	public String getPname() {
		return pname;
	}


	public void setPname(String pname) {
		this.pname = pname;
	}


	public double getShop_price() {
		return shop_price;
	}


	public void setShop_price(double shop_price) {
		this.shop_price = shop_price;
	}


	public String getPimage() {
		return pimage;
	}


	public void setPimage(String pimage) {
		this.pimage = pimage;
	}


	public Date getPdate() {
		return pdate;
	}


	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}


	public int getIs_new() {
		return is_new;
	}


	public void setIs_new(int is_new) {
		this.is_new = is_new;
	}


	public int getIs_hot() {
		return is_hot;
	}


	public void setIs_hot(int is_hot) {
		this.is_hot = is_hot;
	}


	public String getPdesc() {
		return pdesc;
	}


	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}


	public int getPflag() {
		return pflag;
	}


	public void setPflag(int pflag) {
		this.pflag = pflag;
	}


	public String getArticle() {
		return article;
	}


	public void setArticle(String article) {
		this.article = article;
	}


	public String getPublished() {
		return published;
	}


	public void setPublished(String published) {
		this.published = published;
	}


	public String getWords() {
		return words;
	}


	public void setWords(String words) {
		this.words = words;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
