package homework5;

public class Rectangle extends Shape {
    private final double a;
    private final double b;

    public Rectangle(int color, double a, double b) {
        super(ShapeType.RECTANGLE, color);
        this.a = a;
        this.b = b;
    }

    @Override
    public double getPerimeter() {
        return 2 * (a + b);
    }

    @Override
    public double getSquare() {
        return a * b;
    }
}
