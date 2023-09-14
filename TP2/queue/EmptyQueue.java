package queue;

public class EmptyQueue extends QueueCommander {
	
	private static String QUEUE_IS_EMPTY = "Queue is empty";
	
	public Object take() {
		throw new Error(QUEUE_IS_EMPTY);
	}
	
	public Object head() {
		throw new Error(QUEUE_IS_EMPTY);
	}
	
}
