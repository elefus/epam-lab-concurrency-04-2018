package lesson_05_04_2018.philosophers.another;

import java.util.ArrayList;
import java.util.List;

public class Waiter {
    private List<Stick> sticks;
    private List<Stick> busySticks;

    Waiter(List<Stick> sticks){
        this.sticks = sticks;
        busySticks = new ArrayList<>();
    }

    public synchronized Stick getLeftStick() {
        if (busySticks.size() < sticks.size()-1) {
            Stick toReturn = sticks.get(busySticks.size());
            busySticks.add(toReturn);
            return toReturn;
        }
        else
            return null;
    }

    public synchronized Stick getRightStick() {
        if (busySticks.size() < sticks.size()) {
            Stick toReturn = sticks.get(busySticks.size());
            busySticks.add(toReturn);
            return toReturn;
        }
        else
            return null;
    }

    public synchronized void releaseSticks() {
        busySticks.remove(busySticks.size()-1);
        busySticks.remove(busySticks.size()-1);
    }
}
