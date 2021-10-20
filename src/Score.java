import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score extends Rectangle{

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;
    int player2;
    Color SCORE_COLOR = Color.black;
    
    Score(int GAME_WIDTH, int GAME_HEIGHT) {
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    } 

    public void draw(Graphics g) {
        g.setFont(new Font("Consolas",Font.PLAIN,60));
        g.setColor(SCORE_COLOR);
        g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);

        String p1 = String.valueOf(player1/10) + String.valueOf(player1%10);
        g.drawString(p1, GAME_WIDTH/2 - 85, 50);
        String p2 = String.valueOf(player2/10) + String.valueOf(player2%10);
        g.drawString(p2, GAME_WIDTH/2 + 20, 50);
    }

}
