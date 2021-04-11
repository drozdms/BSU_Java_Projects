package game.Objects.Tiles;

import game.Point;

import static game.textures.Tiles.TRACK_TILE;

public class TrackTile extends Tile {
    public TrackTile(Point position) {
        super(position);
        image = resizeImage(TRACK_TILE);
    }
}
