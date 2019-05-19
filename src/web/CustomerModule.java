package web;

import dao.CustomerDAO;
import domain.Customer;
import org.jooby.Err;
import org.jooby.Jooby;
import org.jooby.Status;

/**
 *
 * @author dinjo998
 */
public class CustomerModule extends Jooby{
	
	private CustomerDAO customerDao;

	public CustomerModule(CustomerDAO dao) {
		this.customerDao = dao;
		get("/api/customers/:username", (req) -> {
			String username = req.param("username").value();
			if(customerDao.getCustomer(username) == null){
				throw new Err(Status.NOT_FOUND);
			}else{
				return customerDao.getCustomer(username);
			}
		});
		post("/api/register", (req, rsp) -> {
			Customer customer = req.body(Customer.class);
			customerDao.save(customer);
			rsp.status(Status.CREATED);
		});
		assets("/api/register", "createAccount.html");
	}
}
