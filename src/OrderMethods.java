
public class OrderMethods {
	
	OrderQueue orQueue = new OrderQueue();
	
	Product pro = Main.product;

	Customer cus = Main.customer;
	
	public int countList(OrderQueue xList){
		OrderNode o;
		o = xList.head;
		int tmp = 0;
		while (o != null) {
			tmp++;
			o = o.next;
		}
		return tmp;
	}

	public boolean checkQuantity(String pcode, int quantity) {
		if (quantity <= pro.searchByPcode(pcode).quantity){
			pro.searchByPcode(pcode).quantity = pro.searchByPcode(pcode).quantity - quantity;
			pro.searchByPcode(pcode).saled = pro.searchByPcode(pcode).saled +quantity;
			return true;
		}
		else
			return false;
	}

	public OrderQueue SortOrderListByPcode(OrderQueue xlist) {
		OrderQueue left = new OrderQueue();
		OrderQueue right = new OrderQueue();
		OrderNode pivot = xlist.dequeue();
		OrderQueue rs = new OrderQueue();
		while (!xlist.isEmpty()){
			OrderNode tmp = xlist.dequeue();
			if(tmp.or.pcode.compareTo(pivot.or.pcode) <= 0) {
				left.enqueue(tmp.or);
			}
			else{
				right.enqueue(tmp.or);
			}
		}
		if (countList(left) > 1)
			left = SortOrderListByPcode(left);
		if (countList(right) > 1)
			right = SortOrderListByPcode(right);
		while (!left.isEmpty()){
			rs.enqueue(left.dequeue().or);
		}
		rs.enqueue(pivot.or);
		while (!right.isEmpty()){
			rs.enqueue(right.dequeue().or);
		}
		return rs;
	}

	public OrderQueue SortOrderListByCcode(OrderQueue xlist) {
		OrderQueue left = new OrderQueue();
		OrderQueue right = new OrderQueue();
		OrderNode pivot = xlist.dequeue();
		OrderQueue rs = new OrderQueue();
		while (!xlist.isEmpty()){
			OrderNode tmp = xlist.dequeue();
			if(tmp.or.ccode.compareTo(pivot.or.ccode) <= 0) {
				left.enqueue(tmp.or);
			}
			else{
				right.enqueue(tmp.or);
			}
		}
		if (countList(left) > 1)
			left = SortOrderListByCcode(left);
		if (countList(right) > 1)
			right = SortOrderListByCcode(right);
		while (!left.isEmpty()){
			rs.enqueue(left.dequeue().or);
		}
		rs.enqueue(pivot.or);
		while (!right.isEmpty()){
			rs.enqueue(right.dequeue().or);
		}
		return rs;
	}

}
