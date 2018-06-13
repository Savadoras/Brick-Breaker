import java.awt.*;

public class Ball extends GameObject {

    private int radius;
    private Handler handler;

    /**
     * Konstruktor obiektu Ball
     * @param x - wspolrzedna x
     * @param y - wspolrzedna y
     * @param radius - promien
     * @param handler
     * @param id - id pilki
     */
    public Ball(float x, float y, int radius, Handler handler, ID id) {
        super(x, y, id);

        this.handler = handler;
        this.radius = radius;

        velX = velY = 0;
    }

    @Override

    protected void tick() {

        for (int j = 0; j < 30; j++) {
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);


                if (tempObject.getId() == ID.Player) {
                    Player player = (Player) tempObject;
                    if (intersect(player.getLeft() + player.getVelX(), player.getRight() + player.getVelX(), player.getTop(), player.getBottom(), tempObject) == 1) {
                        break;
                    }

                }

                if (tempObject.getId() == ID.Brick) {
                    Brick brick = (Brick) tempObject;
                    if (intersect(brick.getLeft(), brick.getRight(), brick.getTop(), brick.getBottom(), tempObject) == 1) {
                        if (brick.getLife() == 1) handler.object.remove(tempObject);
                        else
                            brick.setLife(brick.getLife() - 1);
                        Hud.score++;
                        if(Hud.score == 2* Spawn.numberOfBricks)
                        {
                            Hud.state = 3;
                            Hud.score = 0;
                            handler.object.remove(this);
                        }
                        break;
                    }
                }
            }

            x += velX / 30;
            y += velY / 30;

            if (x <= 0) {
                x = 0;
                velX *= -1;
            } else if (x >= Game.getWIDTH() - 2 * radius) {
                velX *= -1;
                x = Game.getWIDTH() - 2 * radius;
            }
            if (y <= 0) {
                y = 0;
                velY *= -1;
            } else if (y >= Game.getHEIGHT() - 20 - radius) {
                // velY *= -1;
                //  y=Game.getHEIGHT() - 2 * radius;
                Hud.state = 2;
                handler.object.remove(this);

            }
        }
    }

    @Override
    protected void render(Graphics g) {
        g.setColor(new Color(81, 87, 233));
        g.fillOval((int) x, (int) y, radius * 2, radius * 2);
    }

    /**
     *
     * @param left
     * @param right
     * @param top
     * @param bottom
     * @param tempObject
     * @return
     */
    public int intersect(float left, float right, float top, float bottom, GameObject tempObject) {
        double sX = this.x + radius + this.velX;
        double sY = this.y + radius + this.velY;

        if (Math.pow(sX - left, 2) + Math.pow(sY - top, 2) <= Math.pow(radius, 2) && sX < left && sY < top && velY > 0 && velX > 0) {
            //kolizja z lewym gornym rogiem
            double a = (left - sX) / (top - sY);
            double nX = -velX;
            double nY = -velY;
            velX = (float) (((1 - Math.pow(a, 2)) / (1 + Math.pow(a, 2))) * nX + ((2 * a) / (1 + Math.pow(a, 2))) * nY);
            velY = (float) ((2 * a) / (1 + Math.pow(a, 2)) * nX - (1 - Math.pow(a, 2)) / (1 + Math.pow(a, 2)) * nY);

            return 1;
        } else if (Math.pow(sX - right, 2) + Math.pow(sY - top, 2) <= Math.pow(radius, 2) && sX > right && sY < top && velY > 0 && velX < 0) {
            //kolizja z prawym gornym rogiem
            double a = (right - sX) / (top - sY);
            double nX = -velX;
            double nY = -velY;
            velX = (float) (((1 - Math.pow(a, 2)) / (1 + Math.pow(a, 2))) * nX + ((2 * a) / (1 + Math.pow(a, 2))) * nY);
            velY = (float) ((2 * a) / (1 + Math.pow(a, 2)) * nX - (1 - Math.pow(a, 2)) / (1 + Math.pow(a, 2)) * nY);

            return 1;
        } else if (Math.pow(sX - left, 2) + Math.pow(sY - top, 2) <= Math.pow(radius, 2) && sX < left && sY > bottom && velY < 0 && velX > 0) {
            //kolizja z lewym dolnym rogiem
            double a = (left - sX) / (bottom - sY);
            double nX = -velX;
            double nY = -velY;
            velX = (float) (((1 - Math.pow(a, 2)) / (1 + Math.pow(a, 2))) * nX + ((2 * a) / (1 + Math.pow(a, 2))) * nY);

            velY = (float) ((2 * a) / (1 + Math.pow(a, 2)) * nX - (1 - Math.pow(a, 2)) / (1 + Math.pow(a, 2)) * nY);

            return 1;
        } else if (Math.pow(sX - right, 2) + Math.pow(sY - top, 2) <= Math.pow(radius, 2) && sX > right && sY > bottom && velY < 0 && velX < 0) {
            //kolizja z prawym dolnym rogiem
            double a = (right - sX) / (bottom - sY);
            double nX = -velX;
            double nY = -velY;
            velX = (float) (((1 - Math.pow(a, 2)) / (1 + Math.pow(a, 2))) * nX + ((2 * a) / (1 + Math.pow(a, 2))) * nY);
            velY = (float) ((2 * a) / (1 + Math.pow(a, 2)) * nX - (1 - Math.pow(a, 2)) / (1 + Math.pow(a, 2)) * nY);

            return 1;
        } else if (sY >= top && sY <= bottom && sX + radius >= left && sX - radius <= right) {

            if (tempObject.getId() == ID.Player) {
                velX = tempObject.getVelX();
            } else
                velX *= -1;
            return 1;
        } else if (sX >= left && sX <= right && (sY + radius) >= top && (sY - radius) <= bottom) {


            if (tempObject.getId() == ID.Player) {
                Player player = (Player) tempObject;
                //if(player.getVelX()!=0) {
                System.out.println("velX:" + velX + "  velY:" + velY + "  playerVelX:" + player.getVelX());
                if (Math.abs(velX + player.getVelX() / 3) <= 9 * (Game.vel / 10))
                    velX = velX + player.getVelX() / 3;
                else if (velX > 0)
                    velX = 9 * (Game.vel / 10);
                else
                    velX = -9 * (Game.vel / 10);
                if (velY > 0) {
                    velY = (-1) * (float) Math.sqrt(Math.pow(Game.vel, 2) - Math.pow(velX, 2));
                } else velY = (float) Math.sqrt(Math.pow(Game.vel, 2) - Math.pow(velX, 2));
                System.out.println("velX:" + velX + "  velY:" + velY + "  playerVelX:" + player.getVelX());
                // }else velY*=(-1);
            } else velY *= (-1);

            return 1;
        }

        return 0;
    }

}