package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import service.ProductDAOInterface;
import utility.ConnectionManager;

public class ProductDAO implements ProductDAOInterface {
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public void insert(Product product) 
	{
		try 
		{
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			
			String sql = "INSERT INTO PRODUCT(productId, name, category, price, quantity) VALUES(seq_user.nextval, ?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, product.getName());
			st.setString(2, product.getCategory());
			st.setString(3, product.getPrice());
			st.setString(4, product.getQuantity());
			st.executeUpdate();
			con.close();
		} 
		catch (Exception e) 
		{
			e.getMessage();
		}
	}
	
	public void delete(int productId) 
	{
		try 
		{
			boolean rowDeleted;
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			String sql = "DELETE FROM PRODUCT WHERE productId= ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, productId);
			rowDeleted = st.executeUpdate() > 0;
			if(rowDeleted) 
			{
				System.out.println("----------------------------Product Deleted Successfully!!----------------------------");
				con.close();
			}
			else 
			{
				System.out.println("--------------------------------Product does not exist--------------------------------");
				con.close();
			}
		}
		catch (Exception e) 
		{
			e.getMessage();
		}
	}
	
	public List<Product> displayProducts() 
	{	
		List<Product> list = new ArrayList<Product>();
		try 
		{
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			String sql = "Select * from Product";
			PreparedStatement st = con.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) 
			{
				Product p = new Product();
				int productId = rs.getInt(1);
				String productName = rs.getString(2);
				String category = rs.getString(3);
				String price = rs.getString(4);
				String quantity = rs.getString(5);
				
				p.setProduct_id(productId);
				p.setName(productName);
				p.setCategory(category);
				p.setPrice(price);
				p.setQuantity(quantity);
			
				list.add(p);
			}
			con.close();
		}
		catch (Exception e) 
		{
			e.getMessage();
		}
		return list;
	}

	public void updateProduct(Product p) 
	{
		try 
		{
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			
			String sql = "UPDATE Product SET name= ?, category= ?, price= ?, quantity= ? where productId = ?";
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, p.getName());
			st.setString(2, p.getCategory());
			st.setString(3, p.getPrice());
			st.setString(4, p.getQuantity());
			st.setInt(5, p.getProduct_id());
			boolean rowUpdated = st.executeUpdate() > 0;
			if(rowUpdated)
			{
				System.out.println("------------------------Product Details Updated Successfully!!------------------------");
				con.close();
			}
			else
			{
				System.out.println("-------------------------Could not Update Product! Try Again!-------------------------");
				con.close();
			}
		}
		catch (Exception e) 
		{
			e.getMessage();
		}
	}
	
	public void searchProduct(String pid) 
	{
		
		try 
		{
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			
			String sql = "SELECT productId,name,category,price,quantity FROM Product WHERE productId= '"+pid+"'";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
	
			if(rs.next()) 
			{
				System.out.println("**************************************************************************************");
				System.out.println("ProductID         ProductName            Category              Price          Quantity ");
				System.out.println("=======================================================================================");
				System.out.println(rs.getString("productId")+"         "+rs.getString("name")+"            "+rs.getString("category")+"              "+rs.getString("price")+"          "+rs.getString("quantity"));
			    con.close();
			}
			else 
			{
				System.out.println("-------------Product is out of stock..Enter 1 to Add Product to the Store-------------");
				con.close();
			}
		}
		catch (Exception e) 
		{
			e.getMessage();
		}
	}
	
	public List<Product> getAllAscProducts() 
	{
		
		 List<Product> listasc = new ArrayList<Product>();
		try 
		{
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			String sql = "Select * from Product order by productId ASC";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Product p1 = new Product();
				int productId = rs.getInt(1);
				String productName = rs.getString(2);
				String category = rs.getString(3);
				String price = rs.getString(4);
				String quantity = rs.getString(5);
				
				p1.setProduct_id(productId);
				p1.setName(productName);
				p1.setCategory(category);
				p1.setPrice(price);
				p1.setQuantity(quantity);
				listasc.add(p1);
				
			}
			con.close();
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		
		return listasc;
	}
	
	public List<Product> getAllDescProducts() 
	{
		
		List<Product> listdesc = new ArrayList<Product>();
		try 
		{
			
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			String sql = "Select * from Product order by productId DESC";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) 
			{
				Product p2 = new Product();
				int productId = rs.getInt(1);
				String productName = rs.getString(2);
				String category = rs.getString(3);
				String price = rs.getString(4);
				String quantity = rs.getString(5);
				
				p2.setProduct_id(productId);
				p2.setName(productName);
				p2.setCategory(category);
				p2.setPrice(price);
				p2.setQuantity(quantity);
				listdesc.add(p2);
				
			}
			con.close();
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		
		return listdesc;
		
	}

}
