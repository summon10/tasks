package geometry;

public class Circle {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }


    public double area() {
        return Math.PI * radius * radius;
    }
    public double getRadius() {
         return radius;

        }
    public double perimeter() {
        return 2 * Math.PI * radius;
    }


    public String toString() {
        return String.format("geometry.Circle(radius=%.2f)", radius);
    }
}