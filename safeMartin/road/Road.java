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
    public static final int ENEMIES = 4;

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


        Thread m = new Thread(new Martin(road, unsafeController));
        Thread e1 = new Thread(new Enemy(road, unsafeController));
        Thread e[] = new Thread[ENEMIES];
        for (int i = 0; i < ENEMIES; ++i) {
            e[i] = new Thread(new Enemy(road, unsafeController));
            e[i].start();
        }
        e1.start();
        m.start();



        //add code to take information from one controller then swap
        safe.addItemListener(safeEvent -> {
            if (safe.getState()) {
                System.err.println("safe");    
            }
        });

    }


    class Martin implements Runnable {
        RoadCanvas display; UnsafeMartin control;

        public Martin(RoadCanvas display, UnsafeMartin control){
            this.display = display; this.control = control;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    System.err.println("Martin in house");
                    Thread.sleep(1500);
                    control.exit(sensor1);
                    display.houseExit();
                    System.err.println("Martin on path");
                    Thread.sleep(200);
                    while (control.lightOn());
                    control.enter(sensor2);
                    display.martinRoadEnter();
                    System.err.println("Martin on road");
                    Thread.sleep(500);
                    control.exit(sensor4);
                    display.martinRoadExit();
                } catch (InterruptedException e) {}

            } 
        }

     
    }

    class Enemy implements Runnable {
        RoadCanvas display; UnsafeMartin control;

        public Enemy(RoadCanvas display, UnsafeMartin control){
            this.display = display; this.control = control;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    control.gate.pass();
                    while (!display.canEnter());
                    control.enter(sensor3); 
                    display.roadEnter();
                    Thread.sleep(500);
                    System.err.println("enter road");
                    control.exit(sensor4);
                    display.roadExit();
                    Thread.sleep(500);
                    System.err.println("exit road");

                } catch (InterruptedException e) {}
            }
        }


    }

}
