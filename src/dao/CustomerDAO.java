package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.RegisterUser;
import service.CustomerDaoInterface;
import utility.ConnectionManager;

public class CustomerDAO implements CustomerDaoInterface  {
	
	public List<RegisterUser> getAllUsers()
	{
		
		 List<RegisterUser> userlist = new ArrayList<RegisterUser>();
		try 
		{
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			String sql = "Select * from USERNEW";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				RegisterUser user = new RegisterUser();
				int userId = rs.getInt(1);
				String userName = rs.getString(2);
				String email = rs.getString(3);
				String mobile = rs.getString(4);
				String password = rs.getString(5);
				
				user.setUserId(userId);
				user.setName(userName);
				user.setEmail(email);
				user.setMobile(mobile);
				user.setPassword(password);
				userlist.add(user);
				
			}
			con.close();
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		
		return userlist;
	}
	
	public List<Order> getAllOrders() 
	{ 
	
	   List<Order> orderlist = new ArrayList<Order>();
       try 
       {
    	    ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			String sql = "Select * from Orders";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				int orderId = rs.getInt(1);
				String userName = rs.getString(2);
				int prodId = rs.getInt(3);
				String pName = rs.getString(4);
				String pCategory = rs.getString(5);
				String pPrice = rs.getString(6);
				String pQuantity = rs.getString(7);
				
				order.setOrderId(orderId);
				order.setCustomerName(userName);
				order.setProdId(prodId);
				order.setProductName(pName);
				order.setProductCategory(pCategory);
				order.setProductPrice(pPrice);
				order.setProductQuantity(pQuantity);
				orderlist.add(order);
			}
			con.close();  
       }
       catch(Exception e)
       {
			e.getMessage();
		   }
       return orderlist;
	}

	
	public void deleteCustomer(int id) 
	{
		boolean rowDeleted;
		try {
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			String sql = "DELETE FROM USERNEW WHERE userid = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			rowDeleted = st.executeUpdate() > 0;
			if(rowDeleted) 
			{
				System.out.println("<<---------------------Customer Details Deleted Successfully!!---------------------->>");
				con.close();
			}
			else 
			{
				System.out.println("<<-----------------------------Customer does not exist------------------------------>>");
				con.close();
			}
		}
		catch(Exception e) 
		{
			e.getMessage();
		}
	}

	public void updateDetails(RegisterUser user) 
	{
		
		try {
	
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			
			String sql = "UPDATE USERNEW SET email= ?, mobile= ?, password= ? where name = ?";
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, user.getEmail());
			st.setString(2, user.getMobile());
			st.setString(3, user.getPassword());
			st.setString(4, user.getName());
			
			boolean rowUpdated = st.executeUpdate() > 0;
			if(rowUpdated)
			{
				System.out.println("---------------------------- Details Updated Successfully!!---------------------------");
				con.close();
			}
			else
			{
				System.out.println("-----------------------------Could not Update! Try Again!-----------------------------");
				con.close();
			}
		}
		catch (Exception e) 
		{
			e.getMessage();
		}
		
	}

}
