package queue;

public class LoadedQueue extends QueueCommander {
	
	private Object cargo;

	public LoadedQueue (Object cargo) {
		this.cargo = cargo;
	}
	
	public Object take() {
		return this.cargo;
	}
	
	public Object head() {
		return this.cargo;
	}
}
