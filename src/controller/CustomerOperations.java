package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dao.BillGenerator;
import dao.CustomerDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Order;
import model.Product;
import model.RegisterUser;

public class CustomerOperations {
		
	//functionalities of customer
	public void customer() throws Exception {
		
		int choice;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		do {
			System.out.println();
			System.out.println();
			System.out.println("**************************************************************************************");
			System.out.println("1.View Products");
			System.out.println("2.Purchase Product");
			System.out.println("3.My Orders");
			System.out.println("4.Update existing Orders");
			System.out.println("5.Cancel previous Orders");
			System.out.println("6.Generate Bill");
			System.out.println("7.Update Your Details");
			System.out.println("8.Exit");
			System.out.println("\nWhich Action You want to Perform?(1-7)");
			choice = Integer.parseInt(br.readLine());
			
			//objects for respective dao classes
			ProductDAO productdao = new ProductDAO();
			CustomerDAO customerdao = new CustomerDAO();
			OrderDAO orderdao = new OrderDAO();
			BillGenerator bill = new BillGenerator();
			//objects for list
			List<Product> list = new ArrayList<Product>();
			List<Order> list1 = new ArrayList<Order>();
			//objects for model classes
			Order orders = new Order();
			RegisterUser user = new RegisterUser();
			
			
			switch(choice) 
			{
			//choice to display products in store
			case 1:
				//declaring object for list
				list = productdao.displayProducts();
				
				System.out.println("                                  Products In Store                                   ");
				System.out.println(">>==================================================================================<<");
				//For each loop to iterate over products
				if(list!=null)
				{
					System.out.println("ProductID         ProductName            Category              Price          Quantity ");
					for(Product p: list) {
						
						System.out.println("======================================================================================");
						System.out.println(p.getProduct_id()+"               "+p.getName()+"            "+p.getCategory()+"            "+p.getPrice()+"            "+p.getQuantity());
						
					}
				}
				//message if list is empty
				else {
					System.out.println(list);
				}
				break;
				
			case 2:
				//choice to purchase product from store
				System.out.println("------Please Enter Following Details------");
				System.out.println("Enter your name");
				String name = br.readLine();
				System.out.println("Enter the Product ID to purchase");
				int prodId = Integer.parseInt(br.readLine());
				System.out.println("Enter Product Name");
				String prod_name = br.readLine();
				System.out.println("Enter Product Category");
				String prod_cat = br.readLine();
				System.out.println("Enter Price of Product");
				String prod_price = br.readLine();
				System.out.println("Enter the required quantity");
				String prod_quantity = br.readLine();
				
				//setters to initialize order
				orders.setCustomerName(name);
				orders.setProdId(prodId);
				orders.setProductName(prod_name);
				orders.setProductCategory(prod_cat);
				orders.setProductPrice(prod_price);
				orders.setProductQuantity(prod_quantity);
				
				//placing order
				orderdao.purchase(orders);
				System.out.println(">>-----------------------------Order Placed Successfully!!--------------------------<<");
				break;
			
			//choice to view Customer order	
			case 3:
				//searching order based on customer name
				System.out.println("Enter your name to view your Orders");
				String Cname = br.readLine();
				//Order list for specific customer order
				list1 = orderdao.MyOrders(Cname);
				
				System.out.println("                                                 YOUR ORDERS                                                 ");
				System.out.println(">>=========================================================================================================<<");
				//For each loop to iterate over orders
				if(list1!=null)
				{
					System.out.println("OrderID         Name         ProductID         Product Name         Category         Price          Quantity ");
			
					for(Order o: list1) {
						
						System.out.println("=============================================================================================================");
						System.out.println(o.getOrderId()+"             "+o.getCustomerName()+"         "+o.getProdId()+"                "+o.getProductName()+"         "+o.getProductCategory()+"         "+o.getProductPrice()+"         "+o.getProductQuantity());
					}
					System.out.println(">>=========================================================================================================<<");	
				}
				//message if empty cart
				else 
				{
					System.out.println("----------------------------------------------NO ORDERS PLACED-----------------------------------------------");
				}
				break;
			
			//choice to update orders	
			case 4:
				System.out.println("**************************************************************************************");
				System.out.println("                                   Update Orders                                      ");
				System.out.println(">>==================================================================================<<");
				//getting update details from customer
				System.out.println("Enter Order ID to update");
				int orderId = Integer.parseInt(br.readLine());
				System.out.println("Enter your name");
				name = br.readLine();
				System.out.println("Enter Product ID to update");
				prodId = Integer.parseInt(br.readLine());
				System.out.println("Update Product name");
				String pName = br.readLine();
				System.out.println("Update Category");
				String pCategory = br.readLine();
				System.out.println("Update Price");
				String pPrice = br.readLine();
				System.out.println("Update Quantity");
				String pQuantity = br.readLine();
				
				//setters to initialize order 
				orders.setOrderId(orderId);
				orders.setCustomerName(name);
				orders.setProdId(prodId);
				orders.setProductName(pName);
				orders.setProductCategory(pCategory);
				orders.setProductPrice(pPrice);
				orders.setProductQuantity(pQuantity);
				
				//updating order function
				orderdao.updateMyOrder(orders);
				break;
				
			case 5:
				//getting order id to delete
				System.out.println("Enter Order ID to delete Order");
				orderId = Integer.parseInt(br.readLine());
				orderdao.deleteOrder(orderId);
				break;
				
			case 6: 
				//generating bill based on Customer Name
				System.out.println("Enter your name to generate the bill");
				name = br.readLine();
				bill.generateBill(name);
				break;
				
			case 7:
				//Updating Customer Details
				System.out.println("**************************************************************************************");
				System.out.println("                                Update Customer Details                               ");
				System.out.println(">>==================================================================================<<");
				System.out.println("Enter your Name to update");
				String myName = br.readLine();
				System.out.println("Update Email");
				String email = br.readLine();
				System.out.println("Update Mobile");
				String mobile = br.readLine();
				System.out.println("Change Password");
				String password = br.readLine();
				
				//setters to initialize customer details
				user.setName(myName);
				user.setEmail(email);
				user.setMobile(mobile);
				user.setPassword(password);
				
				//function to update customer
				customerdao.updateDetails(user);
				break;
				
			//exit message for customer	
			case 8:
				System.out.println();
				System.out.println("__________________________________________");
				System.out.println("Thank You for Shopping! Visit Again :=) ");
				System.out.println("__________________________________________");
				break;	
			
			//invalid choice case	
			default: 
				System.out.println("Invalid choice");
				break;
			}
		}while(choice!=8);
		
	}//customer() closed

}//class scope ends

