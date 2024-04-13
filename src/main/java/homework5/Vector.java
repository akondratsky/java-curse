package homework5;

public class Vector extends Line {

    public Vector(Point pos1, Point pos2) {
        super(pos1, pos2);
    }

    public Vector(Line line) {
        super(line.pos1, line.pos2);
    }

    public Vector startFrom(Point start) {
        return new Vector(
                start,
                new Point(start.x + (pos2.x - pos1.x), start.y + (pos2.y - pos1.y))
        );
    }

    /**
     * Создать метод scalarSum в классе Vector , который принимает
     * аргумент another типа Vector и возвращает значение типа Vector ,
     * которое представляет собой векторное сложение векторов
     * правилом трех точек (необходимо использовать метод startFrom )
     */
    public Vector scalarSum(Vector another) {
        // https://ru.wikipedia.org/wiki/Вектор_(геометрия)#Правило_трёх_точек
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Создать метод scalarMult в классе Vector , который принимает
     * аргумент another типа Vector и возвращает значение типа double ,
     * которое представляет собой скалярное произведение векторов
     */
    public double scalarMult(Vector another) {
        // https://ru.wikipedia.org/wiki/Вектор_(геометрия)#Скалярное_произведение_векторов
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
