package businesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

import model.RegisterUser;
import utility.ConnectionManager;

public class RegistrationProcess {
	
	public boolean checkSignup(RegisterUser u)
	{
		if(validEmail(u.getEmail()) && validPassword(u.getPassword())) {
			return true;
		}
		return false;
		
	}
	
	private boolean validEmail(String email) {
		
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();	
	}
	
	private boolean validPassword(String password) {
		
		Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
		Pattern lowerCasePatten = Pattern.compile("[a-z ]");
		Pattern digitCasePatten = Pattern.compile("[0-9 ]");
		boolean flag = true;

		if (password.length() < 8 || password.length() > 20) {
			flag = false;
		}
		if (!specailCharPatten.matcher(password).find()) {
			flag = false;
		}
		if (!UpperCasePatten.matcher(password).find()) {
			flag = false;
		}
		if (!lowerCasePatten.matcher(password).find()) {
			flag = false;
		}
		if (!digitCasePatten.matcher(password).find()) {
			flag = false;
		}

		return flag;
		
	}


	public void register(RegisterUser user) throws SQLException, Exception {
		ConnectionManager cm = new ConnectionManager();
		Connection con = cm.getConnection();
		
		String sql = "INSERT INTO USERNEW (userid,name,mobile,email,password) VALUES (seq_user.nextval, ?, ?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, user.getName());
		st.setString(2, user.getMobile());
		st.setString(3, user.getEmail());
		st.setString(4, user.getPassword());
		st.executeUpdate();
		con.close();
	}

}
