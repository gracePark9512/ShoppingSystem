package dao;

import domain.Customer;

/**
 *
 * @author dinjo998
 */
public interface CustomerDAO {

	void save(Customer customer);

	Customer getCustomer(String userName);

	Boolean validateCredentials(String userName, String password);
}
