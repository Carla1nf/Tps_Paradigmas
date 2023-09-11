package queue;

public class EmptyQueue extends Queue {
	private static String QUEUE_IS_EMPTY = "Queue is empty";
	
	@Override
	public Queue add (Object cargo) {
		return new LoadedQueue().add(cargo);
	}
	@Override
	public Object take() {
		throw new Error(QUEUE_IS_EMPTY);
	}
	@Override
	public Object head() {
		throw new Error(QUEUE_IS_EMPTY);
	}
	
}
