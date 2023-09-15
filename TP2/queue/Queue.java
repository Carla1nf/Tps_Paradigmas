package queue;

import java.util.ArrayList;

public class Queue {
	
	public ArrayList<QueueCommander> queueList = new ArrayList<>();
    
	public Queue() {
		queueList.add(new EmptyQueue());
	}
	
	public boolean isEmpty() {
		return queueList.get(size()).isEmpty();
	}

	public Queue add( Object  cargo ) {
		queueList.add(1,new LoadedQueue(cargo));
		return this;
	}
	
	public Object take() {
		return queueList.remove(size()).getCargo();
	}

	public Object head() {
		return queueList.get(size()).getCargo();
	}

	public int size() {
		return queueList.size() - 1;
	}
}
