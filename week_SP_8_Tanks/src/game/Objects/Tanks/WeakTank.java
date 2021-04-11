package game.Objects.Tanks;

import static game.Objects.Entity.resizeImage;
import game.Point;
import static game.textures.Tanks.ENEMY_TANK_1;
import static game.textures.Tanks.ENEMY_TANK_2;
import static game.textures.Tanks.ENEMY_TANK_3;
import static game.textures.Tanks.ENEMY_TANK_4;

import static game.textures.Tanks.ENEMY_WEAK_TANK;

public class WeakTank extends EnemyTank {
    public WeakTank(Point position) {
        super(position);
        initRotatedImages(resizeImage(ENEMY_TANK_1), resizeImage(ENEMY_TANK_2), resizeImage(ENEMY_TANK_3), resizeImage(ENEMY_TANK_4));
        setDirection(Direction.DOWN);
        speed = 1.3;
    }
}
