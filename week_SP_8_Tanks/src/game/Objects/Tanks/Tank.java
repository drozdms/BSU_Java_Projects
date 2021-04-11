package game.Objects.Tanks;

import game.Game;
import game.Point;
import game.Objects.ActiveEntity;
import game.Objects.Tiles.DestructibleTile;
import game.Objects.Tiles.Tile;
import game.Objects.Tiles.TrackTile;
import game.Objects.Tiles.WaterTile;

import java.awt.*;
import java.util.ArrayList;

public abstract class Tank extends ActiveEntity {
    private static final double DELTA = 0.08 * TILE_SIZE;
    

    boolean isOnTrack;
    private Point prevPosition;
    protected int shotDelay;
    protected int Nn = game.GamePanel.N-1;

    Tank(Point position) {
        super(position);
        
    }

    @Override
    public void display(Graphics2D g2) {
        g2.drawImage(image, null, (int) position.x, (int) position.y);
    }

    @Override
    public void update() {
        prevPosition = position;
        position = new Point(position.x + speed * DELTA_X[direction.ordinal()],
                position.y + speed * DELTA_Y[direction.ordinal()]);

        checkTanks();
        checkTiles();
        checkWindowBorders();
        
        
        
        if (state != 0)
        {
            state++;
            if (state > 15)
                state = 0;
        }
        
        
        setDirection(direction);
    }

    @Override
    protected void checkTanks() {
        PlayerTank playerTank = Game.getPlayerTank();
        ArrayList<EnemyTank> currentEnemyTanks = Game.getCurrentEnemyTanks();

        if (!(this instanceof PlayerTank) && doSquaresIntersect(position, TILE_SIZE - DELTA,
                                                                playerTank.getPosition(), TILE_SIZE - DELTA)) {
            position = prevPosition;
            return;
        }

        for (EnemyTank enemyTank : currentEnemyTanks) {
            if (!(enemyTank.equals(this)) && doSquaresIntersect(position, TILE_SIZE - DELTA,
                                                                enemyTank.position, TILE_SIZE - DELTA)) {
                position = prevPosition;
                return;
            }
        }
    }

    @Override
    protected void checkTiles() {
        Tile[][] gameField = Game.getGameField();

        neighbourTiles.clear();
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                int row = (int) (position.x + ((j == 1) ? TILE_SIZE - DELTA : DELTA)) / TILE_SIZE;
                int col = (int) (position.y + ((i == 1) ? TILE_SIZE - DELTA : DELTA)) / TILE_SIZE;
                neighbourTiles.add(gameField[row][col]);
            }
        }

        isOnTrack = false;

        for (Tile tile : neighbourTiles) {
            checkDestructibleTiles(tile);
            checkWaterTiles(tile);
            checkTrackTiles(tile);
        }
    }

    @Override
    protected void checkDestructibleTiles(Tile tile) {
        if (tile instanceof DestructibleTile) {
            int[][] shapeOfTile = ((DestructibleTile) tile).getShapeOfTile();
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < 2; ++j) {
                    if (shapeOfTile[i][j] == 1) {
                        Point smallTilePosition = new Point(tile.getPosition().x + j * TILE_SIZE / 2,
                                                              tile.getPosition().y + i * TILE_SIZE / 2);
                        if (doSquaresIntersect(smallTilePosition, TILE_SIZE / 2,
                                               position, TILE_SIZE - DELTA)) {
                            position = prevPosition;
                            return;
                        }
                    }
                }
            }
        }
    }

    private void checkWaterTiles(Tile tile) {
        if (tile instanceof WaterTile)
            position = prevPosition;
    }

    private void checkTrackTiles(Tile tile) {
        if (tile instanceof TrackTile)
            isOnTrack = true;
    }

    @Override
    protected void checkWindowBorders() {
        if (position.x <= 0)
            position.x = 0;
        else if (position.x >= TILE_SIZE * Nn)
            position.x = TILE_SIZE * Nn;

        if (position.y <= 0)
            position.y = 0;
        else if (position.y >= TILE_SIZE * Nn)
            position.y = TILE_SIZE * Nn;
    }
    
    
    
    public void stateFired()
    {
        state=1;
    }
}