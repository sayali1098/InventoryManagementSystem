package dao;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import utility.ConnectionManager;

public class BillGenerator {
	
	public void generateBill(String name) throws Exception
	{
		
		Document my_bill = new Document();
		PdfWriter.getInstance(my_bill, new FileOutputStream("Bills.pdf"));
		my_bill.open();
		
		Paragraph p = new Paragraph(" Order Details for "+name+"\n");
		p.setAlignment(Paragraph.ALIGN_CENTER);
		my_bill.add(p);
		Paragraph line = new Paragraph("_____________________________________________________________________________\n\n");
		my_bill.add(line);
		
		ConnectionManager cm = new ConnectionManager();
		Connection con = cm.getConnection();
		String sql = "Select * from Orders where user_name =?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, name);
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) 	
			System.out.println("=============================Bill Generated Successfully!!============================");
		else
			System.out.println("=============================Bill Generation Failed!!==============================");
		
		do 
			{
				int orderId = rs.getInt(1);
				Paragraph p1 = new Paragraph( "Order ID               :          "+orderId+"\n");
				my_bill.add(p1);
				String username = rs.getString(2);
				Paragraph p2 = new Paragraph( "Customer Name   :          "+username+"\n");
				my_bill.add(p2);
				int pId = rs.getInt(3);
				Paragraph p3 = new Paragraph( "Product ID            :          "+pId+"\n");
				my_bill.add(p3);
				String pName = rs.getString(4);
				Paragraph p4 = new Paragraph( "Product Name      :          "+pName+"\n");
				my_bill.add(p4);
				String pCategory = rs.getString(5);
				Paragraph p5 = new Paragraph( "Product Category :          "+pCategory+"\n");
				my_bill.add(p5);
				String pPrice = rs.getString(6);
				Paragraph p6 = new Paragraph( "Product Price       :          "+pPrice+"\n");
				my_bill.add(p6);
				String PQuantity = rs.getString(7);
				Paragraph p7 = new Paragraph( "Product Quantity  :          "+PQuantity+"\n");
				my_bill.add(p7);
				Paragraph last = new Paragraph(" Thank You! Visit Again!");
				last.setAlignment(Paragraph.ALIGN_CENTER);
				my_bill.add(last);
				
			} while(rs.next()); 	
		
		my_bill.close();
		con.close();	
	}

}
