package safeMartin.control;

import safeMartin.display.RoadCanvas;

public class UnsafeMartin {
    public enum Sensors{sensor1,sensor2,sensor3,sensor4}

    private boolean lightState;
    private int enemies;
    private RoadCanvas display;

    private synchronized void switchLight(boolean on) {
        lightState = on;
        if (on)
            display.lightOn();
        else 
            display.lightOff();
    }

    public synchronized void enter(Sensors s) {
        switch(s) {
            case sensor2:
                break;
            case sensor3:
                break;
        }

    }

    public synchronized void exit(Sensors s) {
        switch(s) {
            case sensor1:
                break;
            case sensor4:
                break;
        }

    }


}
