package homework5.shapes;

public class Point {
    private static int count = 0;
    protected int x;
    protected int y;

    public Point() {
        this(0, 0);
    }

    public Point(int x, int y) {
        count++;
        this.x = x;
        this.y = y;
    }

    public static Point[] maxLength(Point[] arr) {
        double max = 0;
        Point[] result = new Point[2];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                double length = arr[i].length(arr[j]);
                if (length > max) {
                    max = length;
                    result[0] = arr[i];
                    result[1] = arr[j];
                }
            }
        }
        return result;
    }

    public double length(Point p) {
        return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2));
    }
}