import java.awt.*;

public abstract class Cell {
    protected int position_x;
    protected int position_y;
    protected Color color;

    Cell() {}

    Cell(int position_x, int position_y, Color color) {
        this.position_x = position_x;
        this.position_y = position_y;
        this.color = color;
    }

    protected abstract void draw();
}
