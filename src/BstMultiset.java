import java.io.PrintStream;
import java.util.*;

public class BstMultiset<T> extends Multiset<T> {

	Node<T> parent;
	Node<T> root;
	int size;

	public BstMultiset() {
		parent = null;
		size = 0;
	} // end of BstMultiset()

	public void add(T item) {
		Node current = new Node(item);

		/* The first element */
		if (root == null) {
			root = current;
			root.num = 1;
			return;

		}
		parent = root;
		while (true) {
			if (parent.value.toString().compareTo(current.value.toString()) == 0) {
				parent.num++;
			} else if (parent.value.toString().compareTo(current.value.toString()) < 0) {
				if(parent.left == null)
					parent.left = current;
				else
				parent = parent.left;
				return;
				
			} else if (parent.value.toString().compareTo(current.value.toString()) > 0) {
				if(parent.right == null)
					parent.right = current;
				else
				parent = parent.right;
				return;
			}
		}

	} // end of add()

	public int search(T item) {
		// Implement me!

		// default return, please override when you implement this method
		return 0;
	} // end of add()

	public void removeOne(T item) {
		// Implement me!
	} // end of removeOne()

	public void removeAll(T item) {
		// Implement me!
	} // end of removeAll()

	public void print(PrintStream out) {
		// Implement me!
	} // end of print()

	class Node<T> implements Comparable<T> {
		public int num;
		public T value;
		public Node<T> left;
		public Node<T> right;

		Node(T value) {
			num = 0;
			this.value = value;
			this.right = null;
			this.left = null;
		}

		@Override
		public int compareTo(T value) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
} // end of class BstMultiset
