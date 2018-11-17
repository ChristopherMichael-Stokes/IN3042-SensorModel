package SafeMartin.road;

import java.awt.*;
import java.applet.*;
import static java.awt.GridBagConstraints.*;;

public class Road extends Applet {

    Button martinHouse, martinPath, martinRoad, enemyEnter, enemyExit;

    @Override
    public void init() {
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
        gbc.fill = REMAINDER;
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

    class RoadCanvas extends Canvas {
        final int width, height;

        public RoadCanvas (int width, int height) {
            setBackground (Color.green);
            this.width = width; this.height = height;
            setSize(width, height);
        }

        public void paint (Graphics g) {
            Graphics2D g2;
            g2 = (Graphics2D) g;

            int padding = 10, width_=width/8, height_= width/16, size = padding+height_;
            g2.setColor(Color.red);
            g2.fillPolygon(new int[]{width/2 - width_, width/2, width/2 + width_}, 
                    new int[]{padding + height_, padding, padding + height_}, 3);
            size += padding;

            height_ = width_;
            g2.fillRect(width/2 - width_/2, size, height_, width_);
            size += height_;
        }
   }

}
