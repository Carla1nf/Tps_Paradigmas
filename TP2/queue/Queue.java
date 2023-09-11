package queue;

import java.util.ArrayList;

public class Queue {
	
	public ArrayList<Object> queueList = new ArrayList<Object>();
    public boolean isEmpty() {return queueList.isEmpty();}

	public Queue add( Object  cargo ) {
		queueList.add(cargo);
		return this;
	}
	
	public Object take() {
		Queue guardedQueue = queueProtector();
		guardedQueue.take();
		
		return queueList.remove(0);
	}

	public Object head() {
		Queue guardedQueue = queueProtector();
		guardedQueue.head();
		
		return queueList.get(0);
	}

	public int size() {
		return queueList.size();
	}
	
	private Queue queueProtector() {
		final Queue[] queueNew = {new EmptyQueue()};
		queueList.forEach(item -> {
		    queueNew[0] = queueNew[0].add(item);
		});

		return queueNew[0];

	}
}
