package game.textures;

import game.Objects.Tanks.PlayerTank;
import game.Objects.Tanks.StandardTank;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tanks {
    public static BufferedImage PLAYER_TANK_1 = null;
    public static BufferedImage PLAYER_TANK_2 = null;
    public static BufferedImage PLAYER_TANK_3 = null;
    public static BufferedImage PLAYER_TANK_4 = null;
    
    
    public static BufferedImage ENEMY_TANK_1 = null;
    public static BufferedImage ENEMY_TANK_2 = null;
    public static BufferedImage ENEMY_TANK_3 = null;
    public static BufferedImage ENEMY_TANK_4 = null;
    
    
    
    

    public static BufferedImage ENEMY_WEAK_TANK = null;
    public static BufferedImage ENEMY_FAST_TANK = null;
    public static BufferedImage ENEMY_STANDARD_TANK = null;
    public static BufferedImage ENEMY_MASSIVE_TANK = null;

    public static BufferedImage SHELL = null;

    public static BufferedImage TANKS_LEFT = null;

    static {
        try {
            PLAYER_TANK_1 = loadImage("footage/player_tank/player_tank_1.png");
            PLAYER_TANK_2 = loadImage("footage/player_tank/player_tank_2.png");
            PLAYER_TANK_3 = loadImage("footage/player_tank/player_tank_3.png");
            PLAYER_TANK_4 = loadImage("footage/player_tank/player_tank_4.png");

            ENEMY_WEAK_TANK = loadImage("footage/enemy tanks/weak.png");
            ENEMY_FAST_TANK = loadImage("footage/enemy tanks/fast.png");
            ENEMY_STANDARD_TANK = loadImage("footage/enemy tanks/standard.png");
            ENEMY_MASSIVE_TANK = loadImage("footage/enemy tanks/massive.png");
            
            
            ENEMY_TANK_1 = loadImage("footage/enemy_tank/enemy_tank_1.png");
            ENEMY_TANK_2 = loadImage("footage/enemy_tank/enemy_tank_2.png");
            ENEMY_TANK_3 = loadImage("footage/enemy_tank/enemy_tank_3.png");
            ENEMY_TANK_4 = loadImage("footage/enemy_tank/enemy_tank_4.png");
            
            
            

            SHELL = loadImage("footage/bullet.png");

            TANKS_LEFT = loadImage("footage/tanks left.png");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        
        
    }

    private static BufferedImage loadImage(String imageName) throws IOException {
        return ImageIO.read(new File(imageName));
    }
}
