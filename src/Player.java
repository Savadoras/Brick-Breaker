import java.awt.*;

public class Player extends GameObject {

    private int width, height;
    private float top, bottom, right, left;

    Player(float x, float y, int width, int height, ID id) {
        super(x, y, id);

        velX = velY = 0;

        this.width = width;
        this.height = height;

        top = y;
        bottom = y + height;
        left = x;
        right = x + width;
    }

    @Override
    protected void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.getWIDTH() - width);

        top = y;
        bottom = y + height;
        left = x;
        right = x + width;
    }

    @Override
    protected void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int) x, (int) y, width, height);
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
}
