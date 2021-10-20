import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

/**
 * Runnable is an interface that is to be implemented 
 * by a class whose instances are intended to be executed by a thread
 * There are two ways to create a thread:
    By extending Thread class
    By implementing Runnable interface.
    Thread class provide constructors and methods to 
    create and perform operations on a thread.
    Thread class extends Object class and implements Runnable interface.
 */
public class GamePanel extends JPanel implements Runnable{
    static final int GAME_WIDTH = 1200;
    static final int GAME_HEIGHT = 767;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    GamePanel() {
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true); // if you press key panel will read them 
        this.addKeyListener(new ActionListener());
        this.setPreferredSize(SCREEN_SIZE);
        
        gameThread = new Thread(this);
        gameThread.start();
    }   
    public void newBall() {
        random = new Random();
        ball = new Ball(
            (GAME_WIDTH-BALL_DIAMETER)/2,
            random.nextInt((GAME_HEIGHT-BALL_DIAMETER)),
            BALL_DIAMETER,
            BALL_DIAMETER
            );
    }

    public void newPaddles() {
        paddle1 = new Paddle(
            0,
            (GAME_HEIGHT-PADDLE_HEIGHT)/2, 
            PADDLE_WIDTH, 
            PADDLE_HEIGHT,1
            );
        paddle2 = new Paddle(
            GAME_WIDTH-PADDLE_WIDTH,
            (GAME_HEIGHT-PADDLE_HEIGHT)/2, 
            PADDLE_WIDTH, 
            PADDLE_HEIGHT, 
            2
            );
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    // smoother
    public void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    // ensures paddles and ball stay within window boundaries
    public void checkCollision() {
        if(paddle1.y <= 0)
            paddle1.y = 0;
        if(paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle1.y= (GAME_HEIGHT - PADDLE_HEIGHT);
        if(paddle2.y <= 0)
            paddle2.y = 0;
        if(paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle2.y= (GAME_HEIGHT - PADDLE_HEIGHT);
        if(ball.y <= 0)
            ball.setYDirection(-ball.yVelocity);
        if(ball.y >= (GAME_HEIGHT-BALL_DIAMETER)) // y coordinate is top left corner of the ball
            ball.setYDirection(-ball.yVelocity);
        
        // bounces ball of paddles
        if(ball.intersects(paddle1)) {
            ball.xVelocity *= -1; // make it positive to set ball direction to paddle2
            if (ball.xVelocity < 6)
                ball.xVelocity ++ ; // increases speed of game
            if (ball.yVelocity > 0)
                ball.yVelocity = ball.xVelocity;
            else
                ball.yVelocity = -ball.xVelocity;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(paddle2)) {
            ball.xVelocity *= (-1); // change sign to set ball direction to paddle1
            if (ball.xVelocity > -6)
                ball.xVelocity -- ; // increases speed of game
            if (ball.yVelocity > 0)
                ball.yVelocity = - ball.xVelocity;
            else
                ball.yVelocity = ball.xVelocity;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.x <= 0) {
            score.player2++;
            newPaddles();
            newBall();
        }
        if(ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            score.player1++;
            newPaddles();
            newBall();
        }
    } 

    public void run() {
        // game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if (delta >= 1) {
                move(); // calls move method of panel
                checkCollision();
                repaint();
                delta--;
            }
        }

    }

    public class ActionListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);

        }
        
    }

}
