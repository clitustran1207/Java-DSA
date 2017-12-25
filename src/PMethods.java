import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PMethods {
	ProductQueue queue = new ProductQueue();
	
	public Product searchByPcode(String xCode){
		ProductNode p;
		Product tmp = new Product();
		p = queue.head;
		while (p != null) {
			if (p.pro.pcode.compareTo(xCode) == 0) {
				tmp = p.pro;
				break;
			}
			else {
				tmp = null;
			}
			p = p.next;
		}
		return tmp;
	}

	public boolean checkExist(String xCode){
		ProductNode p;
		int tmp1 = 0;
		p = queue.head;
		while (p != null) {
			if (p.pro.pcode.compareTo(xCode) == 0) {
				tmp1++;
				break;
			}
			p = p.next;
		}
		if (tmp1 != 0)
			return true;
		else
			return false;
	}

	public int countList(ProductQueue xList){
		ProductNode p;
		p = xList.head;
		int tmp = 0;
		while (p != null) {
			tmp++;
			p = p.next;
		}
		return tmp;
	}
	public String DeleteProduct(ProductQueue xList, String xCode) {
		ProductNode p;
		
		if (xCode.compareTo(xList.head.pro.pcode) == 0){
			ProductNode tmp = xList.dequeue();
			System.out.printf("Product is deleted!");
		}
		else if (xCode.compareTo(xList.tail.pro.pcode) == 0){
			xList.deleteTail();
			System.out.printf("Product is deleted!");
		}
		else {
			int first = 0;
			int second = 0;
			p = xList.head;
			while (p != null) {
				first++;
				if (p.pro.pcode.compareTo(xCode) == 0) {
					break;
				}
				p = p.next;
			}
			p = xList.head;
			while (second < first) {
				second++;
				if (second == first-1) {
					p.next = p.next.next;
					break;
				}
				p = p.next;
			}
		}
		return "Product is deleted!\n";
	}

	public ProductQueue SortProductList(ProductQueue xlist) {
		ProductQueue left = new ProductQueue();
		ProductQueue right = new ProductQueue();
		ProductQueue sorted = new ProductQueue();
		ProductNode pivot = xlist.dequeue();
		while (!xlist.isEmpty()){
			ProductNode tmp = xlist.dequeue();
			if(tmp.pro.pcode.compareTo(pivot.pro.pcode) <= 0) {
				left.enqueue(tmp.pro);
			}
			else{
				right.enqueue(tmp.pro);
			}
		}
		if (countList(left) > 1)
			left = SortProductList(left);
		if (countList(right) > 1)
			right = SortProductList(right);
		while (!left.isEmpty()){
			sorted.enqueue(left.dequeue().pro);
		}
		sorted.enqueue(pivot.pro);
		while (!right.isEmpty()){
			sorted.enqueue(right.dequeue().pro);
		}
		return sorted;
	}

	public String DeleteProduct1(ProductQueue xList, String xCode) {
		
		if (xCode.compareTo(xList.head.pro.pcode) == 0){
			ProductNode tmp = xList.dequeue();
			System.out.printf("Product is deleted!");
		}
		else if (xCode.compareTo(xList.tail.pro.pcode) == 0){
			xList.deleteTail();
			System.out.printf("Product is deleted!");
		}
		else {
			ProductNode now = xList.head;
			ProductNode next = xList.head.next;
			for(; next!=null && now.pro.pcode.compareTo(xCode) != 0; 
			now = now.next, next = next.next);
			if(next != null)
				now.next = next.next;
		}
		return "Product is deleted!\n";
	}

	public String DeleteProductAfter(ProductQueue xList, String xCode) {
		ProductNode p;
		p = xList.head;
		String stt="";
		if (xCode.compareTo(xList.head.pro.pcode) == 0){
			p.next = p.next.next;
			stt = "The descendant product is deleted!\n";
		}
		else if (xCode.compareTo(xList.tail.pro.pcode) == 0){
			stt = "This is the last product!\n";
		}
		else {
			p = xList.head;
			while (p != null) {
				if (p.pro.pcode.compareTo(xCode) == 0) {
					p.next = p.next.next;
					stt = "The descendant product is deleted!\n";
					break;
				}
				p = p.next;
			}
		}
		return stt;
	}

	public void saveData(){
		try {
			if (!queue.isEmpty()) {
				FileOutputStream fos = new FileOutputStream("product.txt");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(queue);
				fos.close();
				oos.close();
				System.out.println("Product list is saved to file!\n");
			}
			else {
				System.out.println("------------EMPTY------------");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void loadData(){
		queue = new ProductQueue();
		try {
			FileInputStream fis = new FileInputStream("product.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			queue = (ProductQueue) ois.readObject();
			fis.close();
			ois.close();
			System.out.println("Data is loaded to product list\n");
		} catch (Exception e) {
			System.out.println(e);
		}
	}


}
