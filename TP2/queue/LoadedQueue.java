package queue;

public class LoadedQueue extends Queue {
	
	public Queue add (Object cargo) {
		this.queueList.add(cargo);
		return this;
	}
	
	public Object take() {
		return null;
	}
	
	public Object head() {
		return null;
	}
}
