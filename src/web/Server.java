package web;

import dao.CustomerCollectionsDAO;
import dao.CustomerDAO;
import dao.CustomerDatabase;
import dao.ProductDataInterface;
import dao.ProductDatabase;
import org.jooby.Jooby;
import org.jooby.json.Gzon;

/**
 *
 * @author dinjo998
 */
public class Server extends Jooby {
	private ProductDataInterface productDao = new ProductDatabase();
	private CustomerDAO customerDao = new CustomerDatabase();
	
	public Server() {
		port(3268);
		use(new Gzon().doWith(gson -> {
			gson.setPrettyPrinting();
		}));
		use(new ProductModule(productDao));
		use(new CustomerModule(customerDao));
		assets("/**");
		assets("/", "index.html");

	}
		
	public static void main(String[] args) {
		new Server().start();
	}
}
