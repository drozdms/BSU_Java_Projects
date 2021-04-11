package game.Objects.Tiles;

import game.Point;


import static game.textures.Tiles.SOIL_TILE;

public class SoilTile extends Tile {
    public SoilTile(Point position) {
        super(position);
        image = resizeImage(SOIL_TILE);
    }

}
