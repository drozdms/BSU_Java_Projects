package game.Objects.Tiles;

import game.Point;

import static game.textures.Tiles.SMALL_BRICK_TILE;

public class BrickTile extends DestructibleTile {
    public BrickTile(Point position, int[] shapeOfTile) throws WrongArrayException {
        super(position, shapeOfTile);
        image = resizeImage(SMALL_BRICK_TILE, TILE_SIZE / 2, TILE_SIZE / 2);
    }
}
