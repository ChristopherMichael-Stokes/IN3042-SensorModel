package safeMartin.control;

import safeMartin.display.RoadCanvas;

public class Gate implements Runnable {
    private enum State{UP, DOWN}
    private State state = State.DOWN;



    public void raise() {
        state = State.UP; 
        notifyAll();
    }
    public void lower() {
        state = State.DOWN; 
        notifyAll();
    }

    public boolean gateDown() {
        return state == State.DOWN ? true : false;
    }




    @Override
    public void run() {


    }
}

