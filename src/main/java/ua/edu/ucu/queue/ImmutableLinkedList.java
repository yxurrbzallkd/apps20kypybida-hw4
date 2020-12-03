package ua.edu.ucu.queue;
import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList {
	private class Node {
		public Object item;
		public Node next;
		public Node previous;

		public Node(Object item, Node next, Node previous) {
			this.item = item;
			this.next = next;
			this.previous = previous;
		}

		public Node copy() {
			return new 
			Node(this.item, this.next, this.previous);
		}
	}

	private Node head;
	private Node tail;
	private int N;

	public ImmutableLinkedList() {
		this.head = new Node(null, null, null);
		this.tail = new Node(null, null, this.head);
		this.head.next = this.tail;
		this.N = 0;
	}

	public ImmutableLinkedList(Node head, Node tail, int size) {
		this.head = head;
		this.tail = tail;
		this.N = size;
	}

	public ImmutableLinkedList add(Object e) {
		//adds element at the end of collection
		ImmutableLinkedList arr = this.copy();
		Node newNode = new Node(e, this.tail, this.tail.previous);
		arr.tail.previous.next = newNode;
		arr.tail.previous = newNode;
		arr.N += 1;
		return arr;
	}

	public ImmutableLinkedList add(int index, Object e) 
	throws IndexOutOfBoundsException {
		//adds elemet at index, exception if index out of range
		if ((index < 0) || (index >= this.size())) {
			throw new 
			IndexOutOfBoundsException("can't add at index "
			+index);
		}
		ImmutableLinkedList arr = this.copy();
		Node n = arr.head;
		for (int i = 0; i < index; i++) {
			n = n.next;
		}
		Node newNode = new Node(e, n, n.next);
		n.next = newNode;
		arr.N += 1;
		return arr;
	}

    public ImmutableLinkedList addAll(Object[] c) {
		//append array to collection
		ImmutableLinkedList arr = this.copy();
		Node n;
		for (Object e:c) {
			n = new Node(e, arr.tail, arr.tail.previous);
			arr.tail.previous.next = n;
			arr.tail.previous = n;
		}
		arr.N += c.length;
		return arr;
	}

	public ImmutableLinkedList addAll(int index, Object[] c) 
	throws IndexOutOfBoundsException {
		if ((index < 0) || (index >= this.size())) {
			throw new 
			IndexOutOfBoundsException("can't add at index "
			+index);
		}
		ImmutableLinkedList arr = this.copy();
		Node t = arr.head.next;
		for (int i = 0; i < index; i++) {
			t = t.next;
		}
		Node n;
		for (Object e:c) {
			n = new Node(e, t, t.previous);
			t.previous.next = n;
			t.previous = n;
		}
		arr.N += c.length;
		return arr;
	}

	public Object get(int index) 
	throws IndexOutOfBoundsException {
		if ((index < 0) || (index >= this.size())) {
			throw new 
			IndexOutOfBoundsException("can't get index "
			+index);
		}
		Node t = this.head.next;
		for (int i = 0; i < index; i++) {
			t = t.next;
		}
		return t.item;
	}

	public ImmutableLinkedList remove(int index) 
	throws IndexOutOfBoundsException {
		if ((index < 0) || (index >= this.size())) {
			throw new 
			IndexOutOfBoundsException("can't remove from index "
			+index);
		}
		ImmutableLinkedList arr = this.copy();
		Node t = arr.head.next;
		for (int i = 0; i < index; i++) {
			t = t.next;
		}
		t.previous.next = t.next;
		arr.N -= 1;
		return arr;
	}

	public ImmutableLinkedList set(int index, Object e) 
	throws IndexOutOfBoundsException {
		if ((index < 0) || (index >= this.size())) {
			throw new 
			IndexOutOfBoundsException("can't set the index "
			+index);
		}
		ImmutableLinkedList arr = this.copy();
		Node t = arr.head.next;
		for (int i = 0; i < index; i++) {
			t = t.next;
		}
		t.item = e;
		return arr;
	}

    public int indexOf(Object e) {
		Node n = this.head.next;
		for (int i = 0; i < this.size(); i++) {
			if (n.item == e) {
				return i;
			}
			n = n.next;
		}
		return -1;
	}

    public int size() {
		return this.N;
	}

    public ImmutableLinkedList clear() {
		return new ImmutableLinkedList();
	}

    public boolean isEmpty() {
		return this.size() == 0;
	}

    public Object[] toArray() {
		Object[] data = new Object[this.size()];
		Node n = this.head.next;
		for (int i = 0; i < this.size(); i++) {
			data[i] = n.item;
			n = n.next;
		}
		return data;
	}

	private ImmutableLinkedList copy() {
		Node newHead = this.head.copy();
		Node n = newHead;
		for (int i = 0; i < this.size()+1; i++) {
			n.next = n.next.copy();
			n.next.previous = n;
			n = n.next;
		}
		Node newTail = n;
		return new ImmutableLinkedList(newHead, newTail, this.size());
	}
	
    @Override
    public String toString() {
		return Arrays.toString(this.toArray());
	}

   public ImmutableLinkedList addFirst(Object e) {
		ImmutableLinkedList arr = this.copy();
		Node newNode = new Node(e, arr.head.next, arr.head);
		arr.head.next.previous = newNode;
		arr.head.next = newNode;
		arr.N += 1;
		return arr;
	}

	public ImmutableLinkedList addLast(Object e) {
		return this.add(e);
	}

	public Object getFirst() 
	throws IndexOutOfBoundsException {
		//returns first element
		if (this.isEmpty()) {
			throw new 
			IndexOutOfBoundsException("can't get first object of empty list");
		}
		return this.get(0);
	}

	public Object getLast() 
	throws IndexOutOfBoundsException {
		//returns last element
		if (this.isEmpty()) {
			throw new 
			IndexOutOfBoundsException("can't get last object of empty list");
		}
		return this.get(this.size()-1);
	}

	public ImmutableLinkedList removeFirst() {
		return this.remove(0);
	}

	public ImmutableLinkedList removeLast() {
		return this.remove(this.size()-1);
	}
}
