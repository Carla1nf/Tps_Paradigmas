package queue;

import java.util.ArrayList;
import java.util.List;

public class Queue {
	
	public List<Object> queue = new ArrayList<>();

    public boolean isEmpty() {
		return queue.isEmpty();
	}

	public Queue add( Object  cargo ) {
		this.queue.add(cargo);
		return this;
	}
	
	public Object take() {
    // TODO Auto-generated method stub
		return null;
	}

	public Object head() {
		// TODO Auto-generated method stub
    return null;
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
