import lombok.SneakyThrows;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Exercise2Fedulov {
    static class Storage {
        String  sharedString = null;
        boolean blocked      = false;

        public boolean isBlocked() {
            return blocked;
        }

        public void setBlocked(boolean blocked) {
            this.blocked = blocked;
        }

        public Storage(String sharedString) {
            this.sharedString = sharedString;
        }

        public String getSharedString() {
            return sharedString;
        }

        public void setSharedString(String sharedString) {
            this.sharedString = sharedString;
        }
    }

    public static void main(String[] args) {
        Storage storage = new Storage("\n------------------initial string-----------------\n");
        System.out.println(storage.getSharedString());

        Thread[] threads = new Thread[2];

        Writer writer = new Writer(storage);
        Reader reader = new Reader(storage);
        threads[0] = new Thread(writer);
        threads[1] = new Thread(reader);
        threads[0].setPriority(Thread.MAX_PRIORITY);
        threads[1].setPriority(Thread.MIN_PRIORITY);

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}

class Writer implements Runnable {
    ReentrantLock            lock = new ReentrantLock();
    Exercise2Fedulov.Storage storageWriters;

    Writer(Exercise2Fedulov.Storage storageWriters) {
        this.storageWriters = storageWriters;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            while (storageWriters.isBlocked()) {
                System.out.println("\n...writer wait for writing\n");
                TimeUnit.MILLISECONDS.sleep(3000);
            }
            lock.lock();
            {
                storageWriters.setSharedString(String.valueOf(new Date()));
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.println("\nwriter wrote string = " + storageWriters.getSharedString() + "\n");
            }
            lock.unlock();
        }
    }
}


class Reader implements Runnable {
    Exercise2Fedulov.Storage storage;
    String                   readstring    = null;
    ReentrantLock            reentrantLock = new ReentrantLock();

    public Reader(Exercise2Fedulov.Storage storage) {
        this.storage = storage;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            while (storage.isBlocked()) {
                System.out.println("\nreader wait for read... \n");
                TimeUnit.MILLISECONDS.sleep(500);
            }
            reentrantLock.lock();
            {
                readstring = storage.getSharedString();
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.println("\nreader read th string = " + readstring + "\n");
            }
        }
    }
}