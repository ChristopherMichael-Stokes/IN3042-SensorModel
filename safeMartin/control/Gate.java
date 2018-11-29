package safeMartin.control;

import safeMartin.display.RoadCanvas;

public class Gate {
    private enum State{UP, DOWN}
    private State state;
    private final RoadCanvas display;

    public Gate(RoadCanvas display) {
        state = state.UP;
        this.display = display;
    }


    public synchronized void raise() throws RuntimeException {
        if (state == State.UP)
            throw new RuntimeException("gate is already up");
        state = State.UP; 
        display.openGate();
    }

    public synchronized void lower() throws RuntimeException {
        if (state == State.DOWN)
            throw new RuntimeException("gate is already down");
        state = State.DOWN; 
        display.closeGate();
    }

    public synchronized void pass() throws InterruptedException {
        while (gateDown()) wait();


        notifyAll();
    }

    public boolean gateDown() {
        return state == State.DOWN ? true : false;
    }

}
