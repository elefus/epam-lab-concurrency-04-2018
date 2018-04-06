import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

        Writer       writer  = new Writer(storage);
        List<Reader> readers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            readers.add(new Reader("reader # " + i, storage));
        }

        List<Thread> threads = new ArrayList<>(readers.size() + 1);

        for (int i = 0; i < threads.size(); i++) {
            threads.add(new Thread(readers.get(i)));
            threads.get(i).setPriority(Thread.MIN_PRIORITY);
        }
        Thread writerThread = new Thread(writer);
        writerThread.setPriority(Thread.MAX_PRIORITY);
        threads.add(writerThread);


        for (Thread t :
                threads) {
            t.start();
        }
    }
}

class Writer implements Runnable {
    //    ReentrantLock            lock = new ReentrantLock();
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
//            lock.lock();
            {
                storageWriters.setWriting(true);
                storageWriters.setSharedString(String.valueOf(new Date()));
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.println("\nwriter wrote string = " + storageWriters.getSharedString() + "\n");
            }
            storageWriters.setWriting(false);
//            lock.unlock();
        }
    }
}


class Reader implements Runnable {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Reader(String name, Exercise2Fedulov.Storage storage) {
        this.name = name;
        this.storage = storage;
    }

    Exercise2Fedulov.Storage storage;
    String                   readstring    = null;
//    ReentrantLock            reentrantLock = new ReentrantLock();

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
//            reentrantLock.lock();
            {
                storage.setReading(true);
                readstring = storage.getSharedString();
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.println("\nreader " + getName() + "read th string = " + readstring + "\n");
            }
            storage.setReading(false);
//            reentrantLock.unlock();
        }
    }
}