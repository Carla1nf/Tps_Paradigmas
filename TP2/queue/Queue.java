package queue;

import java.util.ArrayList;

public class Queue {
	
	public ArrayList<QueueCommander> queueList = new ArrayList<QueueCommander>();
    
	public Queue() {
		queueList.add(new EmptyQueue());
	}
	
	public boolean isEmpty() {
		return queueList.size() == 1;
	}

	public Queue add( Object  cargo ) {
		QueueCommander loadedQueue = new LoadedQueue(cargo);
		queueList.add(1,loadedQueue);
		return this;
	}
	
	public Object take() {
		return queueList.remove(size()).take();
	}

	public Object head() {
		return queueList.get(size()).head();
	}

	public int size() {
		return queueList.size() - 1;
	}
}
