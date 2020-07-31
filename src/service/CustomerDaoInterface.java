package service;

import java.util.List;
import model.Order;
import model.RegisterUser;

public interface CustomerDaoInterface 
{
	public List<RegisterUser> getAllUsers();
	public List<Order> getAllOrders();
	public void deleteCustomer(int id);
	public void updateDetails(RegisterUser user);	
}
