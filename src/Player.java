import java.awt.*;

public class Player extends GameObject {

    private int width, height;
    private float top, bottom, right, left;
    private float a;
    public boolean pressL,pressR;

    Player(float x, float y, int width, int height, ID id) {
        super(x, y, id);
        a=0;
        velX = velY = 0;
        pressL = pressR = false;
        this.width = width;
        this.height = height;

        top = y;
        bottom = y + height;
        left = x;
        right = x + width;
    }

    @Override
    protected void tick() {

        //System.out.println("L = "+pressL+" P = "+pressR);

        if(pressL)acceleration((float)-0.8);
        if(pressR)acceleration((float) 0.8);

        if(pressR==false&pressL==false)
        if(Math.abs(velX)>1) {
            if (velX > 0) velX -= 1;
            if (velX < 0) velX += 1;
        }else velX=0;

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
        g.setColor(new Color(39, 70, 134));
        g.fillRect((int) x, (int) y, width, height);
    }

    public void acceleration(float a){


        if(a>0&velX>=0){
            if(velX<=13) velX += a;
            else velX = 13;
        }else
        if(a<0&velX<=0){
            if(velX>=-13) velX += a;
            else velX = -13;
        }else
        if(a<0&velX>0){
            if(velX>=-13) velX += 3*a;
            else velX = -13;
        }else
        if(a>0&velX<0){
            if(velX<=13) velX += 3*a;
            else velX = 13;
        }

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
