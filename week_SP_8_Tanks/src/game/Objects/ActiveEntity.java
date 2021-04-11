package game.Objects;

import game.Point;
import game.WrongLevelFileException;
import game.Objects.Tiles.Tile;
import game.Objects.Tiles.WrongArrayException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;

public abstract class ActiveEntity extends Entity {
    public enum Direction {
        LEFT,
        UP,
        RIGHT,
        DOWN
    }

    protected static final int[] DELTA_X = {-1, 0, 1, 0};
    protected static final int[] DELTA_Y = {0, -1, 0, 1};
    protected int state = 0;

    protected BufferedImage[][] rotatedImages = new BufferedImage[4][4];

    protected Direction direction;
    protected double speed;
    protected HashSet<Tile> neighbourTiles = new HashSet<>();

    public static boolean doSquaresIntersect(Point sq1, double length1, Point sq2, double length2) {
        return doSegmentsIntersect(sq1.x, sq1.x + length1, sq2.x, sq2.x + length2) &&
                doSegmentsIntersect(sq1.y, sq1.y + length1, sq2.y, sq2.y + length2);
    }

    private static boolean doSegmentsIntersect(double x1, double x2, double y1, double y2) {
        return isPointInSegment(x1, y1, y2) || isPointInSegment(x2, y1, y2) ||
                isPointInSegment(y1, x1, x2) || isPointInSegment(y2, x1, x2);
    }

    private static boolean isPointInSegment(double x, double x1, double x2) {
        return x >= x1 && x <= x2;
    }

    protected static BufferedImage rotateTank(BufferedImage rawImage, Direction direction) {
        if (rawImage == null)
            return null;
        int width = rawImage.getWidth();
        int height = rawImage.getHeight();

        BufferedImage result = new BufferedImage(width, height, rawImage.getType());
        Graphics2D g2 = result.createGraphics();
        g2.rotate(Math.toRadians(90 * direction.ordinal()-90), width / 2, height / 2);
        g2.drawImage(rawImage, null, 0, 0);
        g2.dispose();

        return result;
    }

    public ActiveEntity(Point position) {
        super(position);
    }

    public abstract void update() throws WrongLevelFileException, IOException, WrongArrayException;

    protected abstract void checkTanks() throws WrongArrayException, IOException, WrongLevelFileException;
    protected abstract void checkTiles();
    protected abstract void checkDestructibleTiles(Tile tile);
    protected abstract void checkWindowBorders();

    protected void initRotatedImages(BufferedImage initImage1, BufferedImage initImage2, BufferedImage initImage3, BufferedImage initImage4) {
        BufferedImage[] cur = {initImage1, initImage2, initImage3, initImage4};
        for (int i=0; i < 4; ++i)
            for (int j=0; j < 4; ++j)
                rotatedImages[i][j]=rotateTank(cur[i], Direction.values()[j]);
    }

    public void setDirection(int direction) {
        setDirection(Direction.values()[direction]);
    }

    protected void setDirection(Direction direction) {
        this.direction = direction;
        image = rotatedImages[state / 4][direction.ordinal()];
    }

    public Direction getDirection() {
        return direction;
    }
}
