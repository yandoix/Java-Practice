//import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;
//import java.util.ArrayList;

//import org.w3c.dom.Node;

public class LinkedQueue<T> implements Iterable<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;


    private static class Node<T> {
        private T data;
        private Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private class QueueIterator implements Iterator<T> {

        private Node<T> current = head;

        @Override 
        public boolean hasNext() {

            return current != null;
        }

        @Override 
        public T next() {

            if(!hasNext()) {
                throw new NoSuchElementException("There is nothing left in the Iterator!");
            }
            T returner = current.data;
        
            current = current.next;

            return returner; 

        }
    }

    public int size() {
        return size;
    }

    public void enqueue(T item) {

        Node<T> oldestNode = new Node<>(item);

        if (size==0) {
            head = oldestNode;
            tail = oldestNode;
        } else {
            
            tail.next = oldestNode;
            tail = oldestNode;
        }
        size++;
    }

    public T dequeue() {
        if (size<=0) {
            throw new NoSuchElementException("There is nothing left in the queue");
        }
        
        T removedData = head.data;

        head = head.next;
        size--;

        if (size == 0) {
        tail = null;
        }
        return removedData;
    }

    public T peek() {

         if (size <= 0) {
        throw new NoSuchElementException("Cannot peek into an empty queue");
        }

        return head.data;
    }

    public boolean isEmpty() {

        return size==0;
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    @Override
    public String toString() {
    // If the queue is empty, just return empty brackets
    if (size == 0) {
        return "[]";
    }

    StringBuilder sb = new StringBuilder();
    sb.append("[");

    // Get the iterator we built
    Iterator<T> it = this.iterator();

    // Loop through the data
    while (it.hasNext()) {
        sb.append(it.next());
        if (it.hasNext()) {
            sb.append(", "); // Add a comma between items
        }
    }

    sb.append("]");
    return sb.toString();
    }
}