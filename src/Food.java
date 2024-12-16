import java.awt.*;

public class Food extends Cell {
    public static int foodNumber = 0;

    Food() {
        foodNumber++;
    }

    Food(int position_x, int position_y) {
        super(position_x, position_y, StdDraw.YELLOW);
        foodNumber++;
    }

    public void delete() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(position_x, position_y);
    }

    @Override
    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(position_x, position_y);
    }

    public void eaten() {
        delete();
        System.out.println("Eaten " + foodNumber);
        foodNumber--;
    }

}
