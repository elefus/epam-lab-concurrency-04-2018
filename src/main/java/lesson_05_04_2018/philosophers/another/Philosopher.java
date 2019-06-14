package lesson_05_04_2018.philosophers.another;

import java.util.concurrent.TimeUnit;

public class Philosopher extends Thread{
    protected Stick leftStick;
    protected Stick rightStick;
    private Waiter waiter;

    Philosopher(Waiter waiter) {
        super("Philosopher");
        this.waiter = waiter;
    }

    @Override
    public void run() {
        try {
            eat();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean takeLeftStick(Stick stick) {
        leftStick = stick;
        return stick != null;
    }

    public boolean takeRightStick(Stick stick) {
        rightStick = stick;
        return stick != null;
    }

    protected void releaseSticks() {
        leftStick = null;
        rightStick = null;
    }

    public void eat() throws InterruptedException {
        while (true) {
            if (takeLeftStick(waiter.getLeftStick()))
                break;
            else
                TimeUnit.MILLISECONDS.sleep(100);
        }
        System.out.println("Left stick is taken");
        TimeUnit.SECONDS.sleep(2);
        while (true) {
            if(takeRightStick(waiter.getRightStick())) {
                break;
            }
            else
                TimeUnit.SECONDS.sleep(2);
        }
        System.out.println("Right stick is taken");
        if(leftStick != null && rightStick != null) {
            waiter.releaseSticks();
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
