/*
 * The PriorityQueue class implements the PriorityQueueADT to create a queue of doubly-linked nodes. The queue keeps track of all spaces explored by the program.
 */
public class DLPriorityQueue<T> implements PriorityQueueADT<T> {
	private DLinkedNode<T> front;
	private DLinkedNode<T> rear;
	private int count;

	public DLPriorityQueue() {
		front = null;
		rear = null;
		count = 0;
	}

	/*
	 * Adds a new node to the list and increases the count of items in it
	 */
	public void add(T dataItem, double priority) {
		DLinkedNode<T> newNode = new DLinkedNode<T>(dataItem, priority);
		if (isEmpty()) {
			front = newNode;
			rear = newNode;
		} else {
			sort(newNode);
		}

		++count;

	}

	/*
	 * Changes the priority value of one of the nodes in the list. Throws an
	 * exception if the element does not exist in the list
	 */
	public void updatePriority(T dataItem, double newPriority) throws InvalidElementException {
		if (!isEmpty()) {
			DLinkedNode<T> curr = front;
			DLinkedNode<T> selectedNode = curr;
			boolean found = false;
			while (curr != null && found == false) {
				if ((curr.getDataItem()).equals(dataItem)) {
					selectedNode = curr;
					selectedNode.setPriority(newPriority);
					remove(curr);
					found = true;
				}
				curr = curr.getNext();
			}

			if (found == false) {
				throw new InvalidElementException("Data item not found in queue");
			} else {
				sort(selectedNode);
				++count;
			}

		} else {
			throw new InvalidElementException("Data item not found in queue");
		}

	}

	/*
	 * Removes the node with the lowest priority value from the queue (aka the value
	 * at the front of the queue)
	 */
	public T removeMin() throws EmptyPriorityQueueException {
		if (this.isEmpty() || front == null) {
			throw new EmptyPriorityQueueException("Empty queue");
		} else {
			T value = front.getDataItem();
			remove(front);
			return value;
		}
	}

	/*
	 * Checks if the queue is empty or not
	 */
	public boolean isEmpty() {
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Returns number of elements in the queue
	 */
	public int size() {
		return count;
	}

	/*
	 * Prints out the data values of each node
	 */
	@Override
	public String toString() {
		DLinkedNode<T> curr = front;
		String queue = "";
		while (curr != null) {
			queue += curr.getDataItem();
			curr = curr.getNext();
		}

		return queue;
	}

	/*
	 * Returns the node at the end of the queue
	 */
	public DLinkedNode<T> getRear() {
		return rear;
	}

	/*
	 * Sort and Remove are two helper methods I created to help me organize the
	 * queue efficiently: - Sort adds a new node to the list and places it at the
	 * correct position based on an order of increasing priority - Remove removes a
	 * certain node from the list. I called Sort in the Add method to make sure that
	 * the queue remained in an order of increasing values, and I called Remove in
	 * the UpdatePriority method to remove the node with the old priority value to
	 * replace it with the new node
	 */
	private void sort(DLinkedNode<T> node) {
		DLinkedNode<T> curr = front;
		boolean added = false;
		while (curr != null && added == false) {
			if (node.getPriority() < curr.getPriority()) {
				if (curr == front) {
					curr.setPrev(node);
					node.setNext(curr);
					node.setPrev(null);
					front = node;
				} else {
					DLinkedNode<T> prev = curr.getPrev();
					(curr.getPrev()).setNext(node);
					curr.setPrev(node);
					node.setNext(curr);
					node.setPrev(prev);
				}

				added = true;

			}

			curr = curr.getNext();

		}

		if (added == false && rear != null) {
			rear.setNext(node);
			node.setPrev(rear);
			node.setNext(null);
			rear = node;
		}

	}

	private void remove(DLinkedNode<T> node) {
		if (!isEmpty()) {
			DLinkedNode<T> curr = front;
			while (curr != node) {
				curr = curr.getNext();
			}

			if (count == 1) {
				front = null;
				rear = null;
			}

			else if (curr == front) {
				front = curr.getNext();
				front.setPrev(null);
			}

			else if (curr == rear) {
				rear = curr.getPrev();
				rear.setNext(null);
			}

			else {
				(curr.getNext()).setPrev(curr.getPrev());
				(curr.getPrev()).setNext(curr.getNext());
			}

			--count;

		}

	}

}
