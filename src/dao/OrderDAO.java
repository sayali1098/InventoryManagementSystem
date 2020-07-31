package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import service.OrderDAOInterface;
import utility.ConnectionManager;

public class OrderDAO implements OrderDAOInterface {
		
	public void purchase(Order order) 
	{
		try 
		{
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			String sql = "INSERT INTO Orders(order_id,user_name,prod_id, prod_name, prod_cat, prod_price, prod_quantity) VALUES(seq_user.nextval, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, order.getCustomerName());
			st.setInt(2, order.getProdId());
			st.setString(3, order.getProductName());
			st.setString(4, order.getProductCategory());
			st.setString(5, order.getProductPrice());
			st.setString(6, order.getProductQuantity());
			st.executeUpdate();
			con.close();
			
		}
		catch (Exception e) 
		{
			e.getMessage();
		}
	}
	
	public List<Order> MyOrders(String name) 
	{
		List<Order> list1 = new ArrayList<Order>();
		try 
		{
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			
			String sql = "Select * from Orders where user_name =?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, name);
			ResultSet rs = st.executeQuery();
	
			if(rs.next()==false)
			{
				System.out.println("Sorry!! YOUR CART IS EMPTY...!");
			}
			else
			{
				do{
					Order o = new Order();
					
					int orderId = rs.getInt(1);
					String username = rs.getString(2);
					int pId = rs.getInt(3);
					String pName = rs.getString(4);
					String pCategory = rs.getString(5);
					String pPrice = rs.getString(6);
					String PQuantity = rs.getString(7);
					
					o.setOrderId(orderId);
					o.setCustomerName(username);
					o.setProdId(pId);
					o.setProductName(pName);
					o.setProductCategory(pCategory);
					o.setProductPrice(pPrice);
					o.setProductQuantity(PQuantity);
					
					list1.add(o);
					
					}while(rs.next()); 
			}
			con.close();
		}
		catch (Exception e) {
			e.getMessage();
		}
		return list1;
	}
	
	public void updateMyOrder(Order orders) 
	{
		try 
		{
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			
			String sql = "UPDATE Orders SET user_name= ?, prod_id= ?, prod_name= ?, prod_cat= ?, prod_price= ?, prod_quantity= ? where order_id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1,orders.getCustomerName());
			st.setInt(2, orders.getProdId());
			st.setString(3, orders.getProductName());
			st.setString(4, orders.getProductCategory());
			st.setString(5, orders.getProductPrice());
			st.setString(6, orders.getProductQuantity());
			st.setInt(7, orders.getOrderId());
			
			boolean rowUpdated = st.executeUpdate() > 0;

				if(rowUpdated)
				{
					System.out.println("<<---------------------------Order Updated Successfully!!--------------------------->>");
					con.close();
				}
				else
				{
					System.out.println("<<--------------------Could not Update Order Details! Try Again!-------------------->>");
					con.close();
				}
			
		}
		catch (Exception e) 
		{
			e.getMessage();
		}
		
	}

	public void deleteOrder(int orderId) 
	{
		try 
		{
			boolean rowDeleted;
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			String sql = "DELETE FROM Orders WHERE order_id= ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, orderId);
			rowDeleted = st.executeUpdate() > 0;
			if(rowDeleted) 
			{
				System.out.println("<<--------------------------Order Cancelled Successfully!!-------------------------->>");
				con.close();
			}
			else 
			{
				System.out.println("<<-------------------------------Order does not exist------------------------------->>");
				con.close();
			}
		}
		catch (Exception e) {
			e.getMessage();
		}
	}
	
}
