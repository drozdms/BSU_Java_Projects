package game.Objects;

import game.Point;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Mark Drozd
 */
public class Explosion extends Entity
{
    private int state;
    private int ind;
    public static BufferedImage[] expl={game.textures.Tiles.EXPL_1, game.textures.Tiles.EXPL_2, game.textures.Tiles.EXPL_3, game.textures.Tiles.EXPL_4,
    game.textures.Tiles.EXPL_5, game.textures.Tiles.EXPL_6, game.textures.Tiles.EXPL_7, game.textures.Tiles.EXPL_8};
    
    public static BufferedImage[] little_expl={resizeImage(game.textures.Tiles.EXPL_1, TILE_SIZE/2, TILE_SIZE/2), resizeImage(game.textures.Tiles.EXPL_2, TILE_SIZE/2, TILE_SIZE/2), resizeImage(game.textures.Tiles.EXPL_3, TILE_SIZE/2, TILE_SIZE/2), resizeImage(game.textures.Tiles.EXPL_4, TILE_SIZE/2, TILE_SIZE/2),
    resizeImage(game.textures.Tiles.EXPL_5, TILE_SIZE/2, TILE_SIZE/2), resizeImage(game.textures.Tiles.EXPL_6, TILE_SIZE/2, TILE_SIZE/2), resizeImage(game.textures.Tiles.EXPL_7, TILE_SIZE/2, TILE_SIZE/2), resizeImage(game.textures.Tiles.EXPL_8, TILE_SIZE/2, TILE_SIZE/2)};

    public Explosion(Point position, int ind)
    {
        super(position);
        state = 0;
        this.ind = ind;
    }
    
    @Override
    public void display(Graphics2D g2) {
        g2.drawImage(image, null, (int) position.x, (int) position.y);
    }
    
    
    public boolean update() {
        if (state > 31)
            return false;
        if (ind == 0)
            image = expl[state / 4];
        else image = little_expl[state / 4];
        state++;
        return true;
    
    }

}
