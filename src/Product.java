import java.io.Serializable;
import java.util.Scanner;

public class Product extends PMethods implements Serializable{
	private static final long serialVersionUID = -5351005067295817573L;
	String pcode, pro_name;
	int quantity, saled;
	double price;

	public Product () {
		pcode = null;
		pro_name = null;
		quantity = 0;
		saled = 0;
		price = 0;
	}

	public Product(String pcode, String pro_name, int quantity, int saled, double price) {
		this.pcode = pcode;
		this.pro_name = pro_name;
		this.quantity = quantity;
		this.saled = saled;
		this.price = price;
	}
	
	public void addNewProduct(){
		GetInput get = new GetInput();
		try{
			System.out.println("\tADD NEW PRODUCT");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			pcode = get.getInput("Input Product ID: ");
			while (checkExist(pcode)){
				System.out.println("This ProductCode is already in use. Please try again.");
				pcode = get.getInput("Input Product ID: ");
			}
			pro_name = get.getInput("Input Product Name: ");
			quantity = Integer.parseInt(get.getInput("Input Quantity: "));
			saled = Integer.parseInt(get.getInput("Input Product saled: "));
			while (saled > quantity){
				System.out.println("Only have: "+quantity+" in stock. Please try again.");
				saled = Integer.parseInt(get.getInput("Input Product saled: "));
			}
			price = Double.parseDouble(get.getInput("Input Product Price: "));

			Product pro = new Product(pcode, pro_name, quantity, saled, price);
			queue.enqueue(pro);
			System.out.println("Done!\n");
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public void productDisplay(){
		ProductNode p;
		try {
			if (!queue.isEmpty()) {
				p = queue.head;
				System.out.println("\tPRODUCT LIST");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.printf("%9s %20s %15s %10s %12s\n", "ProductCode", "Name", "Quantity", "Saled", "Price");
				while (p != null) {
					System.out.printf("%9s %25s %10d %10d %12.1f\n", 
							p.pro.pcode, p.pro.pro_name, p.pro.quantity, p.pro.saled, p.pro.price);
					p = p.next;
				}
			}
			else {
				System.out.println("------------EMPTY------------");
			}
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	
	public void searchProduct(){
		GetInput get = new GetInput();
		try {
			if (!queue.isEmpty()) {
				System.out.println("\tSEARCH PRODUCT");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				String tmp = get.getInput("Input Product Code: ");
				if (searchByPcode(tmp) != null) {
					System.out.printf("%9s %25s %10s %10s %12s\n", "ProductCode", "Name", "Quantity", "Saled", "Price");
					System.out.printf("%9s %25s %10d %10d %12.2f\n", 
				searchByPcode(tmp).pcode, searchByPcode(tmp).pro_name, searchByPcode(tmp).quantity, 
				searchByPcode(tmp).saled, searchByPcode(tmp).price);
				}
				else {
					System.out.println("Product not found");
				}
			}
			else {
				System.out.println("------------EMPTY------------");
			}
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public void showSortedList(){
		ProductNode p;
		try {
			if (!queue.isEmpty()) {
				queue = SortProductList(queue);
				p = queue.head;
				System.out.println("\tPRODUCT LIST AFTER SORTING");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.printf("%9s %25s %10s %10s %12s\n", "ProductCode", "Name", "Quantity", "Saled", "Price");
				while (p != null) {
					System.out.printf("%9s %25s %10d %10d %12.2f\n",
					p.pro.pcode, p.pro.pro_name, p.pro.quantity, p.pro.saled, p.pro.price);
					p = p.next;
				}
			}
			else {
				System.out.println("------------EMPTY------------");
			}
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public void deleteProduct() {
		GetInput get = new GetInput();
		if (!queue.isEmpty()) {
			System.out.println("\tDELETE PRODUCT");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			String xCode = get.getInput("Input Product Code: ");
			int tmp = Integer.parseInt(get.getInput("Are you sure you want to delete this customer (1/0): "));
			switch (tmp) {
				case 0:
					System.out.println("Product is kept!");
					break;
				case 1:
					System.out.print(DeleteProduct(queue, xCode));
					break;
				default:
					System.out.println("Press 1 to delete this product or press 0 to cancel.");
			}
		}
		else {
			System.out.println("------------EMPTY------------");
		}
	}

	public void delProductAfter() {
		GetInput get = new GetInput();
		if (!queue.isEmpty()) {
			System.out.println("\tDELETE DESCENDANT PRODUCT");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			String xCode = get.getInput("Input Product Code: ");
			int tmp = Integer.parseInt(get.getInput("Are you sure you want to delete this customer (1/0): "));
			switch (tmp) {
				case 0:
					System.out.println("The descendant product is kept!");
					break;
				case 1:
					System.out.print(DeleteProductAfter(queue, xCode));
					break;
				default:
					System.out.println("Press 1 to delete this product or press 0 to cancel.");
			}
		}
		else {
			System.out.println("------------EMPTY------------");
		}
	}

}
class ProductNode implements Serializable{
	Product pro;
	ProductNode next;

	public ProductNode(Product xProduct) {
		pro = xProduct;
		next = null;
	}
}

class ProductQueue implements Serializable{
	ProductNode head, tail;

	public ProductQueue() {
		head = null;
		tail = null;
	}

	public boolean isEmpty(){
		return head == null;
	}

	public void enqueue(Product xProduct){
		ProductNode lstItm = new ProductNode(xProduct);
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

	public ProductNode dequeue(){
		ProductNode p, returnItem;
		p = head;
		returnItem = head;

		if (head.next != null) {
			head = head.next;
		}
		else {
			head = null;
		}

		if (p != null) p.next = null;

		return returnItem;
	}

	public void deleteTail(){
		ProductNode p = head;
		ProductNode nearTail = null;
		while (p != null){
			if (p.next != null){
				if (p.next.next == null){
					nearTail = p;
				}
			}
			p = p.next;
		}
		tail = nearTail;
		tail.next = null;
	}
}
