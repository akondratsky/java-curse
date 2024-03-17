package homework5;

public class Circle extends Shape {
    private final Point center;
    private final double r;

    public Circle(int color, Point center, double r) {
        super(ShapeType.CIRCLE, color);
        this.center = center;
        this.r = r;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * r;
    }

    @Override
    public double getSquare() {
        return Math.PI * Math.pow(r, 2);
    }
}
