package queue;

import java.util.ArrayList;

public class Queue {
	
	public ArrayList<Object> queueList = new ArrayList<Object>();
	
    public boolean isEmpty() {
    	return queueList.isEmpty();
    }

	public Queue add( Object  cargo ) {
		queueList.add(new LoadedQueue(cargo));
		return this;
	}
	
	public Object take() {
		QueueCommander removeElement = queueList.remove(0);
		return removeElement.take();
	}

	public Object head() {
		QueueCommander firstElement = queueList.get(0);
		return firstElement.head();
	}

	public int size() {
		return queueList.size();
	}
}
