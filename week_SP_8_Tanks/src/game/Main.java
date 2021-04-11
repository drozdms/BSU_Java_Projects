package game;
import java.io.IOException;

import game.Objects.Tiles.WrongArrayException;




/**
 *
 * @author Mark Drozd
 */


public class Main {
    public static void main(String[] args) {
        try {
            new GameApplication();
        } catch (WrongArrayException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WrongLevelFileException e) {
            e.printStackTrace();
        }
    }
}
