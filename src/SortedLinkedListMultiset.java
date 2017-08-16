import java.io.PrintStream;
import java.util.*;

public class SortedLinkedListMultiset<T> extends Multiset<T>
{
	
	/** The pointer to the first node. */
	Node head;
	
	/** The pointer to the last node. */
	Node tail;
	
	/** The size of list. */
	int size;
	
	public SortedLinkedListMultiset() {
		head = null;
		tail = null;
		size = 0;
	} // end of SortedLinkedListMultiset()
	
	
	public void add(T item) {
		
		Node newNode = new Node(item);
		
		if(head == null){
			newNode.setNum(1);
			head = newNode;
			tail = newNode;
		} else {
			int existNum = search(item);
			if( existNum > 0 ) {
				Node currNode = head;
				for(int i=0;i<size;i++){
					if(currNode.getValue().equals(item)) {
						currNode.setNum( existNum + 1 );
						break;
					}
					currNode = currNode.getNext();
				}
			} else {
				newNode.setNum(1);
			}
			newNode.setPrev(tail);
			tail.setNext(newNode);
			tail = newNode;
		}
		size++;
		
	} // end of add()
	
	
	public int search(T item) {
		
		Node currNode = head;
		for(int i=0;i<size;i++){
			if(currNode.getValue().equals(item) && currNode.getNum() != 0) {
				return currNode.getNum();
			}
			currNode = currNode.getNext();
		}
		return 0;
	} // end of search()
	
	
	public void removeOne(T item) {
		
		if( search(item) > 0 ) {
			if( size > 1 ) {
				Node currNode = tail;
				boolean removed = false;
				do {
					if(currNode.getValue().equals(item)) {
						if( currNode.getPrev() == null ) {
							head = currNode.getNext();
							head.setPrev(null);
						} else if ( currNode.getNext() == null ) {
							tail = currNode.getPrev();
							tail.setNext(null);
						} else {
							currNode.getPrev().setNext(currNode.getNext());
							currNode.getNext().setPrev(currNode.getPrev());
						}
						removed = true;
						if(currNode.getNum() == 0) {
							Node updateNode = head;
							for(int i=0;i<size;i++){
								if( updateNode.getValue().equals(item) ){
									updateNode.setNum( updateNode.getNum() - 1 );
									break;
								}
								updateNode = updateNode.getNext();
							}
						}
					}
					currNode = currNode.getPrev();
				} while( !removed );
			} else {
				head = null;
				tail = null;
			}
			size--;
		}
		
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		
		if( search(item) > 0 ) {
			if( size > 1 ) {
				Node currNode = head;
				int rmvNo = 0;
				for(int i=0;i<size;i++){
					if(currNode.getValue().equals(item)) {
						if( currNode.getPrev() == null ) {
							head = currNode.getNext();
							head.setPrev(null);
						} else if ( currNode.getNext() == null ) {
							tail = currNode.getPrev();
							tail.setNext(null);
						} else {
							currNode.getPrev().setNext(currNode.getNext());
							currNode.getNext().setPrev(currNode.getPrev());
						}
						rmvNo++;
					}
					currNode = currNode.getNext();
				}
				size = size - rmvNo;
			} else {
				head = null;
				tail = null;
				size = 0;
			}
		}
		
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		
		Node currNode = head;
		for(int i=0;i<size;i++){
			if(currNode.getNum() > 0) {
				out.println(currNode.getValue() + printDelim + currNode.getNum());
			}
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
		
		public Node(T value) {
			setValue(value);
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
	
	
} // end of class SortedLinkedListMultiset