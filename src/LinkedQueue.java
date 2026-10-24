//import java.util.List;
import java.util.NoSuchElementException;
//import java.util.ArrayList;
public class LinkedQueue<T> {

    private static class Node<E> {
        private E data;
        private Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

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
    
}
