package lesson_03_04_2018;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Example3 {

    enum State {
        RUNNING,
        PAUSE,
        STOP;
    }

    private static volatile State state = State.RUNNING;
    private static volatile int val = 10;
    private static volatile StringBuilder builder = new StringBuilder();
    private volatile File file = null;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            int value = 0;
            loop: while (true) {
                switch (state) {
                    case RUNNING:
                        ++value;
                        System.out.println(value);
                        break;

                    case PAUSE:
                        System.out.println(State.PAUSE);
                        break;

                    case STOP:
                        System.out.println(State.STOP);
                        break loop;
                }
            }
            System.out.println("Counter end: " + value);
        }).start();

        TimeUnit.MILLISECONDS.sleep(500);

        state = State.PAUSE;

        TimeUnit.MILLISECONDS.sleep(500);

        state = State.STOP;
        System.out.println("Main end");
    }
}