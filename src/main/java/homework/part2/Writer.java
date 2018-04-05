package homework.part2;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Writer extends Thread{
    private Random random = new Random();
    private final Storage storage;

    public Writer(Storage storage, String name) {
        super(name);
        this.storage = storage;
    }

    private void write() throws  InterruptedException{
        //generate random string
        char[] chars = new char[random.nextInt(100)];
        for(int i = 0; i< chars.length;i++) {
            chars[i] = (char)random.nextInt(1<<16 - 1);
        }

        storage.readerIsHere = true;
        synchronized (storage) {
            System.out.println(Thread.currentThread().getName() + " in the storage");
            TimeUnit.SECONDS.sleep(1);
            storage.setString(new String(chars));
            storage.notify();
        }
        storage.readerIsHere = false;
    }

    public void run(){
        while (true){
            try {
                write();
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
