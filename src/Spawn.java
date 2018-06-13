/**
 * Klasa odpowiedzialna za tworzenie obiekow gry
 */
public class Spawn {

    private int level;
    private Handler handler;
    public static int numberOfBricks;

    /**
     * konstruktor klasy Spawn
     * @param handler opakowanie na wszystkie obiekty gry
     */
    public Spawn(Handler handler) {
        this.handler = handler;
        level = 1;
        numberOfBricks = 0;
    }

    /**
     * metoda tworzaca nowe obiekty gry
     */
    public void tick() {

        Hud.score = 0;
        switch (level) {
            case 1:
                handler.addObject(new Player(Game.getWIDTH() / 2 - 50, Game.getHEIGHT() - 20, 120, 15, ID.Player));
                handler.addObject(new Ball(Game.getWIDTH() / 2 - 5, Game.getHEIGHT() - 41, 10, handler, ID.Ball));

                numberOfBricks = 0;
                for (int j = 0; j < 5; j++)
                    for (int i = 0; i < Game.getWIDTH(); i += 60)
                    {
                        handler.addObject(new Brick(i, j * 15 + 30, 60, 15, ID.Brick, 2));
                        numberOfBricks++;
                    }
                break;
        }

        Hud.state = 0;

    }

}
