import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		DBConnection db = new DBConnection();
		Scanner scan = new Scanner(System.in);
		while (true) {
			String input;
			System.out.println("Welcome to the E-commerce");
			System.out.println("Login or SignUp!");
			System.out.println("1 - Login");
			System.out.println("2 - Sign Up");
			System.out.println("3 - End Program");
			input = scan.nextLine();
			if (input.equalsIgnoreCase("1")) {
				System.out.print("Enter an username: ");
				String username = scan.nextLine();
				System.out.print("Enter a password: ");
				String password = scan.nextLine();
				if (db.loginControl(username, password)) {
					System.out.println(username + " Welcome!");

					while (true) {
						System.out.println("What do you want ?");
						System.out.println("1 - List all users");
						System.out.println("2 - List all products");
						System.out.println("3 - Add products");
						System.out.println("4 - Delete products");
						System.out.println("5 - Quit Account");
						String input2=scan.nextLine();
						if (input2.equalsIgnoreCase("1")) {
							db.displayCustomers();
						}
						else if (input2.equalsIgnoreCase("2")) {
							db.displayProducts();
						}
						else if (input2.equalsIgnoreCase("3")) {
							System.out.println("Enter a product name: ");
							String product_name=scan.nextLine();
							System.out.println("Enter a product price: ");
							String Sproduct_price=scan.nextLine();
							double product_price=Double.parseDouble(Sproduct_price);
							System.out.println("Enter a stock amount: ");
							String Sstock_amount=scan.nextLine();
							int stock_amount=Integer.parseInt(Sstock_amount);
							System.out.println("Enter a category id: ");
							db.getCategories();
							String Scategory_id=scan.nextLine();
							int category_id=Integer.parseInt(Scategory_id);
							db.addProduct(product_name, product_price, stock_amount, category_id);
						}
						else if (input2.equalsIgnoreCase("4")) {
							System.out.println("Enter a product id: ");
							String Sp_id=scan.nextLine();
							int p_id=Integer.parseInt(Sp_id);
							db.deleteProduct(p_id);
						}
						else if (input2.equalsIgnoreCase("5")) {
							System.out.println("------------------------\nExiting account.");
							break;
						}

					}

				} else {
					System.out.println("Invalid username and password.");
				}

			} else if (input.equalsIgnoreCase("2")) {
				System.out.println("Enter a first name: ");
				String customer_first_name=scan.nextLine();
				System.out.println("Enter a last name: ");
				String customer_last_name=scan.nextLine();
				System.out.println("Enter an user name: ");
				String customer_user_name=scan.nextLine();
				System.out.println("Enter a password: ");
				String customer_password=scan.nextLine();
				System.out.println("Enter a gender: ");
				String customer_gender=scan.nextLine();
				System.out.println("Enter an address: ");
				String customer_address=scan.nextLine();
				System.out.println("Enter a phone number: ");
				String customer_phone_number=scan.nextLine();
				db.addCustomer(customer_first_name, customer_last_name, customer_user_name, customer_password, customer_gender, customer_address, customer_phone_number);
			} else if (input.equalsIgnoreCase("3")) {
				System.out.println("------------------------\nExiting program.");
				break;
			}
		}

		try {
			db.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
