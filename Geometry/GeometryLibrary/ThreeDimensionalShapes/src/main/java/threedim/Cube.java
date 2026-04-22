package threedim;

public class Cube  {
    private final double side;

    public Cube(double side) {
        this.side = side;
    }

    public double getVolume() {
        return side * side * side;
    }

    public double getSurfaceArea() {
        return 6 * side * side;
    }

    public String toString() {
        return String.format("Cube(side=%.2f)", side);
    }
    public String getDescription(){
        return "Description of Cube for version 1.1.0";
    }
}