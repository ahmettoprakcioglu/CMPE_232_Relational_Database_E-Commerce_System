import java.sql.*;

public class DBConnection {

	public static Connection c = null;

	// public static Statement stmt = null;
	public DBConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://10.98.98.61:5432/group61", "group61",
					"YnF#GBs7TYEe+Cg*");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully\nWelcome to our program :)");

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public void closeConnection() throws SQLException {
		c.commit();
		c.close();
	}

	public static void displayCustomers() {

		try {

			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM customers");
			rs.next();

			System.out.println("\nList of all customer:");
			System.out.println("-------------------------");
			while (!rs.isAfterLast()) {

				System.out.println(rs.getInt("customer_id") + " - " + rs.getString("customer_first_name") + " "
						+ rs.getString("customer_last_name"));

				rs.next();
			}

			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void displayProducts() {

		try {

			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM products");
			rs.next();

			System.out.println("\nList of all products:");
			System.out.println("-------------------------");
			while (!rs.isAfterLast()) {

				System.out.println(rs.getInt("product_id") + "-" + rs.getString("product_name") + " "
						+ rs.getString("product_price") + "$");
				rs.next();
			}
			
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void addCustomer(String customer_first_name, String customer_last_name, String customer_user_name,
			String customer_password, String customer_gender, String customer_address, String customer_phone_number) {

		try {

			Statement st = c.createStatement();
			
			String query = "Insert into customers (customer_first_name,customer_last_name,customer_user_name,customer_password,customer_gender,customer_address,customer_phone_number) values ('"+customer_first_name+"','"+customer_last_name+"','"+customer_user_name+"','"+customer_password+"','"+customer_gender+"','"+customer_address+"','"+customer_phone_number+"')";

			
			//st.executeQuery(query);
			int row=st.executeUpdate(query);
			if(row>0) {
				System.out.println("Data added successfully.");
			}else {
				System.out.println("Data could not add.");
			}
			st.close();
			

		} catch (Exception e) {
			//e.printStackTrace();
		}

	}
	
	public static boolean loginControl(String username, String password) {
		try {
			
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT customer_user_name,customer_password FROM customers WHERE customer_user_name = '"+username+"' AND "+"customer_password = '"+password+"'");
			
			if(rs.next()) {
				return true;
			}
			
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void addProduct(String product_name,double product_price,int stock_amount,int category_id) {
		try {

			Statement st = c.createStatement();
			
			String query = "Insert into products (product_name,product_price,stock_amount,category_id) values ('"+product_name+"',"+product_price+","+stock_amount+","+category_id+")";

			
			//st.executeUpdate(query);
			int row=st.executeUpdate(query);
			if(row>0){
				System.out.println("-----------------------------------------");
				System.out.println("The product has been successfully added.");
				System.out.println("-----------------------------------------");
				
			}else {
				System.out.println("-----------------------------------------");
				System.out.println("The product you requested could not be added.");
				System.out.println("-----------------------------------------");
			}
			st.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void deleteProduct(int product_id) {

		Boolean control=false;
		try {
			
			Statement st = c.createStatement();
			Statement st2 = c.createStatement();
			String query = "DELETE FROM products WHERE product_id = "+product_id+"";
			int row=st.executeUpdate(query);
			if(row>0) {
				System.out.println("-----------------------------------------");
				System.out.println(product_id+" deleted from table");
				System.out.println("-----------------------------------------");
			}else {
				System.out.println("-----------------------------------------");
				System.out.println("Delete operation unsuccessful");
				System.out.println("-----------------------------------------");
			}
			
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void getCategories() {
		
		try {

			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM categories");
			rs.next();

			System.out.println("\nList of all categories:");
			System.out.println("-------------------------");
			while (!rs.isAfterLast()) {

				System.out.println(rs.getInt("category_id") + " -> " + rs.getString("category_name"));
				rs.next();
			}

			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
