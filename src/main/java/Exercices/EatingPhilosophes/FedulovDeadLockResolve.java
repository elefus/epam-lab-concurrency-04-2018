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
        while (counter < 1_000) {
            for (int i = 0; i < fivePhilosophes.length; i++) {
                fivePhilosophes[i].start();
            }
//            System.out.println(firstAndSecondsStick);
//            System.out.println(firstAndFithsStick);
//            System.out.println(firdsAndSecondsStick);
//            System.out.println(forthsAndFirdsStick);
//            System.out.println(fithsAndForthsStick);
        }
    }


    // services
    private static void firstPhilosopherTakeHisSticks() {

        int random = (int) (Math.random() + 1) * 1_000;
        int localCounter = 0;
        while (localCounter <= random) {
            localCounter++;
            Object locker = new Object();
            synchronized (locker) {
                if (firstAndSecondsStick.isTaken() == false) {
                    firstAndSecondsStick.setTaken(true);

                    firstAndSecondsStick.setOwner("firstAndSecondsStick taken by fird Philosoph");
                    System.out.println(firstAndSecondsStick.getName() + " - " + firstAndSecondsStick.getOwner());
                    firstAndSecondsStick.setTaken(false);
                }
            }

            synchronized (locker) {
                if (firstAndFithsStick.isTaken() == false) {
                    firstAndFithsStick.setTaken(true);

                    firstAndFithsStick.setOwner("firstAndFithsStick taken by fird Philosoph");
                    System.out.println(firstAndSecondsStick.getName() + " - " + firstAndSecondsStick.getOwner());
                    firstAndFithsStick.setTaken(false);

                }
            }
            System.out.println("First Philosopher done !");
        }
    }

    private static void secondPhilosopherTakeHisSticks() {
        int random = (int) (Math.random() + 1) * 1_000;
        int localCounter = 0;
        while (localCounter <= random) {
            localCounter++;
            Object locker = new Object();
            synchronized (locker) {
                if (firstAndSecondsStick.isTaken() == false) {
                    firstAndSecondsStick.setTaken(true);

                    firstAndSecondsStick.setOwner("firstAndSecondsStick taken by fird Philosoph");
                    System.out.println(firstAndSecondsStick.getName() + " - " + firstAndSecondsStick.getOwner());
                    firstAndSecondsStick.setTaken(false);
                }
            }

            synchronized (locker) {
                if (firdsAndSecondsStick.isTaken() == false) {
                    firdsAndSecondsStick.setTaken(true);

                    firdsAndSecondsStick.setOwner("firdsAndSecondsStick taken by fird Philosoph");
                    System.out.println(firdsAndSecondsStick.getName() + " - " + firdsAndSecondsStick.getOwner());
                    firdsAndSecondsStick.setTaken(false);

                }
            }
            System.out.println("Second Philosopher done !");
        }
    }

    private static void firdPhilosopherTakeHisSticks() {
        int random = (int) (Math.random() + 1) * 1_000;
        int localCounter = 0;
        while (localCounter <= random) {
            localCounter++;

            Object locker = new Object();
            synchronized (locker) {
                if (firdsAndSecondsStick.isTaken() == false) {
                    firdsAndSecondsStick.setTaken(true);

                    firdsAndSecondsStick.setOwner("firdsAndSecondsStick taken by fird Philosoph");
                    System.out.println(firdsAndSecondsStick.getName() + " - " + firdsAndSecondsStick.getOwner());
                    firdsAndSecondsStick.setTaken(false);
                }
            }

            synchronized (locker) {
                if (forthsAndFirdsStick.isTaken() == false) {
                    forthsAndFirdsStick.setTaken(true);

                    forthsAndFirdsStick.setOwner("forthsAndFirdsStick taken by fird Philosoph");
                    System.out.println(forthsAndFirdsStick.getName() + " - " + forthsAndFirdsStick.getOwner());
                    forthsAndFirdsStick.setTaken(false);

                }
            }
            System.out.println("Fird Philosopher done !");
        }
    }

    private static void forthPhilosopherTakeHisSticks() {
        int random = (int) (Math.random() + 1) * 1_000;
        int localCounter = 0;
        while (localCounter <= random) {
            localCounter++;

            Object locker = new Object();
            synchronized (locker) {
                if (forthsAndFirdsStick.isTaken() == false) {
                    forthsAndFirdsStick.setTaken(true);

                    forthsAndFirdsStick.setOwner("forthsAndFirdsStick taken by fird Philosoph");
                    System.out.println(forthsAndFirdsStick.getName() + " - " + forthsAndFirdsStick.getOwner());
                    forthsAndFirdsStick.setTaken(false);
                }
            }

            synchronized (locker) {
                if (fithsAndForthsStick.isTaken() == false) {
                    fithsAndForthsStick.setTaken(true);

                    fithsAndForthsStick.setOwner("fithsAndForthsStick taken by fird Philosoph");
                    System.out.println(fithsAndForthsStick.getName() + " - " + fithsAndForthsStick.getOwner());
                    fithsAndForthsStick.setTaken(false);
                }
            }
            System.out.println("Second Philosopher done !");
        }
    }

    private static void fifthPhilosopherTakeHisSticks() {
        int random = (int) (Math.random() + 1) * 1_000;
        int localCounter = 0;
        while (localCounter <= random) {
            localCounter++;
            Object locker = new Object();
            synchronized (locker) {
                if (fithsAndForthsStick.isTaken() == false) {
                    fithsAndForthsStick.setTaken(true);

                    fithsAndForthsStick.setOwner("fithsAndForthsStick taken by fird Philosoph");
                    System.out.println(fithsAndForthsStick.getName() + " - " + fithsAndForthsStick.getOwner());
                    fithsAndForthsStick.setTaken(false);
                }
            }

            synchronized (locker) {
                if (firstAndFithsStick.isTaken() == false) {
                    firstAndFithsStick.setTaken(true);

                    firstAndFithsStick.setOwner("firstAndFithsStick taken by fird Philosoph");
                    System.out.println(firstAndSecondsStick.getName() + " - " + firstAndSecondsStick.getOwner());
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
}
