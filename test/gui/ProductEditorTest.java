package gui;

import dao.ProductStore;
import domain.Product;
import gui.helpers.SimpleListModel;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.TreeSet;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import static org.assertj.swing.core.matcher.DialogMatcher.withTitle;
import static org.assertj.swing.core.matcher.JButtonMatcher.withText;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class ProductEditorTest {

	private ProductStore dao;
	private DialogFixture fixture;
	private Robot robot;

	@Before
	public void setUp() {
		robot = BasicRobot.robotWithNewAwtHierarchy();

		// Slow down the robot a bit - default is 30 (milliseconds).
		// Do not make it less than 5 or you will have thread-race problems.
		robot.settings().delayBetweenEvents(20);

		// add some categories for testing with
		Collection<String> categories = new TreeSet<>();
		categories.add("cat1");
		categories.add("cat2");
		categories.add("cat3");
		
		//add some products for testing with
		Collection<Product> products = new TreeSet<>();
		
		// products to view
		Product prod2 = new Product("2", "prod2", "Delete test.", "cat2", 
				  new BigDecimal("20.00"), new BigDecimal("20.00"));
		Product prod3 = new Product("3", "prod3", "View test.", "cat3", 
				  new BigDecimal("10.00"), new BigDecimal("10.00"));
		products.add(prod2);
		products.add(prod3);
				
		// create a mock for the DAO
		dao = mock(ProductStore.class);

		// stub the getCategories method to return the test categories
		when(dao.getCategory()).thenReturn(categories);
		
		// stub the getProducts method
		when(dao.getProducts()).thenReturn(products);
		
		// stub the searchByID method
		when(dao.searchByID("3")).thenReturn(prod3);
		
		//return products by category
		Collection<Product> productsByCategory = new TreeSet<>();
		productsByCategory.add(prod2);
		
		// stub the filterByCategory method
		when(dao.filterByCategory("cat2")).thenReturn(productsByCategory);
		
		// stub the deleteProduct method
		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				products.remove(prod2);
				return null;
			}
		}).when(dao).deleteProduct(prod2);

	}

	@After
	public void tearDown() {
		// clean up fixture so that it is ready for the next test
		fixture.cleanUp();
	}
	
	@Test
	public void testSave() {
		// create the dialog passing in the mocked DAO
		ProductEditor editor = new ProductEditor(null, true, dao);

		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, editor);
		fixture.show().requireVisible();

		// enter some details into the UI components
		fixture.textBox("txtID").enterText("1");
		fixture.textBox("txtName").enterText("prod1");
		fixture.textBox("txtDescription").enterText("Save test.");
		fixture.comboBox("txtCategory").selectItem("cat1");
		fixture.textBox("txtPrice").enterText("10.55");
		fixture.textBox("txtQuantity").enterText("10.00");


		// click the save button
		fixture.button("saveButton").click();

		// create a Mockito argument captor to use to retrieve the passed product from the mocked DAO
		ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

		// verify that the DAO.saveProduct method was called, and capture the passed product
		verify(dao).saveProduct(argument.capture());

		// retrieve the passed product from the captor
		Product savedProduct = argument.getValue();

		// test that the product's details were properly saved
		assertEquals("Ensure the ID was saved", "1", savedProduct.getProductID());
		assertEquals("Ensure the name was saved", "prod1", savedProduct.getName());
		assertEquals("Ensure the description was saved", "Save test.", savedProduct.getDescription());
		assertEquals("Ensure the category was saved", "cat1", savedProduct.getCategory());
		assertEquals("Ensure the price was saved", new BigDecimal("10.55"), savedProduct.getPrice());
		assertEquals("Ensure the quantity was saved", new BigDecimal("10"), savedProduct.getQuantity());
	}
	
	@Test
	public void testEdit() {
		// a product to edit
		Product prod1 = new Product("1", "prod1", "Edit test.", "cat1", 
				  new BigDecimal("10.55"), new BigDecimal("10.00"));

		// create dialog passing in product and mocked DAO
		ProductEditor editor = new ProductEditor(null, true, prod1, dao);

		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, editor);

		// show the dialog on the screen, and ensure it is visible
		fixture.show().requireVisible();

		// verify that the UI componenents contains the product's details
		fixture.textBox("txtID").requireText("1");
		fixture.textBox("txtName").requireText("prod1");
		fixture.textBox("txtDescription").requireText("Edit test.");
		fixture.comboBox("txtCategory").requireSelection("cat1");
		fixture.textBox("txtPrice").requireText("10.55");
		fixture.textBox("txtQuantity").requireText("10.00");

		// edit the name and category
		fixture.textBox("txtName").selectAll().deleteText().enterText("product01");
		fixture.textBox("txtDescription").selectAll().deleteText().enterText("Testing edit.");
		fixture.comboBox("txtCategory").selectItem("cat2");
		fixture.textBox("txtPrice").selectAll().deleteText().enterText("15.55");
		fixture.textBox("txtQuantity").selectAll().deleteText().enterText("20.00");

		// click the save button
		fixture.button("saveButton").click();

		// create a Mockito argument captor to use to retrieve the passed product from the mocked DAO
		ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

		// verify that the DAO.saveProduct method was called, and capture the passed product
		verify(dao).saveProduct(argument.capture());

		// retrieve the passed product from the captor
		Product editedProduct = argument.getValue();

		// check that the changes were saved
		assertEquals("Ensure the name was changed", "product01", editedProduct.getName());
		assertEquals("Ensure the description was changed", "Testing edit.", editedProduct.getDescription());
		assertEquals("Ensure the category was changed", "cat2", editedProduct.getCategory());
		assertEquals("Ensure the price was changed", new BigDecimal("15.55"), editedProduct.getPrice());
		assertEquals("Ensure the quantity was changed", new BigDecimal("20"), editedProduct.getQuantity());
	}

	@Test
	public void testView() {
		Product prod2 = new Product("2", "prod2", "Delete test.", "cat2", 
				  new BigDecimal("20.00"), new BigDecimal("20.00"));
		Product prod3 = new Product("3", "prod3", "View test.", "cat3", 
				  new BigDecimal("10.00"), new BigDecimal("10.00"));
		
		// create dialog passing in product and mocked DAO
		ProductReport report = new ProductReport(null, true, dao);

		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, report);

		// show the dialog on the screen, and ensure it is visible
		fixture.show().requireVisible();
		
		// get the model
		SimpleListModel model = (SimpleListModel) fixture.list("productList").target().getModel();

		// check the contents
		assertTrue("list contains the expected product", model.contains(prod2));
		assertTrue("list contains the expected product", model.contains(prod3));
		assertEquals("list contains the correct number of products", 2, model.getSize());
	}
	
	@Test
	public void testDelete() {
		Product prod2 = new Product("2", "prod2", "Delete test.", "cat2", 
				  new BigDecimal("20.00"), new BigDecimal("20.00"));
		
		// create dialog passing in product and mocked DAO
		ProductReport report = new ProductReport(null, true, dao);

		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, report);

		// show the dialog on the screen, and ensure it is visible
		fixture.show().requireVisible();
		
		// select item to delete in the list
		fixture.list("productList").selectItem(prod2.toString());

		// click the delete button
		fixture.button("deleteButton").click();

		// ensure a confirmation dialog is displayed
		DialogFixture confirmDialog = fixture.dialog(withTitle("Select an Option")
				  .andShowing()).requireVisible();

		// click the Yes button on the confirmation dialog
		confirmDialog.button(withText("Yes")).click();
		
		// create a Mockito argument captor to use to retrieve the passed product from the mocked DAO
		ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

		// verify that the DAO.saveProduct method was called, and capture the passed product
		verify(dao).deleteProduct(argument.capture());

		// retrieve the passed product from the captor
		Product deletedProduct = argument.getValue();
		
		assertEquals("Ensure the ID was deleted", "2", deletedProduct.getProductID());
		assertEquals("Ensure the name was deleted", "prod2", deletedProduct.getName());
		assertEquals("Ensure the description was deleted", "Delete test.", deletedProduct.getDescription());
		assertEquals("Ensure the category was deleted", "cat2", deletedProduct.getCategory());
		assertEquals("Ensure the price was deleted", new BigDecimal("20.00"), deletedProduct.getPrice());
		assertEquals("Ensure the quantity was deleted", new BigDecimal("20.00"), deletedProduct.getQuantity());
		
		// get the model
		SimpleListModel model = (SimpleListModel) fixture.list("productList").target().getModel();

		// check the contents
		assertFalse("list doesn't contain the deleted product", model.contains(prod2));
		assertEquals("list contains the correct number of products", 1, model.getSize());
	}
	
	@Test
	public void testSearchByID() {
		Product prod2 = new Product("2", "prod2", "Delete test.", "cat2", 
				  new BigDecimal("20.00"), new BigDecimal("20.00"));
		Product prod3 = new Product("3", "prod3", "View test.", "cat3", 
				  new BigDecimal("10.00"), new BigDecimal("10.00"));
		
		// create dialog passing in product and mocked DAO
		ProductReport report = new ProductReport(null, true, dao);

		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, report);

		// show the dialog on the screen, and ensure it is visible
		fixture.show().requireVisible();

		// type in product ID
		fixture.textBox("txtSearchID").selectAll().deleteText().enterText("3");
		
		// click the search button
		fixture.button("searchButton").click();
		
		// get the model
		SimpleListModel model = (SimpleListModel) fixture.list("productList").target().getModel();

		// check the contents
		assertFalse("list doesn't contain the expected product", model.contains(prod2));
		assertTrue("list contains the expected product", model.contains(prod3));
		assertEquals("list contains the correct number of products", 1, model.getSize());
	}
	
	@Test
	public void testFilterByCategory() {
		Product prod2 = new Product("2", "prod2", "Delete test.", "cat2", 
				  new BigDecimal("20.00"), new BigDecimal("20.00"));
		Product prod3 = new Product("3", "prod3", "View test.", "cat3", 
				  new BigDecimal("10.00"), new BigDecimal("10.00"));
		
		// create dialog passing in product and mocked DAO
		ProductReport report = new ProductReport(null, true, dao);

		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, report);

		// show the dialog on the screen, and ensure it is visible
		fixture.show().requireVisible();

		// select category
		fixture.comboBox("cmbCategoryFilter").selectItem("cat2");
		
		// get the model
		SimpleListModel model = (SimpleListModel) fixture.list("productList").target().getModel();

		// check the contents
		assertFalse("list doesn't contain the expected product", model.contains(prod3));
		assertTrue("list contains the expected product", model.contains(prod2));
		assertEquals("list contains the correct number of products", 1, model.getSize());
	}
}
