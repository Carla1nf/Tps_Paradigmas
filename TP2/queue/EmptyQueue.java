package queue;

public class EmptyQueue extends Queue {
	public Queue add (Object cargo) {
		return new FullQueue().add(cargo);
	}
	public Object take() {
		throw new Error(QUEUE_IS_EMPTY);
	}
	public Object head() {
		throw new Error(QUEUE_IS_EMPTY);
	}
	
}
