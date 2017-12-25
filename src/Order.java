import java.io.Serializable;

public class Order extends OrderMethods {
	
	public String pcode, ccode;
	public int quantity;
	
	public Order () {
		pcode = null;
		ccode = null;
		quantity = 0;
	}

	public Order(String pcode, String ccode, int quantity) {
		this.pcode = pcode;
		this.ccode = ccode;
		this.quantity = quantity;
	}
	
	public void addNewOrder(){
		GetInput get = new GetInput();
		try{
			System.out.println("\tMAKE NEW ORDER");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			pcode = get.getInput("Input Product Code: ");
			while (!pro.checkExist(pcode)) {
				System.out.println("Product Code not found. Please try again.\n");
				pcode = get.getInput("Input Product Code: ");
			}
			ccode = get.getInput("Input Customer Code: ");
			while (!cus.checkExist(ccode)) {
				System.out.println("Customer Code not found. Please try again.\n");
				ccode = get.getInput("Input Customer Code: ");
			}
			quantity = Integer.parseInt(get.getInput("Input quantity: "));
			while (!checkQuantity(pcode, quantity)) {
				System.out.println("Over quantity in stock! Please try again.\n");
				quantity = Integer.parseInt(get.getInput("Input quantity: "));
			}
			Order or = new Order(pcode, ccode, quantity);
			orQueue.enqueue(or);
			System.out.println("Make order complete\n");
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public void showOrderList(){
		OrderNode o;
		try {
			if (!orQueue.isEmpty()) {
				o = orQueue.head;
				System.out.println("\tORDER LIST");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.printf("%9s %25s %10s\n", "ProductCode", "CustomerCode", "Quantity");
				while (o != null) {
					System.out.printf("%9s %25s %10s\n", o.or.pcode, o.or.ccode, o.or.quantity);
					o = o.next;
				}
				System.out.println("Total: "+countList(orQueue));
			}
			else {
				System.out.println("------------EMPTY------------");
			}
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public void DisplaySortedPcode(){
		OrderNode o;
		try {
			if (!orQueue.isEmpty()) {
				orQueue = SortOrderListByPcode(orQueue);
				o = orQueue.head;
				System.out.println("\tORDER LIST WITH PRODUCT ASCENDING");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.printf("%9s %25s %10s\n", "ProductCode", "CustomerCode", "Quantity");
				while (o != null) {
					System.out.printf("%9s %25s %10s\n", o.or.pcode, o.or.ccode, o.or.quantity);
					o = o.next;
				}
			}
			else {
				System.out.println("------------EMPTY------------");
			}
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public void DisplaySortedCcode(){
		OrderNode o;
		try {
			if (!orQueue.isEmpty()) {
				orQueue = SortOrderListByCcode(orQueue);
				o = orQueue.head;
				System.out.println("\tORDER LIST WITH CUSTOMER ASCENDING");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.printf("%9s %25s %10s\n", "ProductCode", "CustomerCode", "Quantity");
				while (o != null) {
					System.out.printf("%9s %25s %10s\n", o.or.pcode, o.or.ccode, o.or.quantity);
					o = o.next;
				}
			}
			else {
				System.out.println("------------EMPTY------------");
			}
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public void sortOrder() {
		GetInput get = new GetInput();
		try {
			System.out.print("1. Sort by Product Code\t\t");
			System.out.println("2. Sort by Customer Code");
			int choice = Integer.parseInt(get.getInput("Please choose: "));
			switch (choice) {
				case 1:
					DisplaySortedPcode();
					break;
				case 2:
					DisplaySortedCcode();
					break;
				default:
					System.out.println("Please choose 1 to sort by pcode or 2 to sort by ccode");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}


}
class OrderNode implements Serializable {
	Order or;
	OrderNode next;

	public OrderNode(Order xOrder){
		or = xOrder;
		next = null;
	}
}

class OrderQueue implements Serializable {
	OrderNode head, tail;

	public OrderQueue() {
		head = null;
		tail = null;
	}

	public boolean isEmpty(){
		return head == null;
	}

	public void enqueue(Order xOrder){
		OrderNode lstItm = new OrderNode(xOrder);
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

	public OrderNode dequeue(){
		OrderNode o, returnItem;
		o = head;
		returnItem = head;

		if (head.next != null) {
			head = head.next;
		}
		else {
			head = null;
		}

		if (o != null) o.next = null;

		return returnItem;
	}

	public void deleteTail(){
		OrderNode o = head;
		OrderNode nearTail = null;
		while (o != null){
			if (o.next != null){
				if (o.next.next == null){
					nearTail = o;
				}
			}
			o = o.next;
		}
		tail = nearTail;
		tail.next = null;
	}
}
