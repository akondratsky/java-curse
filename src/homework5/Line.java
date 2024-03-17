package homework5;

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
    public static boolean isCross(Line l1, Line l2) {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Создать метод isParallel , в классе Line , который принимает
     * аргумент another типа Line , и возвращает значение типа boolean ,
     * которое отвечает на вопрос: параллельны ли линии?
     */
    public static boolean isParallel(Line l1, Line l2) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public double length() {
        return pos1.length(pos2);
    }
}
