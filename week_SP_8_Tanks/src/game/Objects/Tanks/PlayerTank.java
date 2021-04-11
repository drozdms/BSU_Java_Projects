package game.Objects.Tanks;

import game.Controller;
import game.Game;
import game.Point;
import game.WrongLevelFileException;
import game.Objects.Tiles.WrongArrayException;
import game.Sounds.Sounds;

import java.io.IOException;

import static game.textures.Tanks.PLAYER_TANK_1;
import static game.textures.Tanks.PLAYER_TANK_2;
import static game.textures.Tanks.PLAYER_TANK_3;
import static game.textures.Tanks.PLAYER_TANK_4;
import static game.GamePanel.N;
import java.awt.image.BufferedImage;

public class PlayerTank extends Tank {
    
    
    private int level;
    private int lives;

    public PlayerTank(Point position) {
        super(position);
        initRotatedImages(resizeImage(PLAYER_TANK_1), resizeImage(PLAYER_TANK_2), resizeImage(PLAYER_TANK_3), resizeImage(PLAYER_TANK_4));
        setDirection(Direction.UP);
        level = 1;
        speed = 1.6;
        lives = 3;
    }

    @Override
    public void update() {
        if (Controller.isKeyPressed() || isOnTrack)
            super.update();

        if (shotDelay > 0)
            shotDelay--;
        
        if (state != 0)
        {
            state++;
            if (state > 15)
                state = 0;
        }
        
        
        setDirection(direction);
        
    }

    public boolean isShotAllowed() {
        if (shotDelay == 0) {
            shotDelay = 50;
            return true;
        }
        return false;
    }

    public int getLevel() {
        return level;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public void tryKill() throws WrongLevelFileException, IOException, WrongArrayException {
        position = new Point(6 * TILE_SIZE, (N-1) * TILE_SIZE);
        --lives;
    }
    

    
    
    
   
}
