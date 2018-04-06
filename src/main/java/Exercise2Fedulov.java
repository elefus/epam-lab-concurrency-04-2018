import lombok.SneakyThrows;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Exercise2Fedulov {
    static class Storage {
        String  sharedString = null;
        boolean writing      = false;

        public boolean isReading() {
            return reading;
        }

        public void setReading(boolean reading) {
            this.reading = reading;
        }

        boolean reading = false;

        public boolean isWriting() {
            return writing;
        }

        public void setWriting(boolean writing) {
            this.writing = writing;
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

        Thread[] threads = new Thread[4];

        Writer writer      = new Writer(storage);
        Reader readerOne   = new Reader(storage);
        Reader readerTwo   = new Reader(storage);
        Reader readerThree = new Reader(storage);

        threads[0] = new Thread(writer);
        threads[1] = new Thread(readerOne);
        threads[2] = new Thread(readerTwo);
        threads[3] = new Thread(readerThree);

        threads[0].setPriority(Thread.MAX_PRIORITY);
        threads[1].setPriority(Thread.MIN_PRIORITY);
        threads[2].setPriority(Thread.MIN_PRIORITY);
        threads[3].setPriority(Thread.MIN_PRIORITY);

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
            while (storageWriters.isReading()) {
                System.out.println("\n...writer wait for writing\n");
                TimeUnit.MILLISECONDS.sleep(3000);
            }
            lock.lock();
            {
                storageWriters.setWriting(true);
                storageWriters.setSharedString(String.valueOf(new Date()));
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.println("\nwriter wrote string = " + storageWriters.getSharedString() + "\n");
            }
            storageWriters.setWriting(false);
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
            while (storage.isWriting()) {
                System.out.println("\nreader wait for read... \n");
                TimeUnit.MILLISECONDS.sleep(500);
            }
            reentrantLock.lock();
            {
                storage.setReading(true);
                readstring = storage.getSharedString();
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.println("\nreader read th string = " + readstring + "\n");
            }
            storage.setReading(false);
            reentrantLock.unlock();
        }
    }
}