package queue;

public class LoadedQueue extends QueueCommander {
	
	private Object cargo;

	public LoadedQueue(Object cargo) {
		this.cargo = cargo;
	}
	
	public Object test() {
		return this.cargo;
	}
}
