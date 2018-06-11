import java.awt.*;

public class Ball extends GameObject {

    private int radius;
    private Handler handler;


    public Ball(float x, float y,int radius,Handler handler, ID id) {
        super(x, y, id);

        this.handler = handler;
        this.radius=radius;

        velX=velY=0;

    }

    @Override
    protected void tick() {

        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId()==ID.Player){
                Player player = (Player) tempObject;
                intersect(player.getLeft()+player.getVelX(),player.getRight()+player.getVelX(),player.getTop(),player.getBottom() , tempObject);
            }

            if(tempObject.getId()==ID.Brick){
                Brick brick = (Brick) tempObject;
                if(intersect(brick.getLeft(),brick.getRight(),brick.getTop(),brick.getBottom(),tempObject)==1){
                    if(brick.getLife()==1)handler.object.remove(tempObject);
                    else
                        brick.setLife(brick.getLife()-1);

                }
            }
        }

        x+=velX;
        y+=velY;


        if(x<=0) {
            velX *= -1;
        }
        else
        if(x>=Game.getWIDTH()-2*radius) velX*=-1;

        if(y<=0) {
            velY *= -1;
        }
        else
        if(y>=Game.getHEIGHT()-2*radius)velY*=-1;// handler.object.remove(this);

    }

    @Override
    protected void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillOval((int)x,(int)y,radius*2,radius*2);
    }


    public int intersect(float left,float right,float top, float bottom, GameObject tempObject){
        double sX = this.x+radius + this.velX;
        double sY = this.y+radius + this.velY;


        if ( Math.pow(sX-left,2) + Math.pow(sY-top,2) <= Math.pow(radius,2) &&sX<left&&sY<top &&velY>0&&velX>0 ) {
                //kolizja z lewym gornym rogiem
                double a = (left - sX) / (top - sY);
                double nX = -velX;
                double nY = -velY;
                velX = (float) (((1 - Math.pow(a, 2)) / (1 + Math.pow(a, 2))) * nX + ((2 * a) / (1 + Math.pow(a, 2))) * nY);
                velY = (float)((2 * a) / (1 + Math.pow(a, 2)) * nX - (1 - Math.pow(a, 2)) / (1 + Math.pow(a, 2)) * nY);

                return 1;
        }
        else
        if ( Math.pow(sX-right,2) + Math.pow(sY-top,2) <= Math.pow(radius,2) &&sX>right&&sY<top &&velY>0&&velX<0 ) {
            //kolizja z prawym gornym rogiem
            double a= (right-sX)/(top-sY);
            double nX=-velX;
            double nY=-velY;
            velX=(float)( ((1 - Math.pow(a,2))/(1 + Math.pow(a,2))) * nX + ((2*a)/(1 + Math.pow(a,2))) * nY );
            velY=(float)( (2*a)/(1 + Math.pow(a,2)) * nX - (1 - Math.pow(a,2))/(1 + Math.pow(a,2))  * nY );

            return 1;
        }
        else
        if ( Math.pow(sX-left,2) + Math.pow(sY-top,2) <= Math.pow(radius,2)&&sX<left&&sY>bottom &&velY<0&&velX>0) {
            //kolizja z lewym dolnym rogiem
            double a= (left-sX)/(bottom-sY);
            double nX=-velX;
            double nY=-velY;
            velX=(float)( ((1 - Math.pow(a,2))/(1 + Math.pow(a,2))) * nX + ((2*a)/(1 + Math.pow(a,2))) * nY );

            velY=(float)( (2*a)/(1 + Math.pow(a,2)) * nX - (1 - Math.pow(a,2))/(1 + Math.pow(a,2))  * nY );

            return 1;
        }
        else
        if ( Math.pow(sX-right,2) + Math.pow(sY-top,2) <= Math.pow(radius,2)&&sX>right&&sY>bottom &&velY<0&&velX<0) {
            //kolizja z prawym dolnym rogiem
            double a= (right-sX)/(bottom-sY);
            double nX=-velX;
            double nY=-velY;
            velX=(float)( ((1 - Math.pow(a,2))/(1 + Math.pow(a,2))) * nX + ((2*a)/(1 + Math.pow(a,2))) * nY );
            velY=(float)( (2*a)/(1 + Math.pow(a,2)) * nX - (1 - Math.pow(a,2))/(1 + Math.pow(a,2))  * nY );

            return 1;
        }
        else
        if(sY>=top&&sY<=bottom&&sX+radius>=left&&sX-radius<=right) {
            
            velX *= -1;
            return 1;
        }else
        if(sX>=left&&sX<=right&& (sY+radius)>=top && (sY-radius)<=bottom){ //to do
            velY *= -1;
            return 1;
        }


            return 0;
    }
    
}
