package task3;

public class Circle implements Shape{
    public double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }


    @Override
    public double getArea() {
        return 3.14*Math.pow(radius,2);
    }

    @Override
    public double getPerimeter() {
        return 2*3.14*radius;
    }
}
