import java.awt.Point;

/**
 * This class link all the stack of Nodes together
 * @author jiawen
 *
 */
public class LinkedStack {
	/**
	 * the Node at the top of the stack
	 */
	private Node top;
	
	/**
	 * constructor that create a stack
	 */
	public LinkedStack () {
		top = null;
	}
	
	/**
	 * check if the linkedstack is empty
	 * @return true if empty, otherwise false
	 */
	public boolean isEmpty () {
		return top == null;
	}
	
	/**
	 * add the item to the top of the stack
	 * @param s the new point
	 */
	public void push (Point s) {
		top = new Node (s, top);
	}
	
	/**
	 * Remove and return the item at the top of the stack
	 * @return the removed item
	 */
	public Point pop () {
		Point retVal = new Point (0,0);
		if (isEmpty()) {
			System.out.println("Nothing to Remove.");
		} else {
			retVal.setLocation(top.getValue());
			top = top.getNext();
		}
		return retVal;
	}
	
	/**
	 * See the item at the top of the stack
	 * @return the item at the top of the stack
	 */
	public Point peek () {
		Point retVal = new Point (0,0);
		if (isEmpty()) {
			System.out.println("Stack is Empty");
		} else {
			retVal.setLocation(top.getValue());
		}
		return retVal;
	}
	
	public int size () {
		int count = 0;
		Node n = top;
		while (n != null) {
			count ++;
			n = n.getNext();
		}
		return count;
	}
}
