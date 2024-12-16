import java.awt.*;

public abstract class GameCharacter {
    protected double position_x;
    protected double position_y;
    protected int velocity;
    protected char direction;
    protected Color color;

    protected GameCharacter() {}

    protected GameCharacter(double position_x, double position_y, int velocity, char direction, Color color) {
        this.position_x = position_x;
        this.position_y = position_y;
        this.velocity = velocity;
        this.direction = direction;
        this.color = color;
    }

    protected abstract void removeFormer();
    protected abstract void draw(double start_x, double start_y);
    protected abstract void move();
    protected abstract void setDirection(char direction);

}
