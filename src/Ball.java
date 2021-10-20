import java.awt.*;
import java.util.*;

public class Ball extends Rectangle {

    Random random;
    int xVelocity;
    int yVelocity;
    int initSpeed = 2;
    private static final Color BALL_COLOR = Color.black;

    Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();
        int randomXDirection = random.nextInt(2); // 0 (left) or 1 (right)
        if(randomXDirection == 0)
            randomXDirection--;
        setXDirection(randomXDirection*initSpeed);
        int randomYDirection = random.nextInt(2); // 0 (left) or 1 (right)
        if(randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection*initSpeed);
    }

    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }

    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;

    }

    public void draw(Graphics g) {
        g.setColor(BALL_COLOR);
        g.fillOval(x, y, width, height); // gives a circle for the ball, instead of rectangle
    }
}
