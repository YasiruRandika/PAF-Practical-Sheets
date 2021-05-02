package service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;

import util.ConnectDB;

public class Item {
	private static PreparedStatement preparedStatement;
	private static Connection connection;
	
	public String readItems()
	{ 
	 String output = ""; 
	try
	 { 
	 Connection con = ConnectDB.getDBConnection(); 
	 if (con == null) 
	 { 
	 return "Error while connecting to the database for reading."; 
	 } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Item Code</th> "
	 		+ "<th>Item Name</th><th>Item Price</th>"
	 		+ "<th>Item Description</th> "
	 		+ "<th>Update</th><th>Remove</th></tr>";
	 String query = "select * from item"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 int size = 0;
	 while (rs.next()) 
	 { 
		 size +=1;
	 String itemID = Integer.toString(rs.getInt("id")); 
	 String itemCode = rs.getString("itemCode"); 
	 String itemName = rs.getString("name"); 
	 String itemPrice = Double.toString(rs.getDouble("price")); 
	 String itemDesc = rs.getString("description"); 
	 // Add into the html table
	 output += "<tr><td><input id='hidItemIDUpdate' "
	 		+ "name='hidItemIDUpdate' "
	 		+ "type='hidden' value='" + itemID + "'>"
	 		+ itemCode + "</td>"; 
	 output += "<td>" + itemName + "</td>"; 
	 output += "<td>" + itemPrice + "</td>"; 
	 output += "<td>" + itemDesc + "</td>"; 
	// buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' "
	 + "class='btnUpdate btn btn-secondary' data-itemid='" + itemID + "'></td>"
	 + "<td><input name='btnRemove' type='button' value='Remove' "
	 + "class='btnRemove btn btn-danger' data-itemid='" + itemID + "'></td></tr>"; 
	  } 
	  con.close(); 
	  // Complete the html table
	  output += "</table>"; 
	  
	  if (size == 0) {
		output="<h4>No Data in the Database</h4>";
	}
	  } 
	 catch (Exception e) 
	  { 
		 output = "Error while reading the item";  
	  System.err.println(e.getMessage()); 
	  } 
	 return output; 
	 }
	
	public String deleteItem(int id) {
		String output;
		try {
			connection = ConnectDB.getDBConnection();
			
			//Check whether properly connected or not
			if (connection == null) {
				return "Error while connecting to the database";
			}
			
			//Create Prepared Statement
			String sql = "DELETE FROM Item WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			//Bind Values
			preparedStatement.setInt(1, id);
			
			//execute the statement
			preparedStatement.execute();
			connection.close();
			output = "Deleted successfully"; 
			output = readItems();
			output = "{\"status\":\"success\", \"data\": \"" + output + "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";  
		} finally {
			/*
			 * Close statement and database connectivity at the end of transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (java.sql.SQLException e) {
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; ;
			}
		}
		return output;
	}
	
	
	public String updateItem(int id, String code, String name, String price, String desc) {
		String output;
		try {
			connection = ConnectDB.getDBConnection();
			
			//Check whether properly connected or not
			if (connection == null) {
				return "Error while connecting to the database";
			}
			
			//Create Prepared Statement
			String sql = "UPDATE Item SET itemCode = ?, name = ?, price = ?, description = ? WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			//Bind Values
			preparedStatement.setString(1, code);
			preparedStatement.setString(2, name);
			preparedStatement.setFloat(3, Float.parseFloat(price));
			preparedStatement.setString(4, desc);
			preparedStatement.setInt(5, id);
			
			//execute the statement
			preparedStatement.execute();
			connection.close();
			output = "Updated successfully"; 
			output = readItems();
			output = "{\"status\":\"success\", \"data\": \"" + output + "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
		} finally {
			/*
			 * Close statement and database connectivity at the end of transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (java.sql.SQLException e) {
				output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
			}
		}
		return output;
	}
	
	public String insertItem(String code, String name, String price, String desc) {
		String output;
		try {
			connection = ConnectDB.getDBConnection();
			
			//Check whether properly connected or not
			if (connection == null) {
				return "Error while connecting to the database";
			}
			
			//Create Prepared Statement
			String sql = "INSERT INTO Item(itemCode, name, price, description) VALUES(?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			
			//Bind Values
			preparedStatement.setString(1, code);
			preparedStatement.setString(2, name);
			preparedStatement.setFloat(3, Float.parseFloat(price));
			preparedStatement.setString(4, desc);
			
			//execute the statement
			preparedStatement.execute();
			connection.close();
			output = "Inserted successfully";
			output = readItems();
			output = "{\"status\":\"success\", \"data\": \"" + output + "\"}";
			System.out.println(output);
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
		} finally {
			/*
			 * Close statement and database connectivity at the end of transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (java.sql.SQLException e) {
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
			}
		}
		
		return output;
	}

}
