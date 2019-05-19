package dao;

import domain.Product;
import java.util.Collection;

/**
 *
 * @author dinjo998
 */
public interface ProductDataInterface {

	void deleteProduct(Product product);

	Collection<Product> filterByCategory(String category);

	Collection<String> getCategory();

	Collection<Product> getProducts();

	void saveProduct(Product product);

	Product searchByID(String productID);
	
}
