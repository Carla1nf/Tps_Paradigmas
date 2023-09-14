package queue;

public class EmptyQueue extends Queue {
	
	private static String QUEUE_IS_EMPTY = "Queue is empty";
	
	public Object take() {
		throw new RuntimeException(QUEUE_IS_EMPTY);
	}
	
	public Object head() {
		throw new RuntimeException(QUEUE_IS_EMPTY);
	}
	
}
