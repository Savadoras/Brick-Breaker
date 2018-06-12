import java.awt.*;

public class Hud {

    public static int state = 0;
    public static int score = 0;

    private final int HEIGHT, WIDTH;

    public Hud(int height, int width) {
        this.HEIGHT = height;
        this.WIDTH = width;
    }

    public void Update(Graphics g) {
        g.setColor(Color.BLUE);

        switch (state) {
            case 0:
                g.setFont(new Font("Times", Font.BOLD, 30));
                g.drawString("PRESS SPACEBAR TO START! ", WIDTH - 650, HEIGHT / 2);
                break;
            case 1:
                g.setFont(new Font("Times", Font.BOLD, 20));
                g.drawString("SCORE: " + score, WIDTH - 120, 20);
                break;
            case 2:
                g.setFont(new Font("Times", Font.BOLD, 30));
                g.drawString("GAME OVER, PRESS SPACEBAR TO RESTART! ", WIDTH - 780, HEIGHT / 2);
                break;
            case 3:
                g.setFont(new Font("Times", Font.BOLD, 30));
                g.drawString("GAME WON!!, PRESS SPACEBAR TO RESTART! ", WIDTH - 780, HEIGHT / 2);
                break;
        }

    }

}
