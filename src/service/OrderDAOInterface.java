package service;

import java.util.List;

import model.Order;

public interface OrderDAOInterface 
{
	public void purchase(Order order);
	public List<Order> MyOrders(String name);
	public void updateMyOrder(Order orders);
	public void deleteOrder(int orderId);
}
