package queue;

public class FullQueue extends Queue {
	public Queue add (Object cargo) {
		this.queueList.add(cargo);
		return this;
	}
	public Object take() {
		return queueList.remove(0);
	}
	public Object head() {
		return queueList.get(0);
	}
}
