import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private final double VER = 2.0;
    private static int WIDTH = 896, HEIGHT = 504;
    private boolean running = false;
    private String title = "BrickBreaker v" + VER;
    private Thread thread;
    private Handler handler;

    private Hud hud;
    private int fps = 0;
    public static int speedBallX = 5, speedBallY = -5, speedPlayerX = 10;



    public Game() {
        handler = new Handler();
        this.hud = new Hud(HEIGHT, WIDTH);

        this.addKeyListener(new KeyInput(handler));
        this.setFocusable(true);

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        new Window(WIDTH, HEIGHT, title, this);

        handler.addObject(new Player(WIDTH / 2 - 50, HEIGHT - 20, 120, 15, ID.Player));
        handler.addObject(new Ball(WIDTH / 2 - 5, HEIGHT - 41, 10, handler, ID.Ball));


        for (int j = 0; j < 5; j++)
            for (int i = 0; i < WIDTH; i += 60) {
                handler.addObject(new Brick(i, j * 15 + 30, 60, 15, ID.Brick, 2));
            }


    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
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
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) render();
            fps++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + fps);
                fps = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //tło
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        hud.Update(bs);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Game();
    }

    public static float clamp(float val, float min, float max) {
        if (val <= min) return min;
        else if (val >= max) return max;
        else return val;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getSpeedBallX() {
        return speedBallX;
    }

    public static void setSpeedBallX(int speedBallX) {
        Game.speedBallX = speedBallX;
    }

    public static int getSpeedBallY() {
        return speedBallY;
    }

    public static void setSpeedBallY(int speedBallY) {
        Game.speedBallY = speedBallY;
    }

    public static int getSpeedPlayerX() {
        return speedPlayerX;
    }

    public static void setSpeedPlayerX(int speedPlayerX) {
        Game.speedPlayerX = speedPlayerX;
    }
}
