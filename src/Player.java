import java.awt.*;

/**
 * Klasa zwiazana z paletka.
 */
public class Player extends GameObject {

    /**
     * Zmienne mowiace o tym czy klawisz jest wcisniety.
     */
    public boolean pressL, pressR;
    private int width, height;
    private float top, bottom, right, left;
    private float a;

    /**
     * Konstruktor klasy Player.
     * @param x Polozenie poczatkowe paletki w osi X.
     * @param y Polozenie poczatkowe paletki w osi Y.
     * @param width Wysokosc paletki.
     * @param height Szerokosc paletki.
     * @param id Identyfikator obiektu paletka.
     */
    Player(float x, float y, int width, int height, ID id) {
        super(x, y, id);
        a = 0;
        velX = velY = 0;
        pressL = pressR = false;
        this.width = width;
        this.height = height;

        top = y;
        bottom = y + height;
        left = x;
        right = x + width;
    }

    /**
     * metoda odpowiedzialna za przesuwanie paletki.
     */
    @Override
    protected void tick() {

        if (pressL) acceleration((float) -0.8);
        if (pressR) acceleration((float) 0.8);

        if (!pressR & !pressL)
            if (Math.abs(velX) > 1) {
                if (velX > 0) velX -= 1;
                if (velX < 0) velX += 1;
            } else velX = 0;

        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.getWIDTH() - width);

        top = y;
        bottom = y + height;
        left = x;
        right = x + width;
    }

    /**
     * metoda odpowiedzialna za wyswietlanie paletki.
     * @param g Obiekt klasy graficznej.
     */
    @Override
    protected void render(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect((int) x, (int) y, width, height);
    }

    /**
     * Metoda obliczajaca predkosc paletki na podstawie przyspieszenia.
     * @param a przyspieszenie paletki.
     */
    private void acceleration(float a) {


        if (a > 0 & velX >= 0) {
            if (velX <= 13) velX += a;
            else velX = 13;
        } else if (a < 0 & velX <= 0) {
            if (velX >= -13) velX += a;
            else velX = -13;
        } else if (a < 0 & velX > 0) {
            if (velX >= -13) velX += 3 * a;
            else velX = -13;
        } else if (a > 0 & velX < 0) {
            if (velX <= 13) velX += 3 * a;
            else velX = 13;
        }

    }

    /**
     * getter do gornej krawedzi paletki.
     * @return Wspolrzedna Y gornej krawedzi.
     */
    public float getTop() {
        return top;
    }

    /**
     * getter do dolnej krawedzi paletki.
     * @return Wspolrzedna Y dolnej krawedzi.
     */
    public float getBottom() {
        return bottom;
    }

    /**
     * Getter do prawej krawedzi paletki.
     * @return Wspolrzedna X prawej krawedzi.
     */
    public float getRight() {
        return right;
    }

    /**
     * getter do lewej krawedzi paletki.
     * @return Wspolrzedna X lewej krawedzi.
     */
    public float getLeft() {
        return left;
    }

}
