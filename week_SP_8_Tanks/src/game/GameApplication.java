package game;

import game.Objects.Tiles.WrongArrayException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import static javax.swing.JFrame.EXIT_ON_CLOSE;


class GameApplication extends JFrame {
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 670;

    private GamePanel gamePanel = new GamePanel();

    GameApplication() throws WrongArrayException, IOException, WrongLevelFileException {
        setTitle("Tanks Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        add(gamePanel, BorderLayout.CENTER);

        pack();
        setResizable(false);
        setVisible(true);
    }
}
