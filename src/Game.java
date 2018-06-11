import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private static int WIDTH=600,HEIGHT=WIDTH/12*9;
    private Thread thread;
    private boolean running = false;
    private Handler handler;


    public Game(){
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));


        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH,HEIGHT));
        this.setMinimumSize(new Dimension(WIDTH,HEIGHT));

        new Window(WIDTH,HEIGHT,"Brick Breaker",this);

        handler.addObject(new Player(WIDTH/2-50,HEIGHT-20,120,15, ID.Player));
        handler.addObject(new Ball(WIDTH/2-5,HEIGHT-41,10, handler, ID.Ball));

        for(int j=0;j<5;j++)
        for(int i=0;i<WIDTH;i+=60){
            handler.addObject(new Brick(i,j*15+30,60,15,ID.Brick, 3));
        }

    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            while (delta>=1){
                tick();
                delta--;
            }
            if(running) render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.GRAY);
        g.fillRect(0,0,WIDTH,HEIGHT);

        g.setColor(Color.RED);
        g.fillRect(WIDTH,0,50,HEIGHT);
        g.fillRect(0,HEIGHT,WIDTH+50,50);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args){
        new Game();
    }

    public static float clamp(float val,float min,float max){
        if(val<=min) return min;
        else
            if (val>=max) return max;
        else
            return val;

    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }


}
