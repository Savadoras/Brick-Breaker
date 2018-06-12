import java.awt.*;
import javax.swing.JFrame;

public class Window extends Canvas {

    public Window(int width, int height, String title, Game game) {
        JFrame frame = new JFrame(title);

        /*frame.setPreferredSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));*/

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
