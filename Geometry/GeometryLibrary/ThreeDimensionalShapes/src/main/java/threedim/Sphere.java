package threedim;

public class Sphere {
    private final double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }


    public double getVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }


    public double getSurfaceArea() {
        return 4 * Math.PI * radius * radius;
    }

    public String toString() {
        return String.format("Sphere(radius=%.2f)", radius);
    }
    public String getDescription() {
        return "Description of Sphere for version 1.1.0";
    }
}