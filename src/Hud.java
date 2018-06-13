import java.awt.*;

/**
 * Klasa odpowiedzialna za obsluge interfejsu z uzytkownikiem
 */
public class Hud {

    /**
     * Stan gry w jakim obecnie sie znajdujemy.
     */
    public static int state = 0;
    /**
     * Ilosc zdobytych punktow.
     */
    public static int score = 0;

    private final int HEIGHT, WIDTH;

    /**
     * Konstruktor klasy Hud
     * @param height wysokosc okna w ktorym dziala gra
     * @param width szerokosc okna w ktorym dziala gra
     */
    public Hud(int height, int width) {
        this.HEIGHT = height;
        this.WIDTH = width;
    }

    /**
     * metoda odpowiedzialna za wyswietlnie poprawnego komunikatu
     * @param g obiekt klasy odpowiedzialnej za wyswietlnie na ekranie
     */
    public void Update(Graphics g) {
        g.setColor(Color.BLUE);

        switch (state) {
            case 0:
                g.setFont(new Font("Times", Font.BOLD, 30));
                g.drawString("PRESS SPACEBAR TO START! ", WIDTH - 650, HEIGHT / 2);
                break;
            case 1:
                g.setFont(new Font("Times", Font.BOLD, 20));
                g.drawString("SCORE: " + score, WIDTH - 120, 20);
                break;
            case 2:
                g.setFont(new Font("Times", Font.BOLD, 30));
                g.drawString("GAME OVER, PRESS SPACEBAR TO RESTART! ", WIDTH - 780, HEIGHT / 2);
                break;
            case 3:
                g.setFont(new Font("Times", Font.BOLD, 30));
                g.drawString("GAME WON!!, PRESS SPACEBAR TO RESTART! ", WIDTH - 780, HEIGHT / 2);
                break;
        }

    }

}
