package dao;

import domain.Product;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
//import java.util.TreeSet;

/**
 *
 * @author dinjo998
 */
public class ProductStore implements ProductDataInterface{
//	private static Collection<Product> products = new TreeSet();
	private static Map<String, Product> productsByID = new HashMap<>();
	private static Map<String, Set<Product>> productsInCategory = new HashMap<>();
	
	@Override
	public void saveProduct(Product product){
//		products.add(product);
		productsByID.put(product.getProductID(), product);
		String filter = product.getCategory();
		if(productsInCategory.containsKey(filter)){
			(productsInCategory.get(filter)).add(product);
		} else{
			Set<Product> newSet = new HashSet<>();
			newSet.add(product);
			productsInCategory.put(filter, newSet);
		}
	}
	
	@Override
	public void deleteProduct(Product product){
//		products.remove(product);
		//(productsInCategory.get(product.getCategory())).remove(product);
		productsByID.remove(product.getProductID());
	}
	
	@Override
	public Product searchByID(String productID){
		if(productsByID.containsKey(productID)){
			return productsByID.get(productID);
		}else{
			return null;
		}
	}
	
	@Override
	public Collection<Product> filterByCategory(String category){
		return productsInCategory.get(category);
	}
		
	@Override
	public Collection<Product> getProducts(){
		return productsByID.values();
	}
	
	@Override
	public Collection<String> getCategory(){
//		Collection<String> category = new TreeSet();
//		for(Product product : products){
//			category.add(product.getCategory());
//		}
//		return category;
		return productsInCategory.keySet();
	}

}
