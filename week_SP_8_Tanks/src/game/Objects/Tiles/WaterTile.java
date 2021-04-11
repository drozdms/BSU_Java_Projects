package game.Objects.Tiles;

import game.Point;

import java.awt.*;

import static game.textures.Tiles.WATER_TILE;

public class WaterTile extends Tile {
    public WaterTile(Point position) {
        super(position);
        image = resizeImage(WATER_TILE);
    }
}
