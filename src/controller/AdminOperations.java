package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CustomerDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Order;
import model.Product;
import model.RegisterUser;

public class AdminOperations {
	
	public void admin() throws SQLException, Exception {
		
		int choice;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		do {
			System.out.println();
			System.out.println("**************************************************************************************");
			//functionalities for the Admin
			System.out.println("1.Add Product");
			System.out.println("2.Delete Product");
			System.out.println("3.Product Details");
			System.out.println("4.Update Product Details");
			System.out.println("5.Search Product");
			System.out.println("6.Sort Products");
			System.out.println("7.Customer Details");
			System.out.println("8.Customer Order Details");
			System.out.println("9.Delete Customer");
			System.out.println("10.Cancel Orders");
			System.out.println("11.Exit");
			System.out.println("\nWhich Action You want to Perform?(1-11)");
			//getting choice from Admin
			choice = Integer.parseInt(br.readLine());
			
			//object declaration for dao classes
			Product product = new Product();
			CustomerDAO customerdao = new CustomerDAO();
			ProductDAO productdao = new ProductDAO();
			OrderDAO orderdao = new OrderDAO();
			
			//object declaration for Lists
			List<Product> plist = new ArrayList<Product>();
			List<Product> list1 = new ArrayList<Product>();
			List<Product> list2 = new ArrayList<Product>();
			List<RegisterUser> userlist = new ArrayList<RegisterUser>();
			List<Order> orderlist = new ArrayList<Order>();
			
			switch(choice) 
			{
				//choice to insert new Products 
				case 1:
					System.out.println("------------------> Enter Product Details <------------------");
					
					//getting details from admin
					System.out.println("Enter Product Name:");
					String name = br.readLine();
					System.out.println("Enter Product Category:");
					String category = br.readLine();
					System.out.println("Enter Product Price");
					String price = br.readLine();
					System.out.println("Enter Quantity of Product");
					String quantity = br.readLine();
					
					//setters to initialize product details
					product.setName(name);
					product.setCategory(category);
					product.setPrice(price);
					product.setQuantity(quantity);
					productdao.insert(product);
					
					//message on successful insertion in db
					System.out.println("-----------------------Product Successfully Added to the Store!!----------------------");
					break;
				
				//choice to delete existing products
				case 2:
					//getting product id from admin
					System.out.println("Enter Product ID to delete");
					int id = Integer.parseInt(br.readLine());
					
					//deleting product based on id
					productdao.delete(id);
					break;
				
				//choice to display available products
				case 3:
					
					//declaring object for list
					plist = productdao.displayProducts();
					System.out.println("**************************************************************************************");
					System.out.println("                                  Products In Store                                   ");
					System.out.println(">>==================================================================================<<");
					
					//For each loop to iterate over products
					if(plist!=null)
					{
						System.out.println("ProductID         ProductName            Category              Price          Quantity ");
						for(Product p: plist) {
							
							System.out.println("======================================================================================");
							System.out.println(p.getProduct_id()+"             "+p.getName()+"            "+p.getCategory()+"            "+p.getPrice()+"            "+p.getQuantity());
						}
					}
					//message if list is empty
					else {
						System.out.println("-----------------------------------Store is Empty!!-----------------------------------");
					}
					break;
				
				//choice to update existing products
				case 4: 
					System.out.println("**************************************************************************************");
					System.out.println("                                  Update Products                                     ");
					System.out.println(">>==================================================================================<<");
					
					//getting product details to be updated
					System.out.println("Enter Product ID to update");
					int pId = Integer.parseInt(br.readLine());
					System.out.println("Update Product name");
					String pName = br.readLine();
					System.out.println("Update Category");
					String pCategory = br.readLine();
					System.out.println("Update Price");
					String pPrice = br.readLine();
					System.out.println("Update Quantity");
					String pQuantity = br.readLine();
					
					//setters to initialize product details
					product.setProduct_id(pId);
					product.setName(pName);
					product.setCategory(pCategory);
					product.setPrice(pPrice);
					product.setQuantity(pQuantity);
					
					//passing the product to be updated
					productdao.updateProduct(product);
					break;
				
				//choice to search a product based on ID
				case 5: 
					//getting product id from admin
					System.out.println("Enter Product ID to be searched");
					String pid = br.readLine();
					//searching product based on ID
					productdao.searchProduct(pid);
					break;
				
				//choice to sort products based on ID
				case 6: 
					//getting the sorting order from admin
					System.out.println("In which order you want to Sort?");
					System.out.println("1.Sort in Ascending Order");
					System.out.println("2.Sort in Descending Order");
					int option = Integer.parseInt(br.readLine());
					
					//ascending sort
					if(option == 1) 
					{
						//list object storing the ascending product details
						list1 = productdao.getAllAscProducts();
						System.out.println("**************************************************************************************");
						System.out.println("                             Products In Ascending Order                              ");
						System.out.println(">>==================================================================================<<");
						
						//for each loop to iterate over ascending elements
						if(list1!=null)
						{
							System.out.println("ProductID         ProductName            Category              Price          Quantity ");
							for(Product p1: list1) {
								
								System.out.println("======================================================================================");
								System.out.println(p1.getProduct_id()+"               "+p1.getName()+"            "+p1.getCategory()+"            "+p1.getPrice()+"            "+p1.getQuantity());
							}
						}
					}
					//descending sort
					else if(option == 2) 
					{
						//list object storing the ascending product details
						list2 = productdao.getAllDescProducts();
						System.out.println("**************************************************************************************");
						System.out.println("                            Products In Descending Order                              ");
						System.out.println(">>==================================================================================<<");
						//for each loop to iterate over ascending elements
						if(list2!=null)
						{
							System.out.println("ProductID         ProductName            Category              Price          Quantity ");
							for(Product p2: list2) {
								
								System.out.println("======================================================================================");
								System.out.println(p2.getProduct_id()+"               "+p2.getName()+"            "+p2.getCategory()+"            "+p2.getPrice()+"            "+p2.getQuantity());
							}
						}
					}
					//message if product list is empty
					else 
					{
						System.out.println("------------------------------------Store is empty!-----------------------------------");
					}
					break;
				
				//choice to display customer information
				case 7: 
					//list object storing customer enrolled
					userlist = customerdao.getAllUsers();
					System.out.println("**************************************************************************************");
					System.out.println("                                  Customer Details                                    ");
					System.out.println(">>==================================================================================<<");
					//for each loop to iterate over customers
					if(userlist!=null)
					{
						System.out.println("CustomerID         Customer Name           Email            Mobile");
						for(RegisterUser u: userlist) {
							
							System.out.println("======================================================================================");
							System.out.println(u.getUserId()+"                   "+u.getName()+"            "+u.getEmail()+"            "+u.getMobile());
						}
					}
					//message if no customers present 
					else 
					{
						System.out.println("--------------------------------No Registered Customers-------------------------------");
					}
					break;
				
				//choice to view all orders placed by customer
				case 8: 
					//list to store order details of customer
					orderlist = customerdao.getAllOrders();
					System.out.println("*********************************************************************************************************************************");
					System.out.println("                                                        Customer Order Details                                                   ");
					System.out.println(">>=============================================================================================================================<<");
					//for each loop to iterate over orders
					if(orderlist!=null)
					{
						
						System.out.println("OrderID   Customer Name     ProductID         ProductName      Category          Price          Quantity ");
						for(Order o: orderlist) {
							
							System.out.println("================================================================================================================================");
							System.out.println(o.getOrderId()+"         "+o.getCustomerName()+"            "+o.getProdId()+"         "+o.getProductName()+"            "+o.getProductCategory()+"              "+o.getProductPrice()+"          "+o.getProductQuantity());
						}
						System.out.println(">>=============================================================================================================================<<");
					}
					//message if no orders placed
					else 
					{
						System.out.println("-----------------------------------No Orders Placed!----------------------------------");
					}
					break;
				
				//choice to remove an existing customer 	
				case 9:
					//getting customer id from admin
					System.out.println("Enter Customer ID to delete");
					id = Integer.parseInt(br.readLine());
					//removing a customer based on id
					customerdao.deleteCustomer(id);
					break;
				
				//choice to cancel customer's order
				case 10:
					//getting order id from admin
					System.out.println("Enter Order ID to Cancel");
					int oId = Integer.parseInt(br.readLine());
					//cancelling order based on id
					orderdao.deleteOrder(oId);
					break;
				
				//exit message
				case 11:
					System.out.println("---------------------------Thank You for Visiting the Store---------------------------");
					break;
				
				//invalid choice case
				default:
					System.out.println("------------------------------------Invalid choice------------------------------------");
				break;
					
			}//switch closed
			
		}while(choice!=11);
		
			
	}//admin() ends

}//class scope closed
