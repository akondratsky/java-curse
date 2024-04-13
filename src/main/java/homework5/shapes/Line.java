package homework5.shapes;

public class Line {
    protected final Point pos1;
    protected final Point pos2;

    public Line(Point pos1, Point pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    /**
     * Создать метод isCross в классе Line , который принимает
     * аргумент another типа Line , и возвращает значение типа boolean ,
     * которое отвечает на вопрос: пересекаются ли линии?
     */
    public boolean isCross(Line another) {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Создать метод isParallel , в классе Line , который принимает
     * аргумент another типа Line , и возвращает значение типа boolean ,
     * которое отвечает на вопрос: параллельны ли линии?
     */
    public boolean isParallel(Line another) {
        return slope() == another.slope();
    }

    public double slope() {
        return (double) (pos2.y - pos1.y) / (pos2.x - pos1.x);
    }

    public double length() {
        return pos1.length(pos2);
    }
}
