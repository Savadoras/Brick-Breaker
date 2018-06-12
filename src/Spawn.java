import java.awt.*;

public class Spawn {

    private int level;
    private Handler handler;

    public Spawn(Handler handler) {
        this.handler=handler;
        level=1;
    }

    public void tick(){





        switch (level){
            case 1:
            handler.addObject(new Player(Game.getWIDTH() / 2 - 50, Game.getHEIGHT() - 20, 120, 15, ID.Player));
            handler.addObject(new Ball(Game.getWIDTH() / 2 - 5, Game.getHEIGHT() - 41, 10, handler, ID.Ball));


            for (int j = 0; j < 5; j++)
                for (int i = 0; i < Game.getWIDTH(); i += 60) {
                    handler.addObject(new Brick(i, j * 15 + 30, 60, 15, ID.Brick, 2));
                }
                break;
        }

        Hud.state = 0;

    }

}
