package queue;

import java.util.ArrayList;

public class Queue {
	
	public ArrayList<QueueCommander> queueList = new ArrayList<>();
    
	public Queue() {
		queueList.add(new EmptyQueue());
	}
	
	public boolean isEmpty() {
		return queueList.size() == 1;
	}

	public Queue add( Object  cargo ) {
		queueList.add(1,new LoadedQueue(cargo));
		return this;
	}
	
	public Object take() {
		return queueList.remove(size()).test();
	}

	public Object head() {
		return queueList.get(size()).test();
	}

	public int size() {
		return queueList.size() - 1;
	}
}
