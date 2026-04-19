import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.FutureTask;

public class TaskQueue<T extends FutureTask<?>>   {

    private int head = 0;
    private int tail = 0;
    private int count = 0;
    private final T[] buffer;


    public TaskQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be > 0");
        }
        this.buffer = (T[]) new Object[capacity];
    }

    public synchronized void enqueue(T task) throws InterruptedException {
        while (count == buffer.length) {
            wait();
        }
        buffer[tail] = task;
        tail = (tail + 1) % buffer.length;
        count++;
        notifyAll();


    }

    public synchronized T dequeue() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        T task = buffer[head];
        buffer[head] = null;
        head = (head + 1) % buffer.length;
        count--;
        notifyAll();
        return task;
    }

    public synchronized int size() {
        return count;
    }

    }