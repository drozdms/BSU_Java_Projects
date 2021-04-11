package game.Objects;

import game.Point;
import game.textures.Tiles;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public static final int TILE_SIZE = 48;

    protected BufferedImage image;
    protected Point position;

    public Entity(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public abstract void display(Graphics2D g2);

    public static BufferedImage resizeImage(BufferedImage rawImage) {
        return resizeImage(rawImage, TILE_SIZE, TILE_SIZE);
    }

    public static BufferedImage resizeImage(BufferedImage rawImage, int width, int height) {
        Image tmp = rawImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    
    
    
    static {
        Tiles.EXPL_1=resizeImage(Tiles.EXPL_1, TILE_SIZE+50, TILE_SIZE+50);
        Tiles.EXPL_2=resizeImage(Tiles.EXPL_2, TILE_SIZE+50, TILE_SIZE+50);
        Tiles.EXPL_3=resizeImage(Tiles.EXPL_3, TILE_SIZE+50, TILE_SIZE+50);
        Tiles.EXPL_4=resizeImage(Tiles.EXPL_4, TILE_SIZE+50, TILE_SIZE+50);
        Tiles.EXPL_5=resizeImage(Tiles.EXPL_5, TILE_SIZE+50, TILE_SIZE+50);
        Tiles.EXPL_6=resizeImage(Tiles.EXPL_6, TILE_SIZE+50, TILE_SIZE+50);
        Tiles.EXPL_7=resizeImage(Tiles.EXPL_7, TILE_SIZE+50, TILE_SIZE+50);
        Tiles.EXPL_8=resizeImage(Tiles.EXPL_8, TILE_SIZE+50, TILE_SIZE+50);
        
        
    }
}
