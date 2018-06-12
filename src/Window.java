import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

    public Window(String title, Game game) {
        JFrame frame = new JFrame(title);

        frame.add(game);
        frame.pack();

        //ustawienie własności okna z grą
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.toFront();
        frame.requestFocus();
        frame.setVisible(true);

        game.start();
    }
}
