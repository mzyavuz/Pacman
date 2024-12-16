import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MelikeZeynepCakmakci {
    private static Cell[][] cells = new Cell[31][];
    private static ArrayList<Monster> monsters = new ArrayList<>();

    public static void main(String[] args) {

        int x_pixel = 27, y_pixel = 30;
        int coefficient = 26;
        int width = x_pixel*coefficient, height = y_pixel*coefficient;
        StdDraw.setCanvasSize(width,height);
        StdDraw.setXscale(-1, x_pixel+1);
        StdDraw.setYscale(-1, y_pixel+1);
        StdDraw.clear(StdDraw.BLACK);

        File mapFile = new File("src/map.txt");
        try {
            Scanner mapScanner = new Scanner(mapFile);
            int lineNumber = y_pixel;
            while (mapScanner.hasNextLine()) {
                String line = mapScanner.nextLine();
                int maxLength = line.length();
                cells[lineNumber] = new Cell[x_pixel + 1];
                for (int i = 0; i < maxLength; i++) {
                    if (i == 0 && line.charAt(i) == '0') {
                        while (line.charAt(i) == '0') {
                            i++;
                            maxLength--;
                        }
                    }
                    if (line.charAt(i) == '0') {
                        Food newFood = new Food(i,lineNumber);
                        newFood.draw();
                        cells[lineNumber][i] = newFood;
                    } else if (line.charAt(i) == '1') {
                        Wall newWall = new Wall(i,lineNumber);
                        newWall.draw();
                        cells[lineNumber][i] = newWall;
                    }
                }
                lineNumber--;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Pacman pacman = new Pacman(x_pixel/2, 1, 1, '0', StdDraw.YELLOW);
        pacman.draw(pacman.position_x, pacman.position_y);
        if (cells[(int) pacman.position_y][(int) (pacman.position_x)] instanceof Food food) {
            food.eaten();
            cells[(int) pacman.position_y][(int) (pacman.position_x)] = null;
        }

        monsters.add(new Monster(x_pixel/2, y_pixel/2+1, 1, 'U', StdDraw.RED));
        monsters.add(new Monster(x_pixel/2, y_pixel/2+2, 1, 'U', StdDraw.WHITE));
        monsters.add(new Monster(x_pixel/2+1, y_pixel/2+2, 1, 'U', StdDraw.GRAY));
        monsters.add(new Monster(x_pixel/2+1, y_pixel/2+1, 1, 'U', StdDraw.PINK));
        for (Monster monster : monsters) {
            monster.draw(monster.position_x, monster.position_y);
        }

        StdDraw.enableDoubleBuffering();

        while (!foodFinish()) {
            setPacmanDirection(pacman);
            pacmanMove(pacman, x_pixel);
            for (Monster monster : monsters) {
                monsterMove(monster, x_pixel);
                if (pacmanDie(monster, pacman))
                    return;
            }
            StdDraw.pause(110);
            StdDraw.show();
        }

    }

    private static boolean pacmanDie(Monster monster, Pacman pacman) {
        if (monster.position_x == pacman.position_x && monster.position_y == pacman.position_y) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(20);
            StdDraw.filledRectangle(14, 15,10,10);
            System.out.println("GAME FINISHED! YOU LOST!!!");
            StdDraw.setPenRadius(10);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.text(14, 15, "YOU LOST!!!");
            StdDraw.show();
            return true;
        }
        return false;
    }

    private static boolean foodFinish() {
        if (Food.foodNumber == 0) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(20);
            StdDraw.filledRectangle(14, 15,10,10);
            System.out.println("GAME FINISHED! YOU WON!!!");
            StdDraw.setPenRadius(10);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.text(14, 15, "YOU WON!!!");
            StdDraw.show();
            return true;
        }
        return false;
    }

    private static void setPacmanDirection(Pacman pacman) {
        if (StdDraw.isKeyPressed(37)) {
            pacman.setDirection('L');
        }
        else if (StdDraw.isKeyPressed(38)) {
            pacman.setDirection('U');
        }
        else if (StdDraw.isKeyPressed(39)) {
            pacman.setDirection('R');
        }
        else if (StdDraw.isKeyPressed(40)) {
            pacman.setDirection('D');
        }
    }

    private static void pacmanMove(Pacman pacman, int x_pixel) {
        Cell nextCell;
        if (pacman.direction == 'L') {
            if (pacman.position_x - pacman.velocity < 0) {
                pacman.removeFormer();
                pacman.position_x = x_pixel+1;
                return;
            }
            nextCell = cells[(int) pacman.position_y][(int) (pacman.position_x - pacman.velocity)];
            if (nextCell instanceof Wall)
                return;
            else if (nextCell instanceof Food food) {
                food.eaten();
                cells[(int) pacman.position_y][(int) (pacman.position_x - pacman.velocity)] = null;
            }
        } else if (pacman.direction == 'R') {
            if (pacman.position_x + pacman.velocity > x_pixel) {
                pacman.removeFormer();
                pacman.position_x = -1;
                return;
            }
            nextCell = cells[(int) pacman.position_y][(int) (pacman.position_x + pacman.velocity)];
            if (nextCell instanceof Wall)
                return;
            else if (nextCell instanceof Food food) {
                food.eaten();
                cells[(int) pacman.position_y][(int) (pacman.position_x + pacman.velocity)] = null;
            }
        } else if (pacman.direction == 'U') {
            nextCell = cells[(int) (pacman.position_y + pacman.velocity)][(int) pacman.position_x];
            if (nextCell instanceof Wall)
                return;
            else if (nextCell instanceof Food food) {
                food.eaten();
                cells[(int) (pacman.position_y + pacman.velocity)][(int) pacman.position_x] = null;
            }
        } else if (pacman.direction == 'D') {
            nextCell = cells[(int) (pacman.position_y - pacman.velocity)][(int) pacman.position_x];
            if (nextCell instanceof Wall)
                return;
            else if (nextCell instanceof Food food) {
                food.eaten();
                cells[(int) (pacman.position_y - pacman.velocity)][(int) pacman.position_x] = null;
            }
        }
        pacman.move();
    }

    private static void monsterMove(Monster monster, int x_pixel) {
        ArrayList<Character> directions = new ArrayList<>();
        directions.add('L');
        directions.add('U');
        directions.add('R');
        directions.add('D');
        Random random = new Random();
        Cell nextCell;
        Cell currentCell = cells[(int) monster.position_y][(int) monster.position_x];
        if (monster.direction == 'L') {
            if (monster.position_x - monster.velocity < 0) {
                monster.removeFormer();
                monster.position_x = x_pixel;
            }
            nextCell = cells[(int) monster.position_y][(int) (monster.position_x - monster.velocity)];
            if (nextCell instanceof Wall) {
                directions.removeFirst();
                char newDirection = directions.get(random.nextInt(3));
                monster.setDirection(newDirection);
                return;
            }
        } else if (monster.direction == 'R') {
            if (monster.position_x + monster.velocity > x_pixel) {
                monster.removeFormer();
                monster.position_x = -1;
            }
            nextCell = cells[(int) monster.position_y][(int) (monster.position_x + monster.velocity)];
            if (nextCell instanceof Wall) {
                directions.remove(2);
                char newDirection = directions.get(random.nextInt(3));
                monster.setDirection(newDirection);
                return;
            }
        } else if (monster.direction == 'U') {
            nextCell = cells[(int) (monster.position_y + monster.velocity)][(int) monster.position_x];
            if (nextCell instanceof Wall) {
                directions.remove(1);
                char newDirection = directions.get(random.nextInt(3));
                monster.setDirection(newDirection);
                return;
            }
        } else if (monster.direction == 'D') {
            nextCell = cells[(int) (monster.position_y - monster.velocity)][(int) monster.position_x];
            if (nextCell instanceof Wall) {
                directions.remove(3);
                char newDirection = directions.get(random.nextInt(3));
                monster.setDirection(newDirection);
                return;
            }
        }
        monster.move();
        if (currentCell instanceof Food food) {
            food.draw();
        }
    }

}