/*
 * Class which creates a doubly linked list to store each location and its priority in the queue
 */
public class DLinkedNode<T> {
	private T dataItem;
	private double priority;
	private DLinkedNode<T> next;
	private DLinkedNode<T> prev;

	public DLinkedNode(T data, double prio) {
		dataItem = data;
		priority = prio;
	}

	public DLinkedNode() {
		dataItem = null;
		priority = 0;
	}

	public double getPriority() {
		return priority;
	}

	public T getDataItem() {
		return dataItem;
	}

	public DLinkedNode<T> getNext() {
		return next;
	}

	public DLinkedNode<T> getPrev() {
		return prev;
	}

	public void setDataItem(T data) {
		dataItem = data;
	}

	public void setNext(DLinkedNode<T> node) {
		next = node;
	}

	public void setPrev(DLinkedNode<T> node) {
		prev = node;
	}

	public void setPriority(double prio) {
		priority = prio;
	}

}
