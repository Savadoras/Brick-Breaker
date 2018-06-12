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
                Player player = (Player) tempObject;
                if (key == KeyEvent.VK_LEFT) player.pressL = true;
                if (key == KeyEvent.VK_RIGHT) player.pressR = true;
            }

            if (tempObject.getId() == ID.Ball) {
                Ball ball = (Ball) tempObject;
                if (ball.getVelX() == 0 && ball.getVelY() == 0)
                    if (key == KeyEvent.VK_SPACE) {

                        Hud.state = 1;
                        ball.setVelX(Game.getSpeedBallX());
                        ball.setVelY(Game.getSpeedBallY());

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
                Player player = (Player)tempObject;
                if (key == KeyEvent.VK_LEFT ) player.pressL=false;//tempObject.setVelX(0);
               if (key == KeyEvent.VK_RIGHT ) player.pressR=false;//tempObject.setVelX(0);
            }
        }
    }
}
