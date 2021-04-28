import java.util.Scanner;

public class Point2d{
    private int x = 0;
    private int y = 0;
    private static Character keyPress;
    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Press W to move up\n" + "Press S to move down\n" + "Press A to move left\n" + "Press D to move right\n");
        keyPress = s.next().charAt(0);
        /*while (!keyPress.equals('q')) {
            if(keyPress.equals('w')){
                y += 1;
            }
            else if (keyPress.equals('a')) {
                x -= 1;
            }
            else if (keyPress.equals('s')) {
                y -= 1;
            }
            else if (keyPress.equals('d')) {
                x += 1;
            }
            System.out.println("(" + x + "," + y + ")");
        } */
        testAdd();
    }

    public int getIntX() {
        return x;
    }
    public int getIntY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y ){
        this.y = y;
    }

    public void Point2d(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Point2d add(Point2d other) {
        Point2d result = new Point2d();
        result.Point2d((this.x + other.x), (this.y + other.y));
    	//this.x += other.x;
    	//this.y += other.y;
    	return result;

    }
    private static void testAdd() {
        Point2d first = new Point2d();
        Point2d second = new Point2d();
        first.Point2d(2, 3);
        second.Point2d(1, 1);
        Point2d result = first.add(second);
        System.out.println("(" + result.x + ", " + result.y + ")");
    }
    //private Point2d distance(Point2d other) {
        //int x = Math.sqrt(Math.pow((other.y - this.y), 2));
        //int y = Math.sqrt(Math.pow((other.x - this.x), 2));
        //Point2d dist = new Point2d();
        //dist.Point2d(x, y);
    //}




}
