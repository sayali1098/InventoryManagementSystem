package service;

import java.util.List;
import model.Product;

public interface ProductDAOInterface 
{	
	public void insert(Product product);
	public void delete(int productId);
	public List<Product> displayProducts();
	public void updateProduct(Product p);
	public List<Product> getAllAscProducts();
	public List<Product> getAllDescProducts();

}
