import java.io.Serializable;

public class Customer extends CMethods implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8545649357991360570L;
	String ccode, cus_name, phone;
	
	public Customer () {
		ccode = null;
		cus_name = null;
		phone = null;
	}
	
	public Customer(String ccode, String cus_name, String phone) {
		this.ccode = ccode;
		this.cus_name = cus_name;
		this.phone = phone;
	}
	
	public void delCustomer() {
		GetInput get = new GetInput();
		if (!cusQueue.isEmpty()) {
			System.out.println("\tDELETE CUSTOMER");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			String xCode = get.getInput("Input Customer Code: ");
			System.out.print("Are you sure you want to delete this customer (1/0): ");
			int tmp = Integer.parseInt(get.getInput("Are you sure you want to delete this customer (1/0): "));
			switch (tmp) {
				case 0:
					System.out.println("Customer is not deleted!\n");
					break;
				case 1:
					System.out.print(DeleteCustomer(cusQueue, xCode));
					break;
				default:
					System.out.println("Press 1 to delete this customer or press 0 to cancel.\n");
			}
		}
		else {
			System.out.println("------------EMPTY------------");
		}
	}

	public void searchCustomer(){
		GetInput get = new GetInput();
		CustomerNode c;
		try {
			if (!cusQueue.isEmpty()) {
				System.out.println("\tSEARCH CUSTOMER");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				String tmp = get.getInput("Input Customer Code: ");
				if (searchByCcode(tmp) != null) {
					System.out.printf("%9s %25s %10s\n", "CustomerCode", "Name", "Phone");
					System.out.printf("%9s %25s %10s\n", 
					searchByCcode(tmp).ccode, searchByCcode(tmp).cus_name, searchByCcode(tmp).phone);
				}
				else {
					System.out.println("Customer not found\n");
				}
			}
			else {
				System.out.println("------------EMPTY------------");
			}
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public void customerDisplay(){
		CustomerNode c;
		try {
			if (!cusQueue.isEmpty()) {
				c = cusQueue.head;
				System.out.println("\tCUSTOMER LIST");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.printf("%9s %20s %15s\n", "Customer Code", "Name", "Phone");
				while (c != null) {
					System.out.printf("%9s %25s %15s\n", c.cus.ccode, c.cus.cus_name, c.cus.phone);
					c = c.next;
				}
			}
			else {
				System.out.println("------------EMPTY------------");
			}
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public void addNewCustomer(){
		GetInput get = new GetInput();
		try{
			System.out.println("\tADD NEW CUSTOMER");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			ccode = get.getInput("Input Customer ID: ");
			while (checkExist(ccode)){
				System.out.println("This CustomerCode is already in use. Please try again.\n");
				ccode = get.getInput("Input Customer ID: ");
			}
			cus_name = get.getInput("Input Customer Name: ");
			phone = get.getInput("Input Customer Phone: ");
			Customer cus = new Customer(ccode, cus_name, phone);
			cusQueue.enqueue(cus);
			System.out.println("Add customer complete\n");
		} catch (Exception e) {
			System.out.print(e);
		}
	}

}

class CustomerNode implements Serializable {
	Customer cus;
	CustomerNode next;

	public CustomerNode(Customer xCustomer){
		cus = xCustomer;
		next = null;
	}
}

class CustomerQueue implements Serializable {
	
	private static final long serialVersionUID = 1489053742634441215L;
	CustomerNode head, tail;

	public CustomerQueue() {
		head = null;
		tail = null;
	}

	public boolean isEmpty(){
		return head == null;
	}

	public void enqueue(Customer xCustomer){
		CustomerNode lstItm = new CustomerNode(xCustomer);
		if (head == null){
			head = tail = lstItm;
			head.next = null;
			tail.next = null;
		}
		else{
			tail.next = lstItm;
			tail = tail.next;
			tail.next = null;
		}
	}

	public CustomerNode dequeue(){
		CustomerNode c, returnItem;
		c = head;
		returnItem = head;

		if (head.next != null) {
			head = head.next;
		}
		else {
			head = null;
		}

		if (c != null) c.next = null;

		return returnItem;
	}

	public void delTail(){
		CustomerNode c = head;
		CustomerNode predTail = null;
		while (c != null){
			if (c.next != null){
				if (c.next.next == null){
					predTail = c;
				}
			}
			c = c.next;
		}
		tail = predTail;
		tail.next = null;
	}
}