package game.Objects.Tiles;

import game.Point;

import java.awt.*;

import static game.textures.Tiles.GRASS_TILE;

public class GrassTile extends Tile {
    public GrassTile(Point position) {
        super(position);
        image = resizeImage(GRASS_TILE);
    }
}
