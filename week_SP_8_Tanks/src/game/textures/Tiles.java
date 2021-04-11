package game.textures;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tiles {
    public static BufferedImage GRASS_TILE = null;
    public static BufferedImage WATER_TILE = null;
    public static BufferedImage TRACK_TILE = null;
    public static BufferedImage BRICK_TILE = null;
    public static BufferedImage BRICK_DOWN_TILE = null;
    public static BufferedImage BRICK_UP_TILE = null;
    public static BufferedImage BRICK_LEFT_TILE = null;
    public static BufferedImage BRICK_RIGHT_TILE = null;
    public static BufferedImage SMALL_BRICK_TILE = null;
    public static BufferedImage STEEL_TILE = null;
    public static BufferedImage STEEL_DOWN_TILE = null;
    public static BufferedImage STEEL_UP_TILE = null;
    public static BufferedImage STEEL_LEFT_TILE = null;
    public static BufferedImage STEEL_RIGHT_TILE = null;
    public static BufferedImage SMALL_STEEL_TILE = null;
    public static BufferedImage SOIL_TILE = null;
    public static BufferedImage SOIL_MAP = null;
    
    public static BufferedImage EXPL_1 = null;
    public static BufferedImage EXPL_2 = null;
    public static BufferedImage EXPL_3 = null;
    public static BufferedImage EXPL_4 = null;
    public static BufferedImage EXPL_5 = null;
    public static BufferedImage EXPL_6 = null;
    public static BufferedImage EXPL_7 = null;
    public static BufferedImage EXPL_8 = null;

    static {
        try {
            GRASS_TILE = loadImage("footage/tiles/grass.png");
            WATER_TILE = loadImage("footage/tiles/water.png");
            TRACK_TILE = loadImage("footage/tiles/track.png");
            BRICK_TILE = loadImage("footage/tiles/bricks_tile.png");
            BRICK_DOWN_TILE = loadImage("footage/tiles/brick down.png");
            BRICK_UP_TILE = loadImage("footage/tiles/brick up.png");
            BRICK_LEFT_TILE = loadImage("footage/tiles/brick left.png");
            BRICK_RIGHT_TILE = loadImage("footage/tiles/brick right.png");
            SMALL_BRICK_TILE = loadImage("footage/tiles/bricks.png");
            STEEL_TILE = loadImage("footage/tiles/steel.png");
            STEEL_DOWN_TILE = loadImage("footage/tiles/steel down.png");
            STEEL_UP_TILE = loadImage("footage/tiles/steel up.png");
            STEEL_LEFT_TILE = loadImage("footage/tiles/steel left.png");
            STEEL_RIGHT_TILE = loadImage("footage/tiles/steel right.png");
            SMALL_STEEL_TILE = loadImage("footage/tiles/steel 4x4.png");
            SOIL_TILE = loadImage("footage/tiles/soil.png");
            SOIL_MAP = loadImage("footage/tiles/soil_map.png");
            
            
            EXPL_1 = loadImage("footage/explosion/expl_1.png");
            EXPL_2 = loadImage("footage/explosion/expl_2.png");
            EXPL_3 = loadImage("footage/explosion/expl_3.png");
            EXPL_4 = loadImage("footage/explosion/expl_4.png");
            EXPL_5 = loadImage("footage/explosion/expl_5.png");
            EXPL_6 = loadImage("footage/explosion/expl_6.png");
            EXPL_7 = loadImage("footage/explosion/expl_7.png");
            EXPL_8 = loadImage("footage/explosion/expl_8.png");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage loadImage(String imageName) throws IOException {
        return ImageIO.read(new File(imageName));
    }
}
