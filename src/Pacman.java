import java.awt.*;

public class Pacman extends GameCharacter {
    private final double radius = 0.49;
    private double[] mouthPointsX;
    private double[] mouthPointsY;


    Pacman(double position_x, double position_y, int velocity, char direction, Color color) {
        super(position_x, position_y, velocity, direction, color);
        mouthPointsX = new double[3];
        mouthPointsY = new double[3];
    }

    public void setMouthDirection() {
        switch (direction) {
            case 'L':
                mouthPointsX[0] = position_x - radius;
                mouthPointsX[1] = position_x + 0.2;
                mouthPointsX[2] = position_x - radius;
                mouthPointsY[0] = position_y + radius/2.4;
                mouthPointsY[1] = position_y;
                mouthPointsY[2] = position_y - radius/2.4;
                break;
            case 'R':
                mouthPointsX[0] = position_x + radius;
                mouthPointsX[1] = position_x - 0.2;
                mouthPointsX[2] = position_x + radius;
                mouthPointsY[0] = position_y + radius/2.4;
                mouthPointsY[1] = position_y;
                mouthPointsY[2] = position_y - radius/2.4;
                break;
            case 'U':
                mouthPointsX[0] = position_x + radius/2.4;
                mouthPointsX[1] = position_x;
                mouthPointsX[2] = position_x - radius/2.4;
                mouthPointsY[0] = position_y + radius;
                mouthPointsY[1] = position_y - 0.2;
                mouthPointsY[2] = position_y + radius;
                break;
            case 'D':
                mouthPointsX[0] = position_x + radius/2.4;
                mouthPointsX[1] = position_x;
                mouthPointsX[2] = position_x - radius/2.4;
                mouthPointsY[0] = position_y - radius;
                mouthPointsY[1] = position_y + 0.2;
                mouthPointsY[2] = position_y - radius;
        }
        draw(position_x, position_y);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledPolygon(mouthPointsX, mouthPointsY);
    }

    public void removeFormer() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(position_x, position_y, radius);
    }

    protected void draw(double start_x, double start_y) {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(start_x,start_y,radius-0.05);
    }

    @Override
    protected void setDirection(char direction) {
        this.direction = direction;
        setMouthDirection();
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
    }

}
