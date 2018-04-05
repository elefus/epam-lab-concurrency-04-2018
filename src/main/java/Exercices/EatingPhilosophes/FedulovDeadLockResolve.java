package Exercices.EatingPhilosophes;

public class FedulovDeadLockResolve {

    // not shared resource
    public static Thread[] fivePhilosophes = new Thread[5];

    // shared resorces
    public static volatile Stick firstAndSecondsStick = new Stick("firstAndSecondsStick");
    public static volatile Stick firstAndFithsStick = new Stick("firstAndSecondsStick");
    public static volatile Stick thirdsAndSecondsStick = new Stick("firstAndSecondsStick");
    public static volatile Stick forthsAndFirdsStick = new Stick("firstAndSecondsStick");
    public static volatile Stick fithsAndForthsStick = new Stick("firstAndSecondsStick");

    // players
    public static final Runnable firstPhilosoph = new Runnable() {
        @Override
        public void run() {
            try {
                firstPhilosopherTakeHisSticks();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    public static final Runnable secondPhilosoph = new Runnable() {
        @Override
        public void run() {
            try {
                secondPhilosopherTakeHisSticks();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    public static final Runnable firdPhilosoph = new Runnable() {
        @Override
        public void run() {
            try {
                firdPhilosopherTakeHisSticks();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    public static final Runnable forthPhilosoph = new Runnable() {
        @Override
        public void run() {
            try {
                forthPhilosopherTakeHisSticks();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    public static final Runnable fithPhilosoph = new Runnable() {
        @Override
        public void run() {
            try {
                fifthPhilosopherTakeHisSticks();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    // starters
    public static final Thread firstStarter = new Thread(firstPhilosoph);
    public static final Thread secondStarter = new Thread(secondPhilosoph);
    public static final Thread firdStarter = new Thread(firdPhilosoph);
    public static final Thread forthStarter = new Thread(forthPhilosoph);
    public static final Thread fithStarter = new Thread(fithPhilosoph);

    public static final int countOfAttemptsToEat = (int) (Math.random() + 1) * 10;

    public static void main(String[] args) {
        fivePhilosophes[0] = firstStarter;
        fivePhilosophes[1] = secondStarter;
        fivePhilosophes[2] = firdStarter;
        fivePhilosophes[3] = forthStarter;
        fivePhilosophes[4] = fithStarter;

        runTheGame();

    }

    public static void runTheGame() {
        long counter = 0;
        for (int i = 0; i < fivePhilosophes.length; i++) {
            fivePhilosophes[i].start();
        }
    }


    // services
    private static void firstPhilosopherTakeHisSticks() throws InterruptedException {

        int localCounter = 0;
        while (localCounter <= countOfAttemptsToEat) {
            localCounter++;
            Object locker = new Object();
            if (firstAndSecondsStick.isTaken() == false && fithsAndForthsStick.isTaken() == false) {
                synchronized (locker) {
                    {
                        firstAndSecondsStick.setTaken(true);
                        firstAndSecondsStick.setOwner("firstAndSecondsStick taken by second Philosopher");

                        firstAndFithsStick.setTaken(true);
                        firstAndFithsStick.setOwner("firstAndFithsStick taken by second Philosopher");

                        allSticksStates();
                        eatingSomeWhile();

                        firstAndSecondsStick.setOwner("NoBodysStick");
                        firstAndSecondsStick.setTaken(false);
                        fithsAndForthsStick.setOwner("NoBodysStick");
                        fithsAndForthsStick.setTaken(false);

                    }
                }
            } else {
                if (firstAndSecondsStick.isTaken()) {
                    firstAndSecondsStick.wait(10);
                }
                if (fithsAndForthsStick.isTaken()) {
                    fithsAndForthsStick.wait(10);
                } else if (firstAndSecondsStick.isTaken() && fithsAndForthsStick.isTaken()) {
                    thirdsAndSecondsStick.wait(10);
                }
            }
        }
        System.out.println("\n\nFirst Philosopher done !\n\n");
    }

    private static void secondPhilosopherTakeHisSticks() throws InterruptedException {
        int localCounter = 0;
        while (localCounter <= countOfAttemptsToEat) {
            localCounter++;
            Object locker = new Object();
            if (firstAndSecondsStick.isTaken() == false && thirdsAndSecondsStick.isTaken() == false) {
                synchronized (locker) {

                    firstAndSecondsStick.setTaken(true);
                    firstAndSecondsStick.setOwner("firstAndSecondsStick taken by second Philosopher");

                    thirdsAndSecondsStick.setTaken(true);
                    thirdsAndSecondsStick.setOwner("firstAndFithsStick taken by second Philosopher");

                    allSticksStates();
                    eatingSomeWhile();

                    firstAndSecondsStick.setOwner("NoBodysStick");
                    firstAndSecondsStick.setTaken(false);
                    thirdsAndSecondsStick.setOwner("NoBodysStick");
                    thirdsAndSecondsStick.setTaken(false);

                }
            } else {
                if (firstAndSecondsStick.isTaken()) {
                    firstAndSecondsStick.wait(10);
                } else if (thirdsAndSecondsStick.isTaken()) {
                    fithsAndForthsStick.wait(10);
                } else if (firstAndSecondsStick.isTaken() && thirdsAndSecondsStick.isTaken()) {
                    thirdsAndSecondsStick.wait(10);
                }
            }
        }
        System.out.println("\n\nSecond Philosopher done !\n\n");
    }

    private static void firdPhilosopherTakeHisSticks() throws InterruptedException {
        int localCounter = 0;
        while (localCounter <= countOfAttemptsToEat) {
            localCounter++;

            Object locker = new Object();
            if (forthsAndFirdsStick.isTaken() == false && thirdsAndSecondsStick.isTaken() == false) {
                synchronized (locker) {

                    forthsAndFirdsStick.setTaken(true);
                    forthsAndFirdsStick.setOwner("firstAndSecondsStick taken by second Philosopher");

                    thirdsAndSecondsStick.setTaken(true);
                    thirdsAndSecondsStick.setOwner("firstAndFithsStick taken by second Philosopher");

                    allSticksStates();
                    eatingSomeWhile();

                    forthsAndFirdsStick.setOwner("NoBodysStick");
                    forthsAndFirdsStick.setTaken(false);
                    thirdsAndSecondsStick.setOwner("NoBodysStick");
                    thirdsAndSecondsStick.setTaken(false);

                }
            } else {
                if (forthsAndFirdsStick.isTaken()) {
                    forthsAndFirdsStick.wait(10);
                } else if (thirdsAndSecondsStick.isTaken()) {
                    fithsAndForthsStick.wait(10);
                } else if (forthsAndFirdsStick.isTaken() && thirdsAndSecondsStick.isTaken()) {
                    thirdsAndSecondsStick.wait(10);
                }
            }
        }
        System.out.println("\n\nThird Philosopher done !\n\n");
    }

    private static void forthPhilosopherTakeHisSticks() throws InterruptedException {
        int localCounter = 0;
        while (localCounter <= countOfAttemptsToEat) {
            localCounter++;

            Object locker = new Object();
            if (forthsAndFirdsStick.isTaken() == false && fithsAndForthsStick.isTaken() == false) {
                synchronized (locker) {

                    forthsAndFirdsStick.setTaken(true);
                    forthsAndFirdsStick.setOwner("firstAndSecondsStick taken by second Philosopher");

                    fithsAndForthsStick.setTaken(true);
                    fithsAndForthsStick.setOwner("firstAndFithsStick taken by second Philosopher");

                    allSticksStates();
                    eatingSomeWhile();

                    forthsAndFirdsStick.setOwner("NoBodysStick");
                    forthsAndFirdsStick.setTaken(false);
                    fithsAndForthsStick.setOwner("NoBodysStick");
                    fithsAndForthsStick.setTaken(false);

                }
            } else {
                if (forthsAndFirdsStick.isTaken()) {
                    forthsAndFirdsStick.wait(10);
                } else if (fithsAndForthsStick.isTaken()) {
                    fithsAndForthsStick.wait(10);
                } else if (forthsAndFirdsStick.isTaken() && fithsAndForthsStick.isTaken()) {
                    fithsAndForthsStick.wait(10);
                }
            }
        }
        System.out.println("\n\nForth Philosopher done !\n\n");
    }

    private static void fifthPhilosopherTakeHisSticks() throws InterruptedException {
        int localCounter = 0;
        while (localCounter <= countOfAttemptsToEat) {
            localCounter++;
            Object locker = new Object();
            if (firstAndFithsStick.isTaken() == false && fithsAndForthsStick.isTaken() == false) {
                synchronized (locker) {

                    firstAndFithsStick.setTaken(true);
                    firstAndFithsStick.setOwner("firstAndSecondsStick taken by second Philosopher");

                    fithsAndForthsStick.setTaken(true);
                    fithsAndForthsStick.setOwner("firstAndFithsStick taken by second Philosopher");

                    allSticksStates();
                    eatingSomeWhile();

                    firstAndFithsStick.setOwner("NoBodysStick");
                    firstAndFithsStick.setTaken(false);
                    fithsAndForthsStick.setOwner("NoBodysStick");
                    fithsAndForthsStick.setTaken(false);
                }
            } else {
                if (firstAndFithsStick.isTaken()) {
                    firstAndFithsStick.wait(10);
                } else if (fithsAndForthsStick.isTaken()) {
                    fithsAndForthsStick.wait(10);
                } else if (firstAndFithsStick.isTaken() && fithsAndForthsStick.isTaken()) {
                    fithsAndForthsStick.wait(10);
                }
            }
        }
        System.out.println("\n\nFifth Philosopher done !\n\n");
    }

    private static class Stick {

        public Stick(String name) {
            this.name = name;
        }

        String name = "noBodysStick";

        String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        boolean isTaken() {
            return taken;
        }

        public void setTaken(boolean taken) {
            this.taken = taken;
        }

        String owner = "noBodysStick";

        boolean taken = false;
    }

    private static void eatingSomeWhile() throws InterruptedException {
        Thread.currentThread().sleep(500);
    }

    public static void allSticksStates() {
        System.out.println(firstAndSecondsStick.getName() + ", " + firstAndSecondsStick.getOwner() + ", " +
                           firstAndSecondsStick.isTaken() + "\n" + thirdsAndSecondsStick.getName() + ", " +
                           thirdsAndSecondsStick.getOwner() + ", " + thirdsAndSecondsStick.isTaken() + "\n" +
                           forthsAndFirdsStick.getName() + ", " + forthsAndFirdsStick.getOwner() +
                           forthsAndFirdsStick.isTaken() + "\n" + fithsAndForthsStick.getName() + ", " +
                           fithsAndForthsStick.getOwner() + ", " + fithsAndForthsStick.isTaken() + "\n" +
                           firstAndFithsStick.getName() + ", " + firstAndFithsStick.getOwner() + ", " +
                           firstAndFithsStick.isTaken() + "\n\n------------------------------------------\n");
    }
}
