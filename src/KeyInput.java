import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler hendler;

    public KeyInput(Handler handler) {
        this.hendler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        int key = e.getKeyCode();

        for (int i = 0; i < hendler.object.size(); i++) {
            GameObject tempObject = hendler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_LEFT) tempObject.setVelX(-10);
                if (key == KeyEvent.VK_RIGHT) tempObject.setVelX(10);

            }

            if (tempObject.getId() == ID.Ball) {
                Ball ball = (Ball) tempObject;
                if (ball.getVelX() == 0 && ball.getVelY() == 0)
                    if (key == KeyEvent.VK_SPACE) {
                        ball.setVelX(2);
                        ball.setVelY(-2);
                    }
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);

        int key = e.getKeyCode();

        for (int i = 0; i < hendler.object.size(); i++) {
            GameObject tempObject = hendler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_LEFT && tempObject.getVelX() < 0) tempObject.setVelX(0);
                if (key == KeyEvent.VK_RIGHT && tempObject.getVelX() > 0) tempObject.setVelX(0);

            }

        }
    }
}
