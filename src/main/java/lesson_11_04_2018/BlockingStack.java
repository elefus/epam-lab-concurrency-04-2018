package lesson_11_04_2018;


// push
// pop
//
//
// new BlockingStack(5)
//
//
// -----
//
//
//
//
//
// ______


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingStack<T> {

    private final Object[] arr;
    private volatile int current = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public BlockingStack(int size) {
        arr = new Object[size];
    }

    public void push(T element) throws InterruptedException {

        lock.lock();
        while (current > arr.length - 1) notFull.await();
        try {

            System.out.printf("trying push... %s/%s\n", current, arr.length-1);
            arr[current++] = element;
        } finally {
            notEmpty.signal();
            lock.unlock();
        }
    }

    @SuppressWarnings("unchecked")
    public T pop() throws InterruptedException {
        lock.lock();
        while (current == 0) notEmpty.await();
        try {
            System.out.printf("trying pop... %s/%s\n", current - 1, arr.length-1 );
            return (T) arr[--current];
        } finally {
            notFull.signal();
            lock.unlock();
        }
    }

}

class TestBlockingStack {
    public static void main(String[] args) {
        BlockingStack<Integer> integerBlockingStack = new BlockingStack<>(5);

        ExecutorService service = Executors.newCachedThreadPool();

        service.submit(() -> {
            int count=0;

            while (true) {
                integerBlockingStack.push(count++);
            }


        });
        service.submit(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    integerBlockingStack.pop();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        service.shutdown();
    }

}
