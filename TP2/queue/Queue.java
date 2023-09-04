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
		if (this.queue.isEmpty()){
			return new RuntimeException( "Queue is empty" );
		}
		else{
			return this.queue.get(0);
		}
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
