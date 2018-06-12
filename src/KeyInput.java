import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_LEFT) tempObject.setVelX(-10);
                if (key == KeyEvent.VK_RIGHT) tempObject.setVelX(10);
            }

            if (tempObject.getId() == ID.Ball) {
                Ball ball = (Ball) tempObject;
                if (ball.getVelX() == 0 && ball.getVelY() == 0)
                    if (key == KeyEvent.VK_SPACE) {
                        Hud.state = 1;
                        ball.setVelX(5);
                        ball.setVelY(-5);
                    }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);

        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_LEFT && tempObject.getVelX() < 0) tempObject.setVelX(0);
                if (key == KeyEvent.VK_RIGHT && tempObject.getVelX() > 0) tempObject.setVelX(0);
            }
        }
    }
}
