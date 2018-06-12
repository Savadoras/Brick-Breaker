import java.awt.*;

public class Brick extends GameObject {

    private int width, height;
    private float top, bottom, right, left;
    private int life;

    public Brick(float x, float y, int width, int height, ID id, int life) {
        super(x, y, id);

        this.width = width;
        this.height = height;
        this.life = life;

        top = y;
        bottom = y + height;
        left = x;
        right = x + width;
    }

    @Override
    protected void tick() {
    }

    @Override
    protected void render(Graphics g) {
        switch (life) {
            case 1:
                g.setColor(new Color(241, 35, 36));
                break;
            case 2:
                g.setColor(new Color(156, 23, 28));
                break;
            case 3:
                g.setColor(new Color(113, 9, 20));
                break;
        }

        g.fillRect((int) x, (int) y, width, height);
        g.setColor(Color.WHITE);
        g.drawRect((int) x, (int) y, width, height);
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
