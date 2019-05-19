package domain;

import java.math.BigDecimal;
import java.util.*;

/**
 *
 * @author dinjo998
 */
public class Sale {
	private String orderID;
	private Customer customer;
	private final List<SaleItem> items = new ArrayList<>();

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public BigDecimal getTotal(){
		BigDecimal saleTotal = BigDecimal.ZERO;
		for(SaleItem i :items){
			saleTotal = saleTotal.add(i.getItemTotal());
		}
		return saleTotal;
	}
	
	public void addItem(SaleItem orderItem){
		items.add(orderItem);
	}
}

