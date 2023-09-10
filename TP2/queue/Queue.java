package queue;

import java.util.ArrayList;

public class Queue {
	
	public static String QUEUE_IS_EMPTY = "Queue is empty";
	public ArrayList<Object> queueList = new ArrayList<Object>();

    public boolean isEmpty() {
		return queueList.isEmpty();
	}

	public Queue add( Object  cargo ) {
		queueList.add(cargo);
		return this;
	}
	
	public Object take() {
		if (this.queueList.isEmpty()){
			throw new Error(QUEUE_IS_EMPTY);
		}
		return this.queueList.remove(0);
	}

	public Object head() {
		if (this.queueList.isEmpty()){
			throw new Error(QUEUE_IS_EMPTY);
		}
		else{
			return this.queueList.get(0);
		}
	}

	public int size() {
		return this.queueList.size();
	}
}
