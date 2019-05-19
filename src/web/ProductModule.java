package web;

import dao.ProductDataInterface;
import org.jooby.Jooby;

/**
 *
 * @author dinjo998
 */
public class ProductModule extends Jooby {

	private ProductDataInterface productDao;

	public ProductModule(ProductDataInterface dao) {
		this.productDao = dao;
		get("/api/products", () -> productDao.getProducts());
		get("/api/products/:id", (req) -> {
			String id = req.param("id").value();
			return productDao.searchByID(id);
		});
		get("/api/categories", () -> productDao.getCategory());
		get("/api/categories/:category", (req) -> {
			String category = req.param("category").value();
			return productDao.filterByCategory(category);
		});
	}
}
