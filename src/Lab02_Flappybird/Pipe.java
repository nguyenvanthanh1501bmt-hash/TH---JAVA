package Lab02_Flappybird;

import java.awt.*;

public class Pipe {
    int x, y;
    int width = 64;
    int height = 512;
    Image img;
    boolean isPassed = false;

    public Pipe(Image img, int x, int y) {
        this.img = img;
        this.x = x;
        this.y = y;
    }
}