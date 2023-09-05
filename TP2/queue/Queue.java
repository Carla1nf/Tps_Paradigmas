package queue;

import java.util.ArrayList;
import java.util.List;

public class Queue {
	
	public static String QUEUE_IS_EMPTY = "Queue is empty";
	public List<Object> queue = new ArrayList<>();

    public boolean isEmpty() {
		return queue.isEmpty();
	}

	public Queue add( Object  cargo ) {
		this.queue.add(cargo);
		return this;
	}
	
	public Object take() {
		if (this.queue.isEmpty()){
			return new Error( QUEUE_IS_EMPTY);
		}
		Object lastAdded = this.queue.get(lastQueueObject());
		this.queue.remove(lastQueueObject());
		return lastAdded;
	}

	public Object head() {
		if (this.queue.isEmpty()){
			return new Error( QUEUE_IS_EMPTY);
		}
		else{
			return this.queue.get(0);
		}
	}

	public int size() {
		return this.queue.size();
	}

	private int lastQueueObject() {
		return this.queue.size()-1;
	}
}
