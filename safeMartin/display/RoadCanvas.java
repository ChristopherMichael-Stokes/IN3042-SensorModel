package safeMartin.display;

import static safeMartin.road.SafeMartin.*;
import java.awt.*;
import java.applet.*;


public class RoadCanvas extends Canvas {
    final int width, height;

    private Rectangle gate, light;
    private boolean gateOpen = true, lightOn = false;
    private MartinLocation ml;
    private int road;


    public RoadCanvas (int width, int height) {
        setBackground (Color.green);
        this.width = width; this.height = height;
        ml = MartinLocation.InHouse;
        road = 0;
        setSize(width, height);
    }

    public void paint (Graphics g) {
        Graphics2D g2;
        g2 = (Graphics2D) g;

        //house
        int padding = 10, width_= width/12, height_= width/16, size = padding+height_;
        g2.setColor(new Color(165,42,42));
        g2.fillPolygon(new int[]{width/2 - width_, width/2, width/2 + width_}, 
            new int[]{padding + height_, padding, padding + height_}, 3);

        height_ = width_; width_ *= 1.5;
        g2.setColor(new Color(210,180,140));
        g2.fillRect(width/2 - width_/2, size, width_, height_);
        size += height_;

        //path
        g2.setColor(Color.gray);
        width_ = width_/2;
        g2.fillRect(width/2 - width_/2, size, width_, height_);
        size += height_;

        //light
	if (lightOn)
	g.setColor(Color.yellow);

        //road
        g2.setColor(Color.lightGray);
        width_ = 4*width / 5; height_ = width_/4;
        g2.fillRect(width/2 - width_/2, size, width_, width_/4);

        //gate
        g2.setColor(Color.black);
        if (gateOpen) {
        
        } else {

        }

    }

    public void openGate(boolean open) throws RuntimeException {
        if (open && gateOpen || (!gateOpen && !open))
            throw new RuntimeException("Gate already open/closed");
        gateOpen = open;
        repaint();
    }

    public void lightSwitch(boolean turnOn) throws RuntimeException {
        if (lightOn && turnOn || (!lightOn && !turnOn))
            throw new RuntimeException("Light already on/off");
            
        lightOn = turnOn;
        repaint();
    }

    public void leaveHouse() throws RuntimeException {
        if (ml != MartinLocation.InHouse)
            throw new RuntimeException("Martin is not in his house");



    }

    public void enterRoad(Entity e) throws RuntimeException {
        switch(e) {
            case Martin:
                if (ml != MartinLocation.OnPath)
                    throw new RuntimeException("Martin is not on the road");
                break;
            case Enemy:
                ++road;
                break;
        }
    }

    public void leaveRoad(Entity e) throws RuntimeException {
        switch(e) {
            case Martin:
                if (ml != MartinLocation.OnRoad)
                    throw new RuntimeException("Martin is not on the road");
                break;
            case Enemy:
                if (road <= 0)
                    throw new RuntimeException("There are no enemies on the road");
                break;
        }
    }


}



