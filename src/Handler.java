import java.awt.*;
import java.util.LinkedList;

/**
 * Klasa odpowiadająca za przechowywanie wszystkich obiektow z gry
 */
public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    /**
     * Metoda wywolujaca metody obliczajacy parametry wszystkich obiektow
     */
    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    /**
     * Metoda wywolujaca rysowanie wszystkich obiektow
     * @param g Obiekt klasy rysującej
     */
    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    /**
     * Metoda dodajaca obiekt
     * @param object dodawany obiekt
     */
    public void addObject(GameObject object) {
        this.object.add(object);
    }

    /**
     * Metoda usuwajaca obiekt
     * @param object usuwany obiekt
     */
    public void removeObject(GameObject object) {
        this.object.remove(object);
    }

    /**
     * Metoda usuwajaca wszystkie obiekty
     */
    public void removeAllObjects() {
        this.object.clear();
    }
}
