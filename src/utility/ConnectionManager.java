package utility;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
public class ConnectionManager {
	
	//method to fetch jdbc properties from jdbc.properties file
	public static Properties loadPropertiesFile() throws IOException {
		
		Properties prop = new Properties();
		InputStream in = ConnectionManager.class.getClassLoader().getResourceAsStream("jdbc.properties");
		prop.load(in);
		in.close(); 
		return prop;
	}
	
	//method to connect with the database
    public Connection getConnection() throws Exception {
		
		Properties prop= null;
		prop = loadPropertiesFile();
		
		//fetching properties
		final String driver = prop.getProperty("driver");
		final String url = prop.getProperty("url");
		final String username = prop.getProperty("username");
		final String password = prop.getProperty("password");
		
		// Registering the jdbc driver
		Class.forName(driver);  
		
		// Connecting to database
		Connection con = null;
		con = DriverManager.getConnection(url,username,password); 
		return con;
	}
    
}
