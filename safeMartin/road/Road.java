package safeMartin.road;

import safeMartin.display.RoadCanvas;
import safeMartin.control.*;
import static safeMartin.control.UnsafeMartin.Sensors.*;
import java.awt.*;
import java.applet.*;
import static java.awt.GridBagConstraints.*;

public class Road extends Applet {

    Checkbox safe;
    //Button martinHouse, martinPath, martinRoad, enemyEnter, enemyExit;
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
        add(safe = new Checkbox("Safe Model"),gbc);

        /*
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
        */

        gbc.weightx = 0;
        gbc.fill = BOTH;
        gbc.gridwidth = 6;
        gbc.gridy = 0;
        gbc.gridx = 0;
        Panel environment = new Panel();
        environment.add(road);
        add(environment,gbc);


        //SafeMartin safeController = new SafeMartin(road);
        UnsafeMartin unsafeController = new UnsafeMartin(road);
        UnsafeMartin controller = unsafeController;


        Enemy e = new Enemy(road, controller);
        e.start();


        //add code to take information from one controller then swap
        safe.addItemListener(safeEvent -> {
            if (safe.getState()) {
                System.err.println("safe");    
            }
        });

    }


    class Martin extends Thread {
        RoadCanvas display; UnsafeMartin control;

        public Martin(RoadCanvas display, UnsafeMartin control){
            this.display = display; this.control = control;
        }

        @Override
        public void start() {
            while (true) {
                try {
                    this.sleep(1000);
                    control.exit(sensor1);
                    this.sleep(200);
                    while (control.lightOn());
                    control.enter(sensor2);
                    this.sleep(500);
                    control.exit(sensor4);
                } catch (InterruptedException e) {}

            } 
        }

     
    }

    class Enemy extends Thread {
        RoadCanvas display; UnsafeMartin control;

        public Enemy(RoadCanvas display, UnsafeMartin control){
            this.display = display; this.control = control;
        }

        @Override
        public void start() {
            while (true) {
                try {
                    this.sleep(500);
                    control.gate.pass();
                    control.enter(sensor3); 
                    System.err.println("enter road");
                    this.sleep(500);
                    control.exit(sensor4);
                    System.err.println("exit road");
                } catch (InterruptedException e) {}
            }
        }


    }

}
