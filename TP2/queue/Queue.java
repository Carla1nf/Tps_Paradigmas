package queue;

import java.util.ArrayList;

public class Queue {
	
	public static String QUEUE_IS_EMPTY = "Queue is empty";
	public ArrayList<Object> queueList = new ArrayList<Object>();
	public static Queue queueExtra = new EmptyQueue();

    public boolean isEmpty() {return queueList.isEmpty();}

	public Queue add( Object  cargo ) {
		queueExtra = queueExtra.add(cargo);
		queueList.add(cargo);
		return this;
	}
	
	public Object take() {
		queueExtra.take();
		return queueList.remove(0);
	}

	public Object head() {
		queueExtra.head();
		return queueList.get(0);
	}

	public int size() {
		return this.queueList.size();
	}
}
