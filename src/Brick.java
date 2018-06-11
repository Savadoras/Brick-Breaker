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
                g.setColor(Color.GRAY);
                break;
            case 2:
                g.setColor(Color.LIGHT_GRAY);
                break;
            case 3:
                g.setColor(Color.WHITE);
                break;
        }

        g.fillRect((int) x, (int) y, (int) width, (int) height);
        g.setColor(Color.DARK_GRAY);
        g.drawRect((int) x, (int) y, (int) width, (int) height);
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

    public void setTop(float top) {
        this.top = top;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
