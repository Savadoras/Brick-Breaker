import javax.swing.*;
import java.awt.*;

/**
 * Klasa odpowiedzialna za tworzenie okna
 */
public class Window extends Canvas {

    /**
     *  Konstruktor klasy Window
     * @param title tytul okna
     * @param game referencja na obiekt klasy Game
     */
    public Window(String title, Game game) {
        JFrame frame = new JFrame(title);

        frame.add(game);
        frame.pack();

        //ustawienie wlasnosci okna z gra
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.toFront();
        frame.requestFocus();
        frame.setVisible(true);

        game.start();
    }
}
