package lesson_02_04_2018;

import java.util.concurrent.TimeUnit;

public class Example1 {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println("Default handler");
        });
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();

        MyThreadGourp myGroup = new MyThreadGourp(mainThreadGroup, "MyGroup");
        Runnable sayHello = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + ": hello!");
                throw new RuntimeException("Runtime");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread1 = new Thread(sayHello);
        Thread thread2 = new Thread(myGroup, sayHello);
        Thread thread3 = new Thread(myGroup, sayHello);
        thread3.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("Exception in thread 3");
        });

        System.out.println(myGroup.activeCount());

        thread1.start();
        thread2.start();
        thread3.start();

        System.out.println(myGroup.activeCount());
    }

    private static class MyThreadGourp extends ThreadGroup {

        public MyThreadGourp(ThreadGroup parent, String name) {
            super(parent, name);
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("Exception in my thread group: " + e);
        }
    }
}
