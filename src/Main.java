import java.util.Scanner;

public class Main {
	
	static Product product = new Product();
	static Customer customer = new Customer();
	static Order order = new Order();
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Main main = new Main();
		main.mainMenuExec();

	}
	public int getChoice() throws Exception {
		try {
			Scanner sc = new Scanner(System.in);
			String choice = sc.nextLine();
			return Integer.parseInt(choice);
		} catch (Exception e) {
			throw new Exception("Input value must be a digit. Please try again.");
		}
	}
	
	private void mainMenu() throws Exception {
		System.out.println("=======SHOP MANAGEMENT=======");
		System.out.println("1. Manage Product");
		System.out.println("2. Manage Customer");
		System.out.println("3. Manage Order");
		System.out.println("4. Exit");
		System.out.println("=============================");
		System.out.println("Please choose: ");
	}

	private void mainMenuExec() throws Exception {
		Main main = new Main();
		int choice = 0;
		while (choice != 3) {
			try {
				main.mainMenu();
				choice = getChoice();
				switch (choice) {
				case 1:
					productMenuExec();
					break;
				case 2:
					customerMenuExec();
					break;
				case 3:
					orderingMenuExec();
					break;
				case 4:
					System.out.println("Exit. Thanks for using");
					System.exit(0);
				default:
					throw new Exception("Input value must be a digit. Please try again.\n");
				}
			} catch (Exception e) {
				System.out.printf(e.getMessage());
			}
		}
	}
	private void productMenu() throws Exception {
		System.out.println("-------------->MANAGE PRODUCTS");
		System.out.println("==============================");
		System.out.println("1. Load data from memory");
		System.out.println("2. Add new product");
		System.out.println("3. Product list");
		System.out.println("4. Save data");
		System.out.println("5. Search product");
		System.out.println("6. Delete product");
		System.out.println("7. Sort product ascending");
		System.out.println("8. Delete the descendants product");
		System.out.println("9. Back to main menu");
		System.out.println("==============================");
		System.out.println("Please choose: ");
	}

	private void productMenuExec() throws Exception {
		Main main = new Main();
		int choice = 0;
		while (choice != 9) {
			try {
				main.productMenu();
				choice = getChoice();
				switch (choice) {
				case 1:
					product.loadData();
					break;
				case 2:
					product.addNewProduct();
					break;
				case 3:
					product.productDisplay();
					break;
				case 4:
					product.saveData();
					break;
				case 5:
					product.searchProduct();
					break;
				case 6:
					product.deleteProduct();
					break;
				case 7:
					product.showSortedList();
					break;
				case 8:
					product.delProductAfter();
					break;
				case 9:
					product.saveData();
					mainMenuExec();
					break;
				default:
					throw new Exception("Input value must be a digit. Please try again.\n");
				}
			} catch (Exception e) {
				System.out.printf(e.getMessage());
			}
		}
	}
	private void customerMenu() throws Exception {
		System.out.println("------------->MANAGE CUSTOMERS");
		System.out.println("==============================");
		System.out.println("1. Load data from memory");
		System.out.println("2. Add new customer");
		System.out.println("3. Customer list");
		System.out.println("4. Save customer information");
		System.out.println("5. Search customer");
		System.out.println("6. Delete customer");
		System.out.println("7. Back to main menu");
		System.out.println("==============================");
		System.out.println("Please choose: ");
	}
	
	private void customerMenuExec() throws Exception {
		Main app = new Main();
		int choice = 0;
		while (choice != 7) {
			try {
				app.customerMenu();
				choice = getChoice();
				switch (choice) {
				case 1:
					customer.loadData();
					break;
				case 2:
					customer.addNewCustomer();
					break;
				case 3:
					customer.customerDisplay();
					break;
				case 4:
					customer.saveData();
					break;
				case 5:
					customer.searchCustomer();
					break;
				case 6:
					customer.delCustomer();
					break;
				case 7:
					customer.saveData();
					mainMenuExec();
					break;
				default:
					throw new Exception("Input value must be a digit. Please try again.\n");
				}
			} catch (Exception e) {
				System.out.printf(e.getMessage());
			}
		}
	}
	
	private void orderMenu() throws Exception {
		System.out.println("---------------->MANAGE ORDERS");
		System.out.println("==============================");
		System.out.println("1. Make new order");
		System.out.println("2. Total order");
		System.out.println("3. Sort order");
		System.out.println("4. Back to main menu");
		System.out.println("==============================");
		System.out.println("Please choose: ");
	}
	
	private void orderingMenuExec() throws Exception {
		Main app = new Main();
		int choice = 0;
		while (choice != 4) {
			try {
				app.orderMenu();
				choice = getChoice();
				switch (choice) {
				case 1:
					order.addNewOrder();
					break;
				case 2:
					order.showOrderList();
					break;
				case 3:
					order.sortOrder();
					break;
				case 4:
					mainMenuExec();
					break;
				default:
					throw new Exception("Input value must be a digit. Please try again.\n");
				}
			} catch (Exception e) {
				System.out.printf(e.getMessage());
			}
		}
	}

}
