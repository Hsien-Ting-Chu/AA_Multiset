import java.io.PrintStream;
import java.util.*;

public class SortedLinkedListMultiset<T extends Comparable<T>> extends Multiset<T>
{
	/** The pointer to the first node. */
	protected Node head;
	
	/** The pointer to the last node. */
	protected Node tail;
	
	/** The size of list. */
	protected int size;
	
	public SortedLinkedListMultiset() {
		head = null;
		tail = null;
		size = 0;
	} // end of SortedLinkedListMultiset()
	
	
	public void add(T item) {
		
		Node newNode = new Node(item, 1);
		
		if(head == null){
			head = newNode;
			tail = newNode;
		} else {
			Node currNode = head;
			
			while( currNode.getNext() != null && item.compareTo(currNode.getNext().getValue()) >= 0 ) {
				currNode = currNode.getNext();
			}
			
			if( currNode.getValue().equals(item) ) {
				currNode.incNum();
			} else if( currNode == head && item.compareTo(currNode.getValue()) <= -1 ) {
				currNode.setPrev(newNode);
				newNode.setNext(currNode);
				head = newNode;
			} else if( currNode.getNext() == null ) {
				newNode.setPrev(currNode);
				currNode.setNext(newNode);
				tail = newNode;
			} else {
				newNode.setPrev(currNode);
				newNode.setNext(currNode.getNext());
				currNode.getNext().setPrev(newNode);
				currNode.setNext(newNode);
			}
		}
		size++;
		
	} // end of add()
	
	
	public int search(T item) {
		
		Node currNode = head;
		while( currNode != null ) {
			if(currNode.getValue().equals(item)) {
				return currNode.getNum();
			}
			currNode = currNode.getNext();
		}
		return 0;
	} // end of search()
	
	
	public void removeOne(T item) {
		
		Node currNode = head;
		while( currNode != null ) {
			if(currNode.getValue().equals(item)) {
				currNode.decNum();
				if(currNode.getNum() == 0){
					if(currNode == head && currNode.getNext() != null) {
						currNode.getNext().setPrev(null);
						head = currNode.getNext();
					} else if (currNode.getNext() != null) {
						currNode.getPrev().setNext(currNode.getNext());
						currNode.getNext().setPrev(currNode.getPrev());
					} else if (currNode == tail && currNode.getPrev() != null) {
						currNode.getPrev().setNext(null);
						tail = currNode.getPrev();
					} else {
						head = null;
						tail = null;
					}
				}
				size--;
				return;
			}
			currNode = currNode.getNext();
		}
		
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		
		Node currNode = head;
		while( currNode != null ) {
			if(currNode.getValue().equals(item)) {
				if(currNode == head && currNode.getNext() != null) {
					currNode.getNext().setPrev(null);
					head = currNode.getNext();
				} else if (currNode.getNext() != null) {
					currNode.getPrev().setNext(currNode.getNext());
					currNode.getNext().setPrev(currNode.getPrev());
				} else if (currNode == tail && currNode.getPrev() != null) {
					currNode.getPrev().setNext(null);
					tail = currNode.getPrev();
				} else {
					head = null;
					tail = null;
				}
				size--;
			}
			currNode = currNode.getNext();
		}
		
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		
		Node currNode = head;
		while( currNode != null ) {
			out.println(currNode.getValue() + printDelim + currNode.getNum());
			currNode = currNode.getNext();
		}
		
	} // end of print()
	
	
	private class Node {
		
		/** The value stored in this node. */
		T value;
		
		/** The value stored in this node. */
		int num;
		
		/** The pointer to the next node. */
		Node next;
		
		/** The pointer to the previous node. */
		Node prev;
		
		public Node(T value, int num) {
			setValue(value);
			setNum(num);
			next = null;
			prev = null;
		}
		
		public T getValue() {
			return value;
		}
		
		public int getNum() {
			return num;
		}
		
		public Node getNext() {
			return next;
		}
		
		public Node getPrev() {
			return prev;
		}
		
		public void setValue(T value) {
			this.value = value;
		}
		
		public void setNum(int num) {
			this.num = num;
		}
		
		public void setNext(Node next) {
			this.next = next;
		}
		
		public void setPrev(Node prev) {
			this.prev = prev;
		}
		
		public void incNum() {
			num++;
		}
		
		public void decNum() {
			num--;
		}
		
	}
	
	
} // end of class SortedLinkedListMultiset