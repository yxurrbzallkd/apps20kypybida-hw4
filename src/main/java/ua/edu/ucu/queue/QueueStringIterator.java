package ua.edu.ucu.queue;
import java.util.Iterator;

public class QueueStringIterator implements Iterator<String> {
	private Queue q;

	public QueueStringIterator(Queue q) {
		this.q = q;
	}
	@Override
	public boolean hasNext() {
		return this.q.peek() != null;
	}
	@Override
	public String next() {
		return this.q.dequeue().toString();
	}
	@Override
	public void remove() { 
        throw new UnsupportedOperationException(); 
    } 
}
