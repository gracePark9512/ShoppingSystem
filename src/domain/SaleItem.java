package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author dinjo998
 */
public class SaleItem {
	private BigDecimal quantityPurchased;
	private BigDecimal purchasePrice;
	private Sale sale;
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public BigDecimal getQuantityPurchased() {
		return quantityPurchased;
	}

	public void setQuantityPurchased(BigDecimal quantityPurchased) {
		this.quantityPurchased = quantityPurchased;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	public BigDecimal getItemTotal(){
		return purchasePrice.divide(quantityPurchased, 2, RoundingMode.HALF_UP);
	}
}
