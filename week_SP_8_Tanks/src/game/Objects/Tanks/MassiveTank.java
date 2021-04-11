package game.Objects.Tanks;

import game.Point;

import static game.textures.Tanks.ENEMY_MASSIVE_TANK;

import static game.textures.Tanks.ENEMY_TANK_1;
import static game.textures.Tanks.ENEMY_TANK_2;
import static game.textures.Tanks.ENEMY_TANK_3;
import static game.textures.Tanks.ENEMY_TANK_4;

public class MassiveTank extends EnemyTank {
    public MassiveTank(Point position) {
        super(position);
        initRotatedImages(resizeImage(ENEMY_TANK_1), resizeImage(ENEMY_TANK_2), resizeImage(ENEMY_TANK_3), resizeImage(ENEMY_TANK_4));
        setDirection(Direction.DOWN);
        speed = 1.0;
    }
}
