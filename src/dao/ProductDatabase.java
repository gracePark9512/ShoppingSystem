package dao;

import domain.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author dinjo998
 */
public class ProductDatabase implements ProductDataInterface{
	private String url = "jdbc:h2:tcp://localhost:9088/project;IFEXISTS=TRUE";
	
	public ProductDatabase(){
	}
	
	public ProductDatabase(String url){
		this.url = url;
	}
	
	@Override
	public void saveProduct(Product product) {

    String sql="merge into product (product_id, name, description, category,"
				+ " price, quantity) values (?,?,?,?,?,?)";

		try (
				  // get connection to database
				  Connection dbCon = JdbcConnection.getConnection(url);
				  // create the statement
				  PreparedStatement stmt = dbCon.prepareStatement(sql);) {
			// copy the data from the student domain object into the SQL parameters
			stmt.setString(1, product.getProductID());
			stmt.setString(2, product.getName());
			stmt.setString(3, product.getDescription());
			stmt.setString(4, product.getCategory());
			stmt.setBigDecimal(5, product.getPrice());
			stmt.setBigDecimal(6, product.getQuantity());

			stmt.executeUpdate();  // execute the statement

		} catch (SQLException ex) {  // we are forced to catch SQLException
			// don't let the SQLException leak from our DAO encapsulation
			throw new DAOException(ex.getMessage(), ex);
		}
	}
	
	@Override
	public Collection<Product> getProducts() {

		String sql = "select * from product order by product_id";

		try (
				  // get a connection to the database
				  Connection dbCon = JdbcConnection.getConnection(url);
				  // create the statement
				  PreparedStatement stmt = dbCon.prepareStatement(sql);) {
			// execute the query
			ResultSet rs = stmt.executeQuery();

			// Using a List to preserve the order in which the data was returned from the query.
			List<Product> products = new ArrayList<>();

			// iterate through the query results
			while (rs.next()) {

				// get the data out of the query
				String productID = rs.getString("product_id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String category = rs.getString("category");
				BigDecimal price = rs.getBigDecimal("price");
				BigDecimal quantity = rs.getBigDecimal("quantity");

				// use the data to create a product object
				Product p = new Product(productID, name, description,
						  category, price, quantity);

				// and put it in the collection
				products.add(p);
			}
			
			return products;

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		}
	}

	@Override
	public Collection<String> getCategory() {
		String sql = "select distinct category from product order by category";

		try (
				  // get a connection to the database
				  Connection dbCon = JdbcConnection.getConnection(url);
				  // create the statement
				  PreparedStatement stmt = dbCon.prepareStatement(sql);) {
			// execute the query
			ResultSet rs = stmt.executeQuery();

			// Using a List to preserve the order in which the data was returned from the query.
			List<String> categories = new ArrayList<>();

			// iterate through the query results
			while (rs.next()) {

				// get the data out of the query
				String category = rs.getString("category");

				String c = category;

				categories.add(c);
			}

			return categories;

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		}
	}
	 
	@Override
	public void deleteProduct(Product product) {
		String sql = "delete from Product where product_id = (?)";

		try (
				  // get a connection to the database
				  Connection dbCon = JdbcConnection.getConnection(url);
				  // create the statement
				  PreparedStatement stmt = dbCon.prepareStatement(sql);) {
			
			stmt.setString(1, product.getProductID());
			
			stmt.executeUpdate();

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		}
	}

	@Override
	public Collection<Product> filterByCategory(String category) {
		String sql = "select * from product where category = (?)";

		try (
				  // get a connection to the database
				  Connection dbCon = JdbcConnection.getConnection(url);
				  // create the statement
				  PreparedStatement stmt = dbCon.prepareStatement(sql);) {
			
			stmt.setString(1, category);
			// execute the query
			ResultSet rs = stmt.executeQuery();
			

			// Using a List to preserve the order in which the data was returned from the query.
			List<Product> productsByCategory = new ArrayList<>();

			// iterate through the query results
			while (rs.next()) {

				// get the data out of the query
				String productID = rs.getString("product_id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				BigDecimal price = rs.getBigDecimal("price");
				BigDecimal quantity = rs.getBigDecimal("quantity");

				// use the data to create a product object
				Product p = new Product(productID, name, description,
						  category, price, quantity);

				// and put it in the collection
				productsByCategory.add(p);
			}
			
			return productsByCategory;

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		}
	}

	

	@Override
	public Product searchByID(String productID) {
		
    String sql = "select * from product where product_id = ?";

    try (
        // get connection to database
        Connection connection = JdbcConnection.getConnection(url);

        // create the statement
        PreparedStatement stmt = connection.prepareStatement(sql);
    ) {
        // set the parameter
        stmt.setString(1, productID);

        // execute the query
        ResultSet rs = stmt.executeQuery();

        // query only returns a single result, so use 'if' instead of 'while'
        if (rs.next()) {
            // get the data out of the query
				String name = rs.getString("name");
				String description = rs.getString("description");
				String category = rs.getString("category");
				BigDecimal price = rs.getBigDecimal("price");
				BigDecimal quantity = rs.getBigDecimal("quantity");

				// use the data to create a product object
				return new Product(productID, name, description,
						  category, price, quantity);

        } else {
            // no student matches given ID so return null
            return null;
        }

    } catch (SQLException ex) {  // we are forced to catch SQLException
        // don't let the SQLException leak from our DAO encapsulation
        throw new DAOException(ex.getMessage(), ex);
    }
	}
}
