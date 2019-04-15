package jack.domain;

import java.util.HashSet;
import java.util.Set;

public class Category {

	private int cid;
	private String cname;
	private Set<Product> products = new HashSet<Product>();
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	
}
