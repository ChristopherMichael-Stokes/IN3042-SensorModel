package safeMartin.road;

import safeMartin.display.RoadCanvas;
import java.awt.*;
import java.applet.*;
import static java.awt.GridBagConstraints.*;

public class SafeMartin extends Applet {

    Button martinHouse, martinPath, martinRoad, enemyEnter, enemyExit;
    public enum Entity {Martin, Enemy}
    public enum MartinLocation {InHouse, OnPath, OnRoad}
    private boolean gateOpen, lightOn;
    

    @Override
    public void init() {
        gateOpen = true;
        lightOn = false;
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
        Canvas rd = new RoadCanvas(getWidth(), getHeight());
        Panel environment = new Panel();
        environment.add(rd);
        add(environment,gbc);
    }

    class Martin implements Runnable {
        RoadCanvas display;

        public Martin(RoadCanvas display){
            this.display = display;


        }

        @Override
        public void run() {

        }

    
    }

}
