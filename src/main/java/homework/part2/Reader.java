package homework.part2;

import java.util.concurrent.TimeUnit;

public class Reader extends Thread{
    private final Storage storage;

    public Reader(Storage storage, String name) {
        super(name);
        this.storage = storage;
    }

    private void read() throws InterruptedException {
        synchronized (storage) {
            if (storage.readerIsHere) storage.wait();
            System.out.println(Thread.currentThread().getName() + " in the storage");
            TimeUnit.SECONDS.sleep(1);
            System.out.println(storage.getString());
            if(!storage.readerIsHere) storage.notify();
        }

    }

    public void run(){
        while(true){
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                read();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
