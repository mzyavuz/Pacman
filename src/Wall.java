import java.awt.*;

public class Wall extends Cell{

    Wall() {}

    Wall(int position_x, int position_y) {
        super(position_x, position_y, StdDraw.BOOK_BLUE);
    }

    @Override
    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledRectangle(position_x,position_y, 0.509,0.509);
    }

    public int getPosition_x() {
        return position_x;
    }

    public int getPosition_y() {
        return position_y;
    }
}
