package torea;

/**
 * @author Torstein Strømme
 */
public interface IQueue<E> {

    /**
     * Tests if the queue is empty.
     * @return true if empty, false otherwise
     */
    public boolean empty();

    /**
     * Remove and return the first element of the queue.
     * @return top element
     */
    public E dequeue();

    /**
     * Return the first element of the queue without removing it.
     * @return top element
     */
    public E peek();

    /**
     * Add an element to the queue
     * @param item
     */
    public void enqueue(E item);
}