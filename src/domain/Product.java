package domain;

import java.math.BigDecimal;
import java.util.Objects;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNegative;
import net.sf.oval.constraint.NotNull;

/**
 *
 * @author dinjo998
 */
public class Product implements Comparable<Product>{
	@NotNull(message = "Product ID must be provided.")
	@NotBlank(message = "Product ID must be provided.")
	private String productID;
	@NotNull(message = "Name must be provided.")
	@NotBlank(message = "Name must be provided.")
	@Length(min=2, message= "Name must contain at least two characters.")
	private String name;
	@NotNull(message = "Description must be provided.")
	@NotBlank(message = "Description must be provided.")
	private String description;
	@NotNull(message = "Category must be provided.")
	@NotBlank(message = "Category must be provided.")
	private String category;
	@NotNull(message = "Price must be provided.")
	@NotNegative(message = "Price must be zero or greater.")
	private BigDecimal price;
	@NotNull(message = "Quantity must be provided.")
	@NotNegative(message = "Quantity must be zero or greater.")
	private BigDecimal quantity;

	public Product(String productID, String name, String description, String category, BigDecimal price, BigDecimal quantity) {
		this.productID = productID;
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
	}

	public Product() {
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return productID + ", " + name;
	}

	@Override
	public int compareTo(Product o) {
		String myProductID = this.getProductID();
		String theirProductID = o.getProductID();
		return myProductID.compareTo(theirProductID);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 29 * hash + Objects.hashCode(this.productID);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Product other = (Product) obj;
		if (!Objects.equals(this.productID, other.productID)) {
			return false;
		}
		return true;
	}
	
	
}
