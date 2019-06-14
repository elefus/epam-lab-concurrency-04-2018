package lesson_05_04_2018.philosophers.another;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Stick stick1 = new Stick();
        Stick stick2 = new Stick();
        Stick stick3 = new Stick();
        Stick stick4 = new Stick();
        Stick stick5 = new Stick();

        List<Stick> sticks = Arrays.asList(stick1, stick2, stick3, stick4, stick5);

        Waiter waiter = new Waiter(sticks);

        Philosopher philosopher1 = new Philosopher(waiter);
        Philosopher philosopher2 = new Philosopher(waiter);
        Philosopher philosopher3 = new Philosopher(waiter);
        Philosopher philosopher4 = new Philosopher(waiter);
        Philosopher philosopher5 = new Philosopher(waiter);


//        Philosopher philosopher1 = new Philosopher() {
//            @Override
//            public void eat() {
//                synchronized (stick1) {
//                    takeLeftStick(stick1);
//                    System.out.println("Philosopher 1 has taken 1 stick");
//                    try {
//                        TimeUnit.MILLISECONDS.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    synchronized (stick2) {
//                        takeRightStick(stick2);
//                        System.out.println("Philosopher 1 has taken 2 stick");
//                        try {
//                            TimeUnit.MILLISECONDS.sleep(100);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        releaseSticks();
//                    }
//                }
//                System.out.println("Philosopher 1 ended");
//            }
//        };
//
//        Philosopher philosopher2 = new Philosopher() {
//            @Override
//            public void eat() {
//                synchronized (stick2) {
//                    takeLeftStick(stick2);
//                    System.out.println("Philosopher 2 has taken 2 stick");
//                    try {
//                        TimeUnit.MILLISECONDS.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    synchronized (stick3) {
//                        takeRightStick(stick3);
//                        System.out.println("Philosopher 2 has taken 3 stick");
//                        try {
//                            TimeUnit.MILLISECONDS.sleep(100);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        releaseSticks();
//                    }
//                }
//                System.out.println("Philosopher 2 ended");
//            }
//        };
//
//        Philosopher philosopher3 = new Philosopher() {
//            @Override
//            public void eat() {
//                synchronized (stick3) {
//                    takeLeftStick(stick3);
//                    System.out.println("Philosopher 3 has taken 3 stick");
//                    try {
//                        TimeUnit.MILLISECONDS.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    synchronized (stick4) {
//                        takeRightStick(stick4);
//                        System.out.println("Philosopher 3 has taken 4 stick");
//                        try {
//                            TimeUnit.MILLISECONDS.sleep(100);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        releaseSticks();
//                    }
//                }
//                System.out.println("Philosopher 3 ended");
//            }
//        };
//
//        Philosopher philosopher4 = new Philosopher() {
//            @Override
//            public void eat() {
//                synchronized (stick4) {
//                    takeLeftStick(stick4);
//                    System.out.println("Philosopher 4 has taken 4 stick");
//                    try {
//                        TimeUnit.MILLISECONDS.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    synchronized (stick5) {
//                        takeRightStick(stick5);
//                        System.out.println("Philosopher 4 has taken 5 stick");
//                        try {
//                            TimeUnit.MILLISECONDS.sleep(100);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        releaseSticks();
//                    }
//                }
//                System.out.println("Philosopher 4 ended");
//            }
//        };
//
//        Philosopher philosopher5 = new Philosopher() {
//            @Override
//            public void eat() {
//                synchronized (stick5) {
//                    takeLeftStick(stick5);
//                    System.out.println("Philosopher 5 has taken 5 stick");
//                    try {
//                        TimeUnit.MILLISECONDS.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    synchronized (stick1) {
//                        takeRightStick(stick1);
//                        System.out.println("Philosopher 5 has taken 1 stick");
//                        try {
//                            TimeUnit.MILLISECONDS.sleep(100);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        releaseSticks();
//                    }
//                }
//                System.out.println("Philosopher 5 ended");
//            }
//        };

        philosopher1.start();
        philosopher2.start();
        philosopher3.start();
        philosopher4.start();
        philosopher5.start();

        philosopher1.join();
        philosopher2.join();
        philosopher3.join();
        philosopher4.join();
        philosopher5.join();

        System.out.println("Main End!");
    }
}
