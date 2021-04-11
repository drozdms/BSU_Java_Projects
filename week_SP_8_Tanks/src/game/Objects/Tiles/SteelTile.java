package game.Objects.Tiles;


import game.Point;

import static game.textures.Tiles.SMALL_STEEL_TILE;

public class SteelTile extends DestructibleTile {
    public SteelTile(Point position, int[] shapeOfTile) throws WrongArrayException {
        super(position, shapeOfTile);
        image = resizeImage(SMALL_STEEL_TILE, TILE_SIZE / 2, TILE_SIZE / 2);
    }
}
