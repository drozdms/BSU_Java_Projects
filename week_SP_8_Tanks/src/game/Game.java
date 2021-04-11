package game;

import game.Objects.Shell;
import game.Objects.ActiveEntity;
import game.Objects.Tanks.*;
import game.Objects.Tiles.*;
import game.Sounds.Sounds;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static game.Objects.ActiveEntity.doSquaresIntersect;
import static game.Objects.Entity.TILE_SIZE;
import static game.textures.Tanks.TANKS_LEFT;


import static game.GamePanel.N;
import game.Objects.Entity;
import game.Objects.Explosion;
import game.textures.Tiles;
import java.awt.image.BufferedImage;

public class Game {
    public enum EnemyTanks {
        WEAK,
        FAST,
        STANDARD,
        MASSIVE
    }

    private static final int GAMEFIELD_SIZE = TILE_SIZE * N;
    private static final int LEVEL_LIMIT = 4;
    private static BufferedImage background = Entity.resizeImage(Tiles.SOIL_MAP, TILE_SIZE*N, TILE_SIZE*N);

    private static Tile[][] gameField = new Tile[N][N];
    private static int levelNumber = 1;
    private static int score = 0;
    private static int highScore = 0;
    
    
  //  private static Color Transparent_WHITE=new Color();

    private static PlayerTank playerTank = new PlayerTank(new Point((N/2) * TILE_SIZE, (N-1) * TILE_SIZE));

    private static ArrayList<EnemyTank> enemyTanks = new ArrayList<>();
    private static HashSet<EnemyTank> enemyTanksRemoveList = new HashSet<>();
    private static ArrayList<EnemyTank> currentEnemyTanks = new ArrayList<>();
    private static ArrayList<EnemyTank> waitingEnemyTanks = new ArrayList<>();
    private static ArrayList<Explosion> explosions = new ArrayList<>();
    private static HashSet<Explosion> explosionsRemoveList = new HashSet<>();

    private static ArrayList<Shell> bullets = new ArrayList<>();
    private static HashSet<Shell> bulletsRemoveList = new HashSet<>();

    private static int pauseDelay = 0;

    static {
        try {
            loadLevel();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WrongLevelFileException e) {
            e.printStackTrace();
        } catch (WrongArrayException e) {
            e.printStackTrace();
        }
    }

    public static PlayerTank getPlayerTank() {
        return playerTank;
    }

    public static ArrayList<EnemyTank> getCurrentEnemyTanks() {
        return currentEnemyTanks;
    }
    
    public static Tile[][] getGameField() {
        return gameField;
    }

    public static ArrayList<Shell> getBullets() {
        return bullets;
    }

    private static void loadLevel() throws IOException, WrongLevelFileException, WrongArrayException {
        playerTank.setPosition(new Point((N/2) * TILE_SIZE, (N-1) * TILE_SIZE));

        Scanner sc = new Scanner(new File("levels/" + String.valueOf(levelNumber) + ".txt"));

        int spawn = 0;
        for (int i = 0; i < 4; ++i) {
            EnemyTanks type = EnemyTanks.values()[i];

            if (!sc.hasNext())
                throw new WrongLevelFileException();

            int numberOfTanks = sc.nextInt();
            while (--numberOfTanks >= 0) {
                addEnemyTank(type, spawn);
                spawn = (spawn + 6) % 18;
            }
        }
        Collections.shuffle(enemyTanks);
        initFirstTanks();

        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j) {
                if (!sc.hasNext())
                    throw new WrongLevelFileException();

                String tile = sc.next();
                switch(tile) {
                    case "T":
                        gameField[j][i] = new TrackTile(new Point(j * TILE_SIZE, i * TILE_SIZE));
                        break;

                    case "E":
                        gameField[j][i] = new SoilTile(new Point(j * TILE_SIZE, i * TILE_SIZE));
                        break;

                    case "G":
                        gameField[j][i] = new GrassTile(new Point(j * TILE_SIZE, i * TILE_SIZE));
                        break;

                    case "W":
                        gameField[j][i] = new WaterTile(new Point(j * TILE_SIZE, i * TILE_SIZE));
                        break;

                    case "B":
                        gameField[j][i] = new BrickTile(new Point(j * TILE_SIZE, i * TILE_SIZE), new int[] {1, 1, 1, 1});
                        break;

                    case "BD":
                        gameField[j][i] = new BrickTile(new Point(j * TILE_SIZE, i * TILE_SIZE), new int[] {0, 0, 1, 1});
                        break;

                    case "BU":
                        gameField[j][i] = new BrickTile(new Point(j * TILE_SIZE, i * TILE_SIZE), new int[] {1, 1, 0, 0});
                        break;

                    case "BR":
                        gameField[j][i] = new BrickTile(new Point(j * TILE_SIZE, i * TILE_SIZE), new int[] {0, 1, 0, 1});
                        break;

                    case "BL":
                        gameField[j][i] = new BrickTile(new Point(j * TILE_SIZE, i * TILE_SIZE), new int[] {1, 0, 1, 0});
                        break;

                    case "S":
                        gameField[j][i] = new SteelTile(new Point(j * TILE_SIZE, i * TILE_SIZE), new int[] {1, 1, 1, 1});
                        break;

                    case "SD":
                        gameField[j][i] = new SteelTile(new Point(j * TILE_SIZE, i * TILE_SIZE), new int[] {0, 0, 1, 1});
                        break;

                    case "SU":
                        gameField[j][i] = new SteelTile(new Point(j * TILE_SIZE, i * TILE_SIZE), new int[] {1, 1, 0, 0});
                        break;

                    case "SR":
                        gameField[j][i] = new SteelTile(new Point(j * TILE_SIZE, i * TILE_SIZE), new int[] {0, 1, 0, 1});
                        break;

                    case "SL":
                        gameField[j][i] = new SteelTile(new Point(j * TILE_SIZE, i * TILE_SIZE), new int[] {1, 0, 1, 0});
                        break;

                }
            }
        
        
        
        Controller.newLevel();

        Sounds.levelBeginning.play();
        //Sounds.mainTheme.play();
    }

    static void display(Graphics2D g2) {
        
        
        
        displayGameField(g2);
        displayInfo(g2);

        if (Controller.isGamePaused()) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
            g2.setColor(Color.WHITE);
                g2.fillRect(0, 0, TILE_SIZE*N, TILE_SIZE*N);
            if (pauseDelay <= 100) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.f));
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Hobbiton", Font.PLAIN, TILE_SIZE*3));
            g2.drawString("PAUSE", (int) (TILE_SIZE * N/ 2-TILE_SIZE*2*2), TILE_SIZE * (N/2));
            }
            pauseDelay = (++pauseDelay) % 200;
        }
        
        else if (Controller.isNewLevel())
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
            g2.setColor(Color.WHITE);
                g2.fillRect(0, 0, TILE_SIZE*N, TILE_SIZE*N);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.f));
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Hobbiton", Font.PLAIN, TILE_SIZE));
            g2.drawString("Press 'S' to continue", (int) (TILE_SIZE*2), TILE_SIZE * (N/2));
        }
    }

    private static void displayGameField(Graphics2D g2) {
        g2.drawImage(background, null, 0, 0);
        
        for (Tile[] row : gameField)
            for (Tile tile : row)
                if (!(tile instanceof GrassTile))
                    tile.display(g2);

        currentEnemyTanks.forEach((enemyTank) -> {
            enemyTank.display(g2);
        });

        playerTank.display(g2);

        bullets.forEach((bullet) -> {
            bullet.display(g2);
        });
        
        explosions.forEach((explosion) -> {
            explosion.display(g2);
        });

        for (Tile[] row : gameField)
            for (Tile tile : row)
                if (tile instanceof GrassTile)
                    tile.display(g2);
    }

    private static void displayInfo(Graphics2D g2) {
        g2.setColor(Color.GRAY);
        g2.fillRect(TILE_SIZE * N, 0, 150, GAMEFIELD_SIZE);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Press start", Font.PLAIN, 12));
        g2.drawString("TANKS LEFT:", GAMEFIELD_SIZE + 10, 20);

        for (int i = 0; i < enemyTanks.size() + waitingEnemyTanks.size(); ++i)
            g2.drawImage(TANKS_LEFT, null, GAMEFIELD_SIZE + 12 + 30 * (i % 4), 30 + 30 * (i / 4));

        g2.drawString("LIVES: " + playerTank.getLives(), GAMEFIELD_SIZE + 10, 520);
        g2.drawString("SCORE: " + score, GAMEFIELD_SIZE + 10, 540);
        g2.drawString("HISCORE:", GAMEFIELD_SIZE + 10, 565);
        g2.drawString(String.valueOf(highScore), GAMEFIELD_SIZE + 10, 585);
        g2.drawString("STAGE " + levelNumber, GAMEFIELD_SIZE + 10, 610);
    }

    static void update() throws WrongLevelFileException, IOException, WrongArrayException {
        if (Controller.isGamePaused() || Controller.isNewLevel()) {
            //Sounds.mainTheme.setVolume(0);
            return;
        }

        //Sounds.mainTheme.setVolume(1);

        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j)
                if (gameField[i][j] instanceof DestructibleTile && ((DestructibleTile) gameField[i][j]).isDestroyed())
                    gameField[i][j] = new SoilTile(gameField[i][j].getPosition());

        for (Shell bullet : bullets)
            bullet.update();

        currentEnemyTanks.forEach((enemyTank) -> {
            enemyTank.update();
        });
        
        explosions.stream().filter((explosion) -> (!explosion.update())).forEachOrdered((explosion) -> {
            explosionsRemoveList.add(explosion);
        });

        playerTank.update();

        if (!bulletsRemoveList.isEmpty())
            removeFromList(bullets, bulletsRemoveList);
        if (!enemyTanksRemoveList.isEmpty() || !waitingEnemyTanks.isEmpty()) {
            setNewTanks();
            removeFromList(currentEnemyTanks, enemyTanksRemoveList);
        }
        
        if (!explosionsRemoveList.isEmpty())
        {
            explosionsRemoveList.forEach((explosion) -> {
                explosions.remove(explosion);
            });
            explosionsRemoveList.clear();
        }

        if (playerTank.getLives() < 0)
            gameOver();
        else
            checkForWin();
    }

    private static void checkForWin() throws WrongArrayException, IOException, WrongLevelFileException {
        if (enemyTanks.isEmpty() && currentEnemyTanks.isEmpty() && waitingEnemyTanks.isEmpty()) {
            bullets.clear();

            levelNumber = (levelNumber == LEVEL_LIMIT) ? 1 : ++levelNumber;

            loadLevel();
        }
    }

    private static void gameOver() throws WrongArrayException, IOException, WrongLevelFileException {
        enemyTanks.clear();
        enemyTanksRemoveList.clear();
        currentEnemyTanks.clear();
        waitingEnemyTanks.clear();

        bullets.clear();
        bulletsRemoveList.clear();

        score = 0;
        levelNumber = 1;
        playerTank.setLives(3);
        loadLevel();
    }

    public static void increaseScore(EnemyTank enemyTank) {
        if (enemyTank instanceof WeakTank)
            score += 100;
        else if (enemyTank instanceof FastTank || enemyTank instanceof StandardTank)
            score += 200;
        else
            score += 300;

        if (score > highScore)
            highScore = score;
    }

    private static void initFirstTanks() {
        boolean spawns[] = new boolean[3];
        Iterator<EnemyTank> it = enemyTanks.iterator();
        while (it.hasNext()) {
            EnemyTank enemyTank = it.next();
            int spawn = (int) (enemyTank.getPosition().x / TILE_SIZE / 6);
            if (!spawns[spawn]) {
                currentEnemyTanks.add(enemyTank);
                spawns[spawn] = true;
                it.remove();
            }
        }
    }

    private static void addEnemyTank(EnemyTanks tankType, int spawn) {
        switch (tankType) {
            case WEAK:
                enemyTanks.add(new WeakTank(new Point(TILE_SIZE * spawn, 0)));
                break;

            case FAST:
                enemyTanks.add(new FastTank(new Point(TILE_SIZE * spawn, 0)));
                break;

            case STANDARD:
                enemyTanks.add(new StandardTank(new Point(TILE_SIZE * spawn, 0)));
                break;

            case MASSIVE:
                enemyTanks.add(new MassiveTank(new Point(TILE_SIZE * spawn, 0)));
                break;
        }
    }

    public static void addBullet(Tank owner) {
        bullets.add(new Shell(owner));
    }
    
    public static void addExplosion(Point position, int ind) {
        explosions.add(new Explosion(position, ind));
    }

    public static void addToRemoveList(ActiveEntity dynamicObject) {
        if (dynamicObject instanceof Shell)
            bulletsRemoveList.add((Shell) dynamicObject);
        else if (dynamicObject instanceof EnemyTank)
            enemyTanksRemoveList.add((EnemyTank) dynamicObject);
    }

    private static void setNewTanks() {
        Iterator<EnemyTank> it = waitingEnemyTanks.iterator();
        while (it.hasNext()) {
            EnemyTank enemyTank = it.next();
            if (isSpawnAccessible(enemyTank)) {
                currentEnemyTanks.add(enemyTank);
                it.remove();
            }
        }

        for (int i = 0; i < enemyTanksRemoveList.size() && !enemyTanks.isEmpty(); ++i) {
            Random random = new Random();
            int numberOfTanks = (enemyTanks.size() > 1) ? random.nextInt(2) : 0;
            for (int j = numberOfTanks; j >= 0; --j) {
                EnemyTank newTank = enemyTanks.get(j);
                if (isSpawnAccessible(newTank))
                    currentEnemyTanks.add(newTank);
                else
                    waitingEnemyTanks.add(newTank);

                enemyTanks.remove(newTank);
            }
        }
    }

    private static boolean isSpawnAccessible(EnemyTank newTank) {
        if (doSquaresIntersect(newTank.getPosition(), TILE_SIZE, playerTank.getPosition(), TILE_SIZE))
            return false;

        for (EnemyTank enemyTank : currentEnemyTanks)
            if (doSquaresIntersect(newTank.getPosition(), TILE_SIZE, enemyTank.getPosition(), TILE_SIZE))
                return false;

        return true;
    }

    private static void removeFromList(ArrayList<? extends ActiveEntity> list, HashSet<? extends ActiveEntity> removeList) {
        removeList.forEach((object) -> {
            list.remove(object);
        });
        removeList.clear();
    }
}
