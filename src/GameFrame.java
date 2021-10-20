import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame{
    
    private static final Color BOARD_COLOR = new Color(211,211,211);

    GamePanel panel;

    GameFrame() {
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(BOARD_COLOR);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); // fits Game Panel into the Game Frame, so we do not need to set sizes of Game Frame.
        this.setVisible(true);
        this.setLocationRelativeTo(null); // centers GameFrame to open middle of screen. 
    } 

}
