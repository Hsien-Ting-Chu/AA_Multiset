import java.io.PrintStream;
import java.util.*;

public class LinkedListMultiset<T> extends Multiset<T>
{
	/** The pointer to the first node. */
	Node head;
	
	/** The pointer to the last node. */
	Node tail;
	
	/** The size of list. */
	int size;
	
	public LinkedListMultiset() {
		head = null;
		tail = null;
		size = 0;
	} // end of LinkedListMultiset()
	
	
	public void add(T item) {
		
		Node newNode = new Node(item);
		
		if(head == null){
			newNode.setNum(1);
			head = newNode;
			tail = newNode;
		} else {
			if(search(item) > 0){
				newNode.setNum(search(item)+1);
				Node currNode = head;
				for(int i=0;i<size;i++){
					if(currNode.getValue() == item) {
						currNode.setNum(0);
					}
					currNode = currNode.getNext();
				}
			} else {
				newNode.setNum(1);
			}
			newNode.setNext(head);
			head.setPrev(newNode);
			head = newNode;
			size++;
		}
		
	} // end of add()
	
	
	public int search(T item) {
		
		Node currNode = head;
		for(int i=0;i<size;i++){
			if(currNode.getValue() == item && currNode.getNum() != 0) {
				return currNode.getNum();
			}
			currNode = currNode.getNext();
		}
		return 0;
	} // end of search()
	
	
	public void removeOne(T item) {
		
		Node currNode = head;
		for(int i=0;i<size;i++){
			if(currNode.getValue() == item) {
				currNode.getPrev().setNext(currNode.getNext());
				if(currNode.getNext() != null) {
					currNode.getNext().setPrev(currNode.getPrev());
				} else {
					tail = currNode.getPrev();
				}
				if(currNode.getNum() > 1) {
					int newNum = currNode.getNum() - 1;
					do {
						currNode = currNode.getNext();
						if(currNode.getValue() == item) {
							currNode.setNum(newNum);
						}
					} while(currNode.getValue() != item);
				}
				size--;
			}
			currNode = currNode.getNext();
		}
		
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement me!
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		// Implement me!
	} // end of print()
	
	
	/*public int checkNum(T item) {
		
		Node currNode = head;
		for(int i=0;i<size;i++){
			if(currNode.getValue() == item){
				return currNode.getValue();
			}
			currNode = currNode.getNext();
		}
		
	} // end of checkNum()
*/	
	
	private class Node {
		
		/** The value stored in this node. */
		T value;
		
		/** The value stored in this node. */
		int num;
		
		/** The pointer to the next node. */
		Node next;
		
		/** The pointer to the previous node. */
		Node prev;
		
		public Node(T value) {
			this.value = value;
			num = 0;
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
		
	}
	
} // end of class LinkedListMultiset