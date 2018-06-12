import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public static int speedBallX = 5;
    public static int speedBallY = -5;
    public static float vel = (float) Math.sqrt(Math.pow(speedBallY, 2) + Math.pow(speedBallY, 2));
    private static int WIDTH = 896, HEIGHT = 504;
    private final double VER = 4.0;
    private boolean running = false;
    private Thread thread;
    private Handler handler;
    private Hud hud;
    private Spawn spawn;
    private int fps = 0;


    public Game() {
        handler = new Handler();
        this.hud = new Hud(HEIGHT, WIDTH);
        spawn = new Spawn(handler);
        this.addKeyListener(new KeyInput(handler, spawn));
        this.setFocusable(true);

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        String title = "BrickBreaker v" + VER;
        new Window(title, this);

        spawn.tick();
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

    public static int getSpeedBallY() {
        return speedBallY;
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


        handler.render(g);
        hud.Update(g);


        g.dispose();
        bs.show();
    }
}
