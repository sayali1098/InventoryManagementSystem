package businesslogic;

import java.sql.Connection;
import java.sql.Statement;

import model.RegisterUser;
import utility.ConnectionManager;

public class Validation {
	
	public boolean checkUser(RegisterUser user)
	{	
		boolean validate = false;
		try 
		{
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			Statement st = con.createStatement();
			int rs = st.executeUpdate("SELECT * from usernew where email = '"+user.getEmail()+"' and password = '"+user.getPassword()+"'");
				if(rs!=0) 
				{
					validate = true;
					con.close();
				}
				else {
					validate = false;
					con.close();
				}
		} 
		catch (Exception e) 
		{
			e.getMessage();
		}
		return validate;
	}
	
	public boolean checkAdmin(RegisterUser user)
	{
		boolean valid = false ;
		try
		{
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			
			Statement st = con.createStatement();
			int rs = st.executeUpdate("SELECT * from ADMIN where username = '"+user.getName()+"' and password = '"+user.getPassword()+"'");
				if(rs!=0) 
				{
					valid = true;
					con.close();
				}
				else {
					valid = false;
					con.close();
				}
		}
		catch (Exception e) 
		{
			e.getMessage();
		}
		return valid;
	}
}
