package game.Objects.Tiles;

import java.awt.*;
import game.Point;

public abstract class DestructibleTile extends Tile {
    private int[][] shapeOfTile = new int[2][2];

    DestructibleTile(Point position, int[] shapeOfTile) throws WrongArrayException {
        super(position);
        if (shapeOfTile.length == 4) {
            for (int i = 0; i < 4; ++i) {
                this.shapeOfTile[i / 2][i % 2] = shapeOfTile[i];
            }
        }
        else
            throw new WrongArrayException();
    }

    public int[][] getShapeOfTile() {
        return shapeOfTile;
    }

    public void setShapeOfTile(int[][] shapeOfTile) {
        this.shapeOfTile = shapeOfTile;
    }

    public boolean isDestroyed() {
        for (int i = 0; i < 2; ++i)
            for (int j = 0; j < 2; ++j)
                if (shapeOfTile[i][j] == 1)
                    return false;

        return true;
    }

    @Override
    public void display(Graphics2D g2) {
        for (int i = 0; i < 2; ++i)
            for (int j = 0; j < 2; ++j)
                if (shapeOfTile[i][j] == 1)
                    g2.drawImage(image, null,
                             (int) position.x + j * TILE_SIZE / 2,
                             (int) position.y + i * TILE_SIZE / 2);
    }
}
