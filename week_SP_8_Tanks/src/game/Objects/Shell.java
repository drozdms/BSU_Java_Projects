package game.Objects;

import game.Game;
import game.Point;
import game.WrongLevelFileException;
import game.Objects.Tanks.*;
import game.Objects.Tiles.*;
import game.Sounds.Sound;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static game.GamePanel.N;
import static game.textures.Tanks.SHELL;



public class Shell extends ActiveEntity {
    private static final int SHELL_SIZE = TILE_SIZE / 4;

    private boolean isEnemy;
    private boolean isDestroySteel;

    public Shell(Tank tank) {
        super(new Point(tank.getPosition().x + TILE_SIZE / 2 * (1 + DELTA_X[tank.getDirection().ordinal()]) - SHELL_SIZE / 2,
                         tank.getPosition().y + TILE_SIZE / 2 * (1 + DELTA_Y[tank.getDirection().ordinal()]) - SHELL_SIZE / 2));
        initRotatedImages(resizeImage(SHELL, SHELL_SIZE, SHELL_SIZE), null, null, null);
        setDirection(tank.getDirection());
        tank.stateFired();
        isEnemy = tank instanceof EnemyTank;
        speed = 2.0;
        isDestroySteel = tank instanceof PlayerTank && ((PlayerTank) tank).getLevel() > 2;
        Sound.playSound("sounds/shotFired.wav");
       
    }

    @Override
    public void display(Graphics2D g2) {
        g2.drawImage(image, null, (int) position.x, (int) position.y);
    }

    @Override
    public void update() throws WrongLevelFileException, IOException, WrongArrayException {
        position = new Point(position.x + speed * DELTA_X[direction.ordinal()],
                              position.y + speed * DELTA_Y[direction.ordinal()]);

        checkBullets();
        checkTanks();
        checkTiles();
        checkWindowBorders();
    }

    private void checkBullets() {
        ArrayList<Shell> bullets = Game.getBullets();

        for (Shell bullet : bullets) {
            if (!bullet.equals(this) && doSquaresIntersect(position, SHELL_SIZE, bullet.getPosition(), SHELL_SIZE)) {
                Game.addExplosion(position, 1);
                Game.addToRemoveList(this);
                Game.addToRemoveList(bullet);
                return;
            }
        }
    }

    @Override
    protected void checkTanks() throws WrongArrayException, IOException, WrongLevelFileException {
        if (isEnemy) {
            PlayerTank playerTank = Game.getPlayerTank();
            if (doSquaresIntersect(position, SHELL_SIZE, playerTank.getPosition(), TILE_SIZE)) {
                Game.addExplosion(playerTank.getPosition(), 0);
                playerTank.tryKill();
                Game.addToRemoveList(this);
                Sound.playSound("sounds/explosion.wav");
            }
        } else {
            ArrayList<EnemyTank> currentEnemyTanks = Game.getCurrentEnemyTanks();

            for (EnemyTank enemyTank : currentEnemyTanks) {
                if (doSquaresIntersect(enemyTank.getPosition(), TILE_SIZE, position, SHELL_SIZE)) {
                    Game.addToRemoveList(this);
                    Game.addToRemoveList(enemyTank);
                    Game.addExplosion(this.getPosition(), 0);
                    Sound.playSound("sounds/explosion.wav");

                    Game.increaseScore(enemyTank);
                    return;
                }
            }
        }
    }

    @Override
    protected void checkTiles() {
        Tile[][] gameField = Game.getGameField();

        neighbourTiles.clear();
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                int row = (int) (position.x + ((j == 1) ? SHELL_SIZE : 0)) / TILE_SIZE;
                int col = (int) (position.y + ((i == 1) ? SHELL_SIZE : 0)) / TILE_SIZE;
                if (row != N && col != N)
                    neighbourTiles.add(gameField[row][col]);
            }
        }

        for (Tile tile : neighbourTiles) {
            checkDestructibleTiles(tile);
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
                                position, SHELL_SIZE)) {
                            if (tile instanceof  BrickTile || tile instanceof SteelTile && isDestroySteel) {
                                shapeOfTile[i][j] = 0;
                                ((DestructibleTile) tile).setShapeOfTile(shapeOfTile);
                            }
                            Game.addToRemoveList(this);

                           // Sound.playSound("sounds/destroyed brick.mp3", 1.f);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void checkWindowBorders() {
        if (position.x <= 0 || position.x >= TILE_SIZE * N - SHELL_SIZE ||
                position.y <= 0 || position.y >= TILE_SIZE * N - SHELL_SIZE)
            Game.addToRemoveList(this);
    }
}
