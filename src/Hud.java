import java.awt.*;
import java.awt.image.BufferStrategy;

public class Hud {

    public static int state = 0;

    private final int HEIGHT, WIDTH;

    public Hud(int height, int width) {
        this.HEIGHT = height;
        this.WIDTH = width;
    }

    public void Update(BufferStrategy bs) {
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLUE);

        switch (state) {
            case 0:
                g.setFont(new Font("Times", Font.BOLD, 30));
                g.drawString("PRESS SPACEBAR TO START! ", WIDTH - 650, HEIGHT / 2);
                break;
            case 1:
                g.setFont(new Font("Times", Font.BOLD, 20));
                g.drawString("SCORE: ", WIDTH - 120, 20);
                break;
            case 2:
                g.setFont(new Font("Times", Font.BOLD, 30));
                g.drawString("GAME OVER, PRESS SPACEBAR TO RETART! ", WIDTH - 650, HEIGHT / 2);
                break;
        }

    }


    public void setState(int state) {
        this.state = state;
    }
}
