import java.awt.*;

public class Monster extends GameCharacter {
    private final double radius = 0.4;

    Monster() {}

    Monster(double position_x, double position_y, int velocity, char direction, Color color) {
        super(position_x, position_y, velocity, direction, color);
    }

    @Override
    public void removeFormer() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledRectangle(position_x, position_y, radius+0.1, radius+0.1);
    }

    @Override
    protected void draw(double start_x, double start_y) {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(start_x, start_y, radius);
        StdDraw.filledRectangle(start_x, start_y-0.2, 0.4, 0.25);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(start_x-0.15, start_y);
        StdDraw.point(start_x+0.15, start_y);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.point(start_x-0.15, start_y);
        StdDraw.point(start_x+0.15, start_y);
    }

    @Override
    protected void move() {
        removeFormer();
        switch (direction) {
            case 'L':
                position_x -= velocity;  // Move left
                break;
            case 'R':
                position_x += velocity;  // Move right
                break;
            case 'U':
                position_y += velocity;  // Move up
                break;
            case 'D':
                position_y -= velocity;  // Move down
                break;
            default:
                break;  // No movement if direction is invalid
        }
        setDirection(direction);
        draw(position_x, position_y);
    }

    @Override
    protected void setDirection(char direction) {
        this.direction = direction;
    }
}
