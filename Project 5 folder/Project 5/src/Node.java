import java.awt.Point;

/**
 * This class contain the Node of the stack
 * @author jiawen
 *
 */
public class Node {
	/**
	 * the point (x,y)
	 */
	private Point value;
	/**
	 * the next node of the point
	 */
	private Node next;
	
	/**
	 * constructor that create a new node of point
	 * @param val the point value
	 * @param n the next Node after the point value
	 */
	public Node (Point val, Node n) {
		value = val;
		next = n;
	}
	
	public Node (Point val) {
		value = val;
		next = null;
	}
	
	/**
	 * get the value of the point
	 * @return the x and y value of the point
	 */
	public Point getValue () {
		return value;
	}
	
	/**
	 * get the Next Node
	 * @return the next Point
	 */
	public Node getNext (){
		return next;
	}
	
	/**
	 * set a new point the the value
	 * @param v the new point
	 */
	public void setValue (Point v) {
		value = v;
	}
	
	/**
	 * set the next point
	 * @param n the next node
	 */
	public void setNext (Node n) {
		next = n;
	}
	
}
