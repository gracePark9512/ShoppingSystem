package dao;

import domain.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author dinjo998
 */
public class CustomerDatabase implements CustomerDAO{
	private String url = "jdbc:h2:tcp://localhost:9088/project;IFEXISTS=TRUE";
	
	public CustomerDatabase(){
	}
	
	public CustomerDatabase(String url){
		this.url = url;
	}

	@Override
	public void save(Customer customer) {
    String sql="merge into customer (username, password, name, email,"
				+ " address, creditcard) values (?,?,?,?,?,?)";

		try (
				  // get connection to database
				  Connection dbCon = JdbcConnection.getConnection(url);
				  // create the statement
				  PreparedStatement stmt = dbCon.prepareStatement(sql);) {
			// copy the data from the student domain object into the SQL parameters
			stmt.setString(1, customer.getUsername());
			stmt.setString(2, customer.getPassword());
			stmt.setString(3, customer.getName());
			stmt.setString(4, customer.getEmail());
			stmt.setString(5, customer.getAddress());
			stmt.setString(6, customer.getCreditCard());

			stmt.executeUpdate();  // execute the statement

		} catch (SQLException ex) {  // we are forced to catch SQLException
			// don't let the SQLException leak from our DAO encapsulation
			throw new DAOException(ex.getMessage(), ex);
		}
	}

	@Override
	public Customer getCustomer(String username) {
			
    String sql = "select * from customer where username = ?";

    try (
        // get connection to database
        Connection connection = JdbcConnection.getConnection(url);

        // create the statement
        PreparedStatement stmt = connection.prepareStatement(sql);
    ) {
        // set the parameter
        stmt.setString(1, username);

        // execute the query
        ResultSet rs = stmt.executeQuery();

        // query only returns a single result, so use 'if' instead of 'while'
        if (rs.next()) {
            // get the data out of the query
				String password = rs.getString("password");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String creditCard = rs.getString("creditCard");

				// use the data to create a customer object
				return new Customer(username, password, name, email, address,
				creditCard);

        } else {
            // no customer matches given username so return null
            return null;
        }

    } catch (SQLException ex) {  // we are forced to catch SQLException
        // don't let the SQLException leak from our DAO encapsulation
        throw new DAOException(ex.getMessage(), ex);
    }
	}

	@Override
	public Boolean validateCredentials(String username, String password) {
				
    String sql = "select * from customer where username = ? password = ?";

    try (
        // get connection to database
        Connection connection = JdbcConnection.getConnection(url);

        // create the statement
        PreparedStatement stmt = connection.prepareStatement(sql);
    ) {
        // set the parameter
        stmt.setString(1, username);
		  stmt.setString(2, password);

        // execute the query
        ResultSet rs = stmt.executeQuery();

        // query only returns a single result, so use 'if' instead of 'while'
        if (rs.next()) {
				return true;

        } else {
            return false;
        }

    } catch (SQLException ex) {  // we are forced to catch SQLException
        // don't let the SQLException leak from our DAO encapsulation
        throw new DAOException(ex.getMessage(), ex);
    }
	}
	
	
}
