package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import businesslogic.RegistrationProcess;
import businesslogic.Validation;
import model.RegisterUser;

public class Main {

	public static void main(String[] args) throws Exception {
		
		 LocalDateTime myDateObj = LocalDateTime.now();
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");
		 System.out.println("________________________________________________________________________________________");
		 System.out.println("-------------------------WELCOME TO INVENTORY MANAGEMENT SYSTEM-------------------------");
		 System.out.println("                 Today's Date and Current Time is: "+ myDateObj.format(myFormatObj));
		 System.out.println("________________________________________________________________________________________");
		 
		 int choice;
		 //display options to enter in the system
		 do 
		 {
			 System.out.println("Select your option");
			 System.out.println("------------------");
			 System.out.println("1.Register");
			 System.out.println("2.Customer Login");
			 System.out.println("3.Admin");
			 System.out.println("4.Exit");
			 System.out.println("------------------");
			 
			 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 
			 //creating objects for model and controller classes
			 RegisterUser user = new RegisterUser();
			 RegistrationProcess reg = new RegistrationProcess();
			 Validation valid = new Validation();
			 AdminOperations adminFunction = new AdminOperations();
			 CustomerOperations customerFunction = new CustomerOperations();
			 
			 //fetching choice from user
			 choice = Integer.parseInt(br.readLine());
			 
			 switch(choice) {
			 
			 //new user registration
			 case 1:
				 System.out.println("===============================NEW USER REGISTRATION===============================");
				 //getting details from user
				 System.out.println("Enter name:");
				 String name = br.readLine();
				 System.out.println("Enter email");
				 String email = br.readLine();
				 System.out.println("Enter mobile number");
				 String mobile = br.readLine();
				 System.out.println("Set password");
				 String password = br.readLine();
				 //setters to assign values 
				 user.setName(name);
				 user.setEmail(email);
				 user.setMobile(mobile);
				 user.setPassword(password);
				 //validation of email and password
				 if(!reg.checkSignup(user)) 
				 {
					 System.out.println();
					 System.out.println("=============================INVALID EMAIL or PASSWORD=============================");
					 System.out.println("Password must match the following constraints:");
					 System.out.println("1. Atleast one Uppercase letter");
					 System.out.println("2. Atleast one number");
					 System.out.println("3. Atleast one special character");
					 System.out.println("4. Length must be between 8 to 20 characters!");
					 System.out.println("===================================================================================");
				 }
				//function call to register user in database
				 else 
				 {
				 reg.register(user);
				 System.out.println("<<---------------------------Registration Successfull!!----------------------------->>");
				 }
			     break;
			     
			 //login of existing users
			 case 2:
				 System.out.println("====================================CUSTOMER LOGIN====================================");
				 //getting login credentials
				 System.out.println("Enter email");
				 String Cemail = br.readLine();
				 System.out.println("Enter password");
				 String Cpassword = br.readLine();
				 //setters to assign values
				 user.setEmail(Cemail);
				 user.setPassword(Cpassword);
				 //validating login credentials
				 if(valid.checkUser(user)) 
				 {
					 System.out.println("<<---------------------------------Login Successful--------------------------------->>");
					 System.out.println(">>----------------------------!!!WELCOME DEAR CUSTOMER!!!---------------------------<<");
	                //function call to display customer operations
					 customerFunction.customer();
				 }
				 else
					 System.out.println("----------------------------Invalid Credentials! Try Again----------------------------");
				 break;
				 
			//Admin login	 
			 case 3:
				 System.out.println("=====================================ADMIN LOGIN======================================");
				 //getting admin credentials
				 System.out.println("Enter username");
				 String username = br.readLine();
				 System.out.println("Enter password");
				 String Adminpass = br.readLine();
				 //setters to assign values
				 user.setName(username);
				 user.setPassword(Adminpass);
				 //Verifying admin login
				 if(valid.checkAdmin(user)) {
					 System.out.println("<<--------------------------------Login Successful!!!------------------------------->>");
					 //function call to display admin operations
					 adminFunction.admin();
				 }
				 else
					 System.out.println("----------------------------Invalid Credentials! Try Again----------------------------");
				 
			//Goodbye message	 
			 case 4:
				 System.out.println("**************************Thank You for Visiting the Store!***************************");
				 break;
				
			//invalid choice case
			default:
				 System.out.println("------------------------------Invalid Choice..Try Again-------------------------------");
				 break;
			 } //switch close
			 
		 }while(choice!= 4);
		 
	}//main ends

}//class scope closed
