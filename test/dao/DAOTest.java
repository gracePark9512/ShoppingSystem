package dao;

import domain.Product;
import java.math.BigDecimal;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dinjo998
 */
public class DAOTest {
//	private final ProductDataInterface dao = new ProductDatabase("jdbc:h2:tcp://localhost:9088/project-testing");
	private final ProductDataInterface dao = new ProductStore();
	private Product prodOne;
	private Product prodTwo;
	private Product prodThree;
	
	public DAOTest() {
	}
	
	@Test
	public void testDaoSave() {
// save the product using DAO
		dao.saveProduct(prodThree);
// retrieve the same product via DAO
		Product retrieved = dao.searchByID("3");
// ensure that the product we saved is the one we got back
		assertEquals("Retrieved product should be the same",
				  prodThree, retrieved);
	}
	
	@Test
	public void testDaoDelete() {
// delete the product via the DAO
		dao.deleteProduct(prodOne);
// try to retrieve the deleted product
		Product retrieved = dao.searchByID("1");
// ensure that the student was not retrieved (should be null)
		assertNull("Product should no longer exist", retrieved);
	}
	
	@Test
	public void testDaoEdit() {
		this.prodOne.setName("name10");
		dao.saveProduct(prodOne);
		Product retrieved = dao.searchByID("1");
		assertEquals("Retrieved product should be the same",
				  "name10", retrieved.getName());
	}

	
	@Test
	public void testDaoGetAll() {
		Collection<Product> products = dao.getProducts();
// ensure the result includes the two saved products
		assertTrue("prodOne should exist", products.contains(prodOne));
		assertTrue("prodTwo should exist", products.contains(prodTwo));
// ensure the result ONLY includes the two saved products
		assertEquals("Only 2 products in result", 2, products.size());
// find prodOne - result is not a map, so we have to scan for it
		for (Product p : products) {
			if (p.equals(prodOne)) {
// ensure that all of the details were correctly retrieved
				assertEquals(prodOne.getProductID(), p.getProductID());
				assertEquals(prodOne.getName(), p.getName());
				assertEquals(prodOne.getDescription(), p.getDescription());
				assertEquals(prodOne.getCategory(), p.getCategory());
				assertEquals(prodOne.getPrice(), p.getPrice());
				assertEquals(prodOne.getQuantity(), p.getQuantity());
			}
		}
	}
	
	@Test
	public void testDaoProductsByCategory() {
		Collection<Product> products = dao.filterByCategory("cat2");
// ensure the result includes the two saved products
		assertTrue("prodTwo should exist", products.contains(prodTwo));
// ensure the result ONLY includes the two saved products
		assertEquals("Only 1 product in result", 1, products.size());
// find prodOne - result is not a map, so we have to scan for it
		for (Product p : products) {
			if (p.equals(prodTwo)) {
// ensure that all of the details were correctly retrieved
				assertEquals(prodTwo.getProductID(), p.getProductID());
				assertEquals(prodTwo.getName(), p.getName());
				assertEquals(prodTwo.getDescription(), p.getDescription());
				assertEquals(prodTwo.getCategory(), p.getCategory());
				assertEquals(prodTwo.getPrice(), p.getPrice());
				assertEquals(prodTwo.getQuantity(), p.getQuantity());
			}
		}
	}
	
	@Test
	public void testDaoFindById() {
		// get prodOne using findById method
	Product retrieved = dao.searchByID("1");
		// ensure that you got back prodOne, and not another product
		assertEquals(prodOne, retrieved);
		// ensure that prodOne's details were properly retrieved
		assertEquals(prodOne.getProductID(), retrieved.getProductID());
		assertEquals(prodOne.getName(), retrieved.getName());
		assertEquals(prodOne.getDescription(), retrieved.getDescription());
		assertEquals(prodOne.getCategory(), retrieved.getCategory());
		assertEquals(prodOne.getPrice(), retrieved.getPrice());
		assertEquals(prodOne.getQuantity(), retrieved.getQuantity());
		// call findById using a non-existent ID
		retrieved = dao.searchByID("4");
		// ensure that the result is null
		assertEquals(null, retrieved);
	}
	
	@Test
	public void testDaoGetCategory() {
		Collection<String> categories = dao.getCategory();
		assertTrue("cat1 should exist", categories.contains("cat1"));
		assertTrue("cat2 should exist", categories.contains("cat2"));
		assertEquals("Only 2 categories in result", 2, categories.size());
	}

	@Before
	public void setUp() {
		this.prodOne = new Product("1", "name1", "desc1", "cat1", 
				  new BigDecimal("11.00"), new BigDecimal("22.00"));
		this.prodTwo = new Product("2", "name2", "desc2", "cat2", 
				  new BigDecimal("33.00"), new BigDecimal("44.00"));
		this.prodThree = new Product("3", "name3", "desc3", "cat3", 
				  new BigDecimal("55.00"), new BigDecimal("66.00"));
// save the products
		dao.saveProduct(prodOne);
		dao.saveProduct(prodTwo);
// Note: Intentionally not saving prodThree
	}
	
	@After
	public void tearDown() {
		dao.deleteProduct(prodOne);
		dao.deleteProduct(prodTwo);
		dao.deleteProduct(prodThree);
	}
	
}
