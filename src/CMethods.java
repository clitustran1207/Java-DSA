import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CMethods {
	
	CustomerQueue cusQueue = new CustomerQueue();
	
	public Customer searchByCcode(String xCode){
		CustomerNode c;
		Customer tmp = new Customer();
		c = cusQueue.head;
		while (c != null) {
			if (c.cus.ccode.compareTo(xCode) == 0) {
				tmp = c.cus;
				break;
			}
			else {
				tmp = null;
			}
			c = c.next;
		}
		return tmp;
	}

	public boolean checkExist(String xCode){
		CustomerNode c;
		int tmp1 = 0;
		c = cusQueue.head;
		while (c != null) {
			if (c.cus.ccode.compareTo(xCode) == 0) {
				tmp1++;
				break;
			}
			c = c.next;
		}
		if (tmp1 != 0)
			return true;
		else
			return false;
	}

	public void saveData(){
		try {
			if (!cusQueue.isEmpty()) {
				FileOutputStream fos = new FileOutputStream("customer.txt");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(cusQueue);
				fos.close();
				oos.close();
				System.out.println("Customer list is saved to file!\n");
			}
			else {
				System.out.println("No data in customer list");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void loadData(){
		cusQueue = new CustomerQueue();
		try {
			FileInputStream fis = new FileInputStream("customer.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			cusQueue = (CustomerQueue) ois.readObject();
			fis.close();
			ois.close();
			System.out.println("Data is loaded to customer list\n");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String DeleteCustomer(CustomerQueue xList, String xCode) {
		CustomerNode c;
		
		if (xCode.compareTo(xList.head.cus.ccode) == 0){
			CustomerNode tmp = xList.dequeue();
			System.out.printf("Customer is deleted!");
		}
		else if (xCode.compareTo(xList.tail.cus.ccode) == 0){
			xList.delTail();
			System.out.printf("Customer is deleted!");
		}
		else {
			int index1 = 0;
			int index2 = 0;
			c = xList.head;
			while (c != null) {
				index1++;
				if (c.cus.ccode.compareTo(xCode) == 0) {
					break;
				}
				c = c.next;
			}
			c = xList.head;
			while (index2 < index1) {
				index2++;
				if (index2 == index1-1) {
					c.next = c.next.next;
					break;
				}
				c = c.next;
			}
		}
		return "Customer is deleted!\n";
	}

}
