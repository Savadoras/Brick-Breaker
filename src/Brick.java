import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Brick extends GameObject {

    private int width, height;
    private float top, bottom, right, left;
    private int life;

    private BufferedImage image,image2;



    public Brick(float x, float y, int width, int height, ID id, int life) {
        super(x, y, id);

        this.width = width;
        this.height = height;
        this.life = life;

        top = y;
        bottom = y + height;
        left = x;
        right = x + width;
        try {
            image = ImageIO.read(this.getClass().getResource("brick1.png"));
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }
        try {
            image2 = ImageIO.read(this.getClass().getResource("brick2.png"));
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }


    }

    @Override
    protected void tick() {
    }

    @Override
    protected void render(Graphics g) {
        switch (life) {
            case 1:
                g.setColor(new Color(241, 35, 36));
                g.drawImage(image, (int)x, (int)y,null);
                break;
            case 2:
                g.setColor(new Color(156, 23, 28));
                g.drawImage(image2, (int)x, (int)y,null);
                break;
            case 3:
                g.setColor(new Color(113, 9, 20));
                break;
        }

       /* g.fillRect((int) x, (int) y, (int) width, (int) height);
        g.setColor(Color.WHITE);
        g.drawRect((int) x, (int) y, (int) width, (int) height);*/

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getTop() {
        return top;
    }

    public float getBottom() {
        return bottom;
    }

    public float getRight() {
        return right;
    }

    public float getLeft() {
        return left;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
