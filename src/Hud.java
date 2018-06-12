import java.awt.*;

public class Hud {

    private int state = 0;

    private Graphics g;
    private final int HEIGHT, WIDTH;

    public Hud(int height, int width) {
        this.g = new G;
        this.HEIGHT = height;
        this.WIDTH = width;

    }


    public void Update() {

        switch (state) {
            case 0:
                g.setColor(Color.WHITE);
                g.setFont(new Font("Times", Font.BOLD, 30));
                g.drawString("PRESS SPACEBAR TO START! ", WIDTH/2, HEIGHT/2);
                break;
            case 1:
                g.setColor(Color.WHITE);
                g.setFont(new Font("Times", Font.BOLD, 15));
                g.drawString("SCORE: ", HEIGHT - 100, 25);
                break;
            case 2:
                g.setColor(Color.WHITE);
                g.setFont(new Font("Times", Font.BOLD, 30));
                g.drawString("GAME OVER, PRESS SPACEBAR TO RESTART: ", HEIGHT - 100, 25);
                break;
        }

    }


    public void setState(int state) {
        this.state = state;
    }
}
