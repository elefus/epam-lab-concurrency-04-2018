package Exercices.EatingPhilosophes;

public class FedulovDeadLockResolve {

    // not shared resource
    String spagetti = "NoOnesSpagetti";
    public static Thread[] fivePhilosophes = new Thread[5];

    // shared resorces
    public static volatile Stick firstAndSecondsStick = new Stick("firstAndSecondsStick");
    public static volatile Stick firstAndFithsStick = new Stick("firstAndSecondsStick");
    public static volatile Stick firdsAndSecondsStick = new Stick("firstAndSecondsStick");
    public static volatile Stick forthsAndFirdsStick = new Stick("firstAndSecondsStick");
    public static volatile Stick fithsAndForthsStick = new Stick("firstAndSecondsStick");

    // players
    public static final Runnable firstPhilosoph = new Runnable() {
        @Override
        public void run() {
            firstPhilosopherTakeHisSticks();
        }
    };
    public static final Runnable secondPhilosoph = new Runnable() {
        @Override
        public void run() {
            secondPhilosopherTakeHisSticks();
        }
    };
    public static final Runnable firdPhilosoph = new Runnable() {
        @Override
        public void run() {
            firdPhilosopherTakeHisSticks();
        }
    };
    public static final Runnable forthPhilosoph = new Runnable() {
        @Override
        public void run() {
            forthPhilosopherTakeHisSticks();
        }
    };
    public static final Runnable fithPhilosoph = new Runnable() {
        @Override
        public void run() {
            fifthPhilosopherTakeHisSticks();
        }
    };

    // starters
    public static final Thread firstStarter = new Thread(firstPhilosoph);
    public static final Thread secondStarter = new Thread(secondPhilosoph);
    public static final Thread firdStarter = new Thread(firdPhilosoph);
    public static final Thread forthStarter = new Thread(forthPhilosoph);
    public static final Thread fithStarter = new Thread(fithPhilosoph);

    public static final int random = (int) (Math.random() + 1) * 1_00;

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
    private static void firstPhilosopherTakeHisSticks() {

        int localCounter = 0;
        while (localCounter <= random) {
            localCounter++;
            Object locker = new Object();
            synchronized (locker) {
                if (firstAndSecondsStick.isTaken() == false) {
                    firstAndSecondsStick.setTaken(true);

                    firstAndSecondsStick.setOwner("firstAndSecondsStick taken by first Philosopher");
                    allSticksStates();
                    firstAndSecondsStick.setTaken(false);
                }
            }

            synchronized (locker) {
                if (firstAndFithsStick.isTaken() == false) {
                    firstAndFithsStick.setTaken(true);

                    firstAndFithsStick.setOwner("firstAndFithsStick taken by first Philosopher");
                    allSticksStates();
                    firstAndFithsStick.setTaken(false);

                }
            }
            System.out.println("First Philosopher done !");
        }
    }

    private static void secondPhilosopherTakeHisSticks() {
        int localCounter = 0;
        while (localCounter <= random) {
            localCounter++;
            Object locker = new Object();
            synchronized (locker) {
                if (firstAndSecondsStick.isTaken() == false) {
                    firstAndSecondsStick.setTaken(true);

                    firstAndSecondsStick.setOwner("firstAndSecondsStick taken by second Philosopher");
                    allSticksStates();
                    firstAndSecondsStick.setTaken(false);
                }
            }

            synchronized (locker) {
                if (firdsAndSecondsStick.isTaken() == false) {
                    firdsAndSecondsStick.setTaken(true);

                    firdsAndSecondsStick.setOwner("firdsAndSecondsStick taken by second Philosopher");
                    allSticksStates();
                    firdsAndSecondsStick.setTaken(false);

                }
            }
            System.out.println("Second Philosopher done !");
        }
    }

    private static void firdPhilosopherTakeHisSticks() {
        int localCounter = 0;
        while (localCounter <= random) {
            localCounter++;

            Object locker = new Object();
            synchronized (locker) {
                if (firdsAndSecondsStick.isTaken() == false) {
                    firdsAndSecondsStick.setTaken(true);

                    firdsAndSecondsStick.setOwner("firdsAndSecondsStick taken by fird Philosopher");
                    allSticksStates();
                    firdsAndSecondsStick.setTaken(false);
                }
            }

            synchronized (locker) {
                if (forthsAndFirdsStick.isTaken() == false) {
                    forthsAndFirdsStick.setTaken(true);

                    forthsAndFirdsStick.setOwner("forthsAndFirdsStick taken by fird Philosopher");
                    allSticksStates();
                    forthsAndFirdsStick.setTaken(false);

                }
            }
            System.out.println("Fird Philosopher done !");
        }
    }

    private static void forthPhilosopherTakeHisSticks() {
        int localCounter = 0;
        while (localCounter <= random) {
            localCounter++;

            Object locker = new Object();
            synchronized (locker) {
                if (forthsAndFirdsStick.isTaken() == false) {
                    forthsAndFirdsStick.setTaken(true);

                    forthsAndFirdsStick.setOwner("forthsAndFirdsStick taken by forth Philosopher");
                    allSticksStates();
                    forthsAndFirdsStick.setTaken(false);
                }
            }

            synchronized (locker) {
                if (fithsAndForthsStick.isTaken() == false) {
                    fithsAndForthsStick.setTaken(true);

                    fithsAndForthsStick.setOwner("fithsAndForthsStick taken by forth Philosopher");
                    allSticksStates();
                    fithsAndForthsStick.setTaken(false);
                }
            }
            System.out.println("Forth Philosopher done !");
        }
    }

    private static void fifthPhilosopherTakeHisSticks() {
        int localCounter = 0;
        while (localCounter <= random) {
            localCounter++;
            Object locker = new Object();
            synchronized (locker) {
                if (fithsAndForthsStick.isTaken() == false) {
                    fithsAndForthsStick.setTaken(true);

                    fithsAndForthsStick.setOwner("fifthsAndForthsStick taken by fifth Philosopher");
                    allSticksStates();
                    fithsAndForthsStick.setTaken(false);
                }
            }

            synchronized (locker) {
                if (firstAndFithsStick.isTaken() == false) {
                    firstAndFithsStick.setTaken(true);

                    firstAndFithsStick.setOwner("firstAndFithsStick taken by fifth Philosopher");
                    allSticksStates();
                    firstAndFithsStick.setTaken(false);
                }
            }
            System.out.println("Fifth Philosopher done !");
        }
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

    public static synchronized void allSticksStates() {
        System.out.println(firstAndSecondsStick.getName() + ", " + firstAndSecondsStick.getOwner() + ", " +
                           firstAndSecondsStick.isTaken() + "\n" + firdsAndSecondsStick.getName() + ", " +
                           firdsAndSecondsStick.getOwner() + ", " + firdsAndSecondsStick.isTaken() + "\n" +
                           forthsAndFirdsStick.getName() + ", " + forthsAndFirdsStick.getOwner() +
                           forthsAndFirdsStick.isTaken() + "\n" + fithsAndForthsStick.getName() + ", " +
                           fithsAndForthsStick.getOwner() + ", " + fithsAndForthsStick.isTaken() + "\n" +
                           firstAndFithsStick.getName() + ", " + firstAndFithsStick.getOwner() + ", " +
                           firstAndFithsStick.isTaken() + "\n\n------------------------------------------\n");
    }
}
