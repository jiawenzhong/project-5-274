import java.awt.Point;

public class LinkedQueue {

	private Node first;
	private Node last;
	
	public LinkedQueue () {
		first = null;
		last = null;
	}
	
	public boolean isEmpty () {
		return first == null;
	}
	
	public void add (Point s) {
		if (isEmpty()) {
			first = new Node(s);
			last = first;
		} else {
			Node n = new Node(s);
			last.setNext(n);
			last = n;
		}
	}
	
	public Point remove () {
		Point n = new Point(0, 0);
		if (isEmpty()) {
			System.out.println("Nothing to Remove");
		} else {
			n.setLocation(first.getValue());
			first = first.getNext();
			if (first == null) {
				last = null;
			}
		}
		return n;
	}
	
	public Point peek () {
		Point n = new Point(0, 0);
		if (isEmpty()) {
			System.out.println("Queue is Empty.");
		} else {
			n = first.getValue();
		}
		return n;
	}
	
	public int size () {
		int count = 0;
		Node n = first;
		while (n != null) {
			count ++;
			n = n.getNext();
		}
		return count;
	}
	
	public String toString () {
		String s = "";
		Node n = first;
		while (n != null) {
			s = s + n.getValue();
		}
		return s;
	}
}
