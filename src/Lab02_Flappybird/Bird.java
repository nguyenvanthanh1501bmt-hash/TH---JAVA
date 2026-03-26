package Lab02_Flappybird;

import javax.swing.*;
import java.awt.*;

public class Bird {
    int x = 50, y = 300;
    int width = 34, heigh = 24;

    double velocity = 0; //gia tốc trọng trườn
    double gravity = 0.5; //trọng lực

    Image birdImage;

    public Bird() {
        birdImage = new ImageIcon("src/images/lab02/flappybird.png").getImage();
    }

    //rơi xuống theo trọng lực
    public void dropdown() {
        velocity += gravity;
        y += velocity;
    }

    public void jump() {
        velocity = -8;
    }

    public void paintComponent(Graphics g) {
        g.drawImage(birdImage, x, (int)y, width, heigh, null);
    }
}