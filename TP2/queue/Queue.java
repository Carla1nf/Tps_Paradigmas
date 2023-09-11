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
		return this.queueList.size();
	}
	
	private Queue queueProtector() {
		Queue queueNew = new EmptyQueue();
		for (Object i : queueList) queueNew = queueNew.add(i);
		return queueNew;
	}
}
