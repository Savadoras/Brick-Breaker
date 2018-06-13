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

    /**
     *  Konstruktor klasy Brick
     * @param x polozenie cegly w osi x
     * @param y polozenie cegly w osi y
     * @param width szerokosc cegly
     * @param height wysokosc cegly
     * @param id id obiektu
     * @param life zycie cegly
     */

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
                g.drawImage(image, (int)x, (int)y,null);
                break;
            case 2:
                g.drawImage(image2, (int)x, (int)y,null);
                break;

        }
    }


    /**
     * getter do gornej krawedzi cegly.
     * @return Wspolrzedna Y gornej krawedzi.
     */
    public float getTop() {
        return top;
    }
    /**
     * getter do dolnej krawedzi cegly.
     * @return Wspolrzedna Y dolnej krawedzi.
     */
    public float getBottom() {
        return bottom;
    }
    /**
     * Getter do prawej krawedzi cegly.
     * @return Wspolrzedna X prawej krawedzi.
     */
    public float getRight() {
        return right;
    }
    /**
     * getter do lewej krawedzi cegly.
     * @return Wspolrzedna X lewej krawedzi.
     */
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
