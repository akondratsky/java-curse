package homework5.shapes;

public class Triangle extends Shape {
    private final double a, b, c;

    public Triangle(int color, double a, double b, double c) {
        super(ShapeType.TRIANGLE, color);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getPerimeter() {
        return a + b + c;
    }

    @Override
    public double getSquare() {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
