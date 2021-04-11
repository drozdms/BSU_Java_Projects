package game.Objects.Tiles;

import game.Point;
import game.Objects.Entity;

import java.awt.*;

public abstract class Tile extends Entity {
    Tile(Point position) {
        super(position);
    }

    public void display(Graphics2D g2) {
        g2.drawImage(image, null, (int) position.x, (int) position.y);
    }
}
