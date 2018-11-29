package safeMartin.control;

import safeMartin.control.sensor.*;
import safeMartin.display.RoadCanvas;

public class UnsafeMartin {
    public enum Sensors{sensor1,sensor2,sensor3,sensor4}

    private boolean lightState;
    private RoadCanvas display;
    public final Gate gate;
    private int roadCount;
    private boolean martinOnRoad, safe;

    

    public UnsafeMartin (RoadCanvas display) { 
        this.display = display;
        this.gate = new Gate(display);
        roadCount = 0;
        martinOnRoad = false;
    }

    //move sensors to seperate methods
    public synchronized void enter(Sensors s) {
        switch(s) {
            case sensor2:
                martinOnRoad = true;
            case sensor3:
                if (roadCount == 0) {
                    lightState = true;
                    display.lightOn();
                }

                ++roadCount;
                break;
        }

    }

    public synchronized void exit(Sensors s) {
        switch(s) {
            case sensor1:
                gate.lower();
                break;
            case sensor4:
                if (roadCount == 1) {
                    if (martinOnRoad) {
                        gate.raise();
                        martinOnRoad = false;
                    }
                    lightState = false;
                    display.lightOff();
                }

                --roadCount;
                break;
        }

    }

    public boolean lightOn() {
        return lightState;
    }

    public boolean safe() { return safe; }

    public synchronized void setSafe(boolean safe) {
        if (!safe && gate.gateDown()) 
            gate.raise();
        this.safe = safe;
    }


}
