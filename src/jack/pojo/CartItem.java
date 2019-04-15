package jack.pojo;

import jack.domain.Product;

public class CartItem {
	//该bean对象主要是为了保存一条订单，一个购物车由若干个订单项组成
	
	private Product product;
	private int buyNums;
	private double subTotal;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getBuyNums() {
		return buyNums;
	}
	public void setBuyNums(int buyNums) {
		this.buyNums = buyNums;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
	

}
