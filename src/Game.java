import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 *Glowna klasa programu.
 */
public class Game extends Canvas implements Runnable {
    /**
     * Predkosc ruchu w poziomie
     */
    public static int speedBallX = 5;
    /**
     * Predkosc ruchu w pionie
     */
    public static int speedBallY = -5;
    /**
     * Dlugosc wektora ruchu.
     */
    public static float vel = (float) Math.sqrt(Math.pow(speedBallY, 2) + Math.pow(speedBallY, 2));
    private static int WIDTH = 896, HEIGHT = 504;
    private final double VER = 4.0;
    private boolean running = false;
    private Thread thread;
    private Handler handler;
    private Hud hud;
    private Spawn spawn;
    private int fps = 0;

    /**
     * Konstruktor klasy game
     */
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

    /**
     * Funkcja main programu
     * @param args Parametry wejsciowe programu.
     */
    public static void main(String[] args) {
        new Game();
    }

    /**
     * Funkcja sprawdza polozenie pomiedzy zakresem wartosci
     * @param val polozenie obiektu
     * @param min wartosc minimalna
     * @param max wartosc maksymalna
     * @return zwraca polozenie z zakresu min - max
     */
    public static float clamp(float val, float min, float max) {
        if (val <= min) return min;
        else if (val >= max) return max;
        else return val;
    }

    /**
     * getter do pobierania szerokosci okna
     * @return szerokosc okna
     */
    public static int getWIDTH() {
        return WIDTH;
    }

    /**
     * getter do pobierania wysokosci okna
     * @return wysokosc okna
     */
    public static int getHEIGHT() {
        return HEIGHT;
    }

    /**
     * getter do pobierania  predkosci po osi X
     * @return predkosc po osi X
     */
    public static int getSpeedBallX() {
        return speedBallX;
    }

    /**
     * getter do pobierania predkosci po osi Y
     * @return predkosc po osi Y
     */
    public static int getSpeedBallY() {
        return speedBallY;
    }

    /**
     * metoda tworzaca watek glowny gry
     */
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    /**
     * metoda kończaca watek glowny programu
     */
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Glowna metoda odpowiedzialna za wyswitlanie
     */
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

    /**
     * Wrapper dla metody tick z klasy handler
     */
    private void tick() {
        if (Hud.state != 2)
        handler.tick();
    }

    /**
     * Metoda odpowiedzialna za wyswietlanie obrazu
     */
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //tlo
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, WIDTH, HEIGHT);


        handler.render(g);
        hud.Update(g);


        g.dispose();
        bs.show();
    }
}
