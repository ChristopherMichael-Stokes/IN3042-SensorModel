package safeMartin.road;

import safeMartin.display.RoadCanvas;
import safeMartin.control.*;
import java.awt.*;
import java.applet.*;
import static java.awt.GridBagConstraints.*;

public class Road extends Applet {

    Checkbox safe;
    Button martinHouse, martinPath, martinRoad, enemyEnter, enemyExit;
    public enum Entity {Martin, Enemy}
    public enum MartinLocation {InHouse, OnPath, OnRoad}
    private boolean gateOpen, lightOn;
    private RoadCanvas road;

    @Override
    public void init() {
        gateOpen = true;
        lightOn = false;
        road = new RoadCanvas(getWidth(), getHeight());
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new Label("Enemy Actions:"),gbc);
        ++gbc.gridx;
        add(enemyEnter = new Button("Enter road"),gbc);
        ++gbc.gridx;
        add(enemyExit = new Button("Exit road"),gbc);

        gbc.gridx = 0;
        ++gbc.gridy;
        add(new Label("Martin Actions:"),gbc);
        ++gbc.gridx;
        add(martinHouse = new Button("Exit house"),gbc);
        ++gbc.gridx;
        add(martinPath = new Button("Enter road"),gbc);
        ++gbc.gridx;
        add(martinRoad = new Button("Leave road"),gbc);

        gbc.weightx = 0;
        gbc.fill = BOTH;
        gbc.gridwidth = 6;
        gbc.gridy = 0;
        gbc.gridx = 0;
        Panel environment = new Panel();
        environment.add(road);
        add(environment,gbc);
    }

    public void openGate(boolean open) throws RuntimeException {
        if (open && gateOpen || (!open && !gateOpen))
            throw new RuntimeException("gate cannot move");

        gateOpen = open;
        if (open)
            road.openGate();
        else
            road.closeGate();

    }

    public void switchLight(boolean on) throws RuntimeException {
        if (lightOn && on || (!lightOn && !on))
            throw new RuntimeException("light cannot turn on");

        lightOn = on;
        if (on) 
            road.lightOn();
        else 
            road.lightOff();
        
    }

    class Martin implements Runnable {
        RoadCanvas display;
        SafeMartin control;

        public Martin(RoadCanvas display){
            this.display = display;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    wait(); 

                } catch (InterruptedException e) {}

            } 
        }

     
    }

    class Enemy implements Runnable {
        RoadCanvas display; UnsafeMartin control;

        public Enemy(RoadCanvas display, Gate gate, UnsafeMartin control){
            this.display = display; this.control = control;
        }

        @Override
        public void run() {}


    }

}