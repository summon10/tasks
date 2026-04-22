import geometry.*;
import geometryUtils.*;
import threedim.Cube;
import threedim.Sphere;

public class GeometryApp {
    public static void main(String[] args) {
        Circle circle = new Circle(5.0);
        Rectangle rectangle = new Rectangle(4.0, 6.0);
        Triangle triangle = new Triangle(3.0, 4.0, 5.0);

        Cube cube = new Cube(3.0);
        Sphere sphere = new Sphere(2.55);


        System.out.println(circle);
        System.out.println(rectangle);
        System.out.println(triangle);

        System.out.println("Area in square feet: " + geometryUtils.convertSquareMetersToSquareFeet(circle.area()));

        System.out.printf("%s: Volume = %.2f, Surface Area = %.2f%n",
                cube, cube.getVolume(), cube.getSurfaceArea());
        System.out.printf("%s: Volume = %.2f, Surface Area = %.2f%n",
                sphere, sphere.getVolume(), sphere.getSurfaceArea());
    }
}
