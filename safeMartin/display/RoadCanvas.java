package safeMartin.display;

import static safeMartin.road.SafeMartin.*;
import java.awt.*;
import java.applet.*;


public class RoadCanvas extends Canvas {
    final int width, height;

    private Color lightColor;
    private boolean gateOpen;


    public RoadCanvas (int width, int height) {
        setBackground (Color.green);
        this.width = width; this.height = height;
        setSize(width, height-(int)height/3);

        gateOpen = true;
        lightColor = Color.white;
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

        //light
        g2.setColor(lightColor);
        g2.fillRect(width/2 - width_, size + size/4, width_/2, width_/2);
        size += height_;

        //road
        g2.setColor(Color.lightGray);
        width_ = 4*width / 5; height_ = width_/4;
        g2.fillRect(width/2 - width_/2, size, width_, width_/4);

        //gate
        height_ = width_/4;
        System.err.println(height_);
        g2.setColor(Color.black);
        int begin;
        if (gateOpen)
            begin = size-height_;
        else 
            begin = size;
        g2.fillRect(width/2 - width_/2, begin, width_/32, height_);

    }

    public void openGate() {
        gateOpen = true;
        repaint();
    }

    public void closeGate() {
        gateOpen = false;
        repaint();
    }

    public void lightOn() {
        lightColor = Color.red;
        repaint();
    }

    public void lightOff() {
        lightColor = Color.white;
        repaint();
    }

}
