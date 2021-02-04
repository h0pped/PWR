package task3;

public class Square implements Shape {

    private double length;

    public Square(double length){
        this.length = length;
    }

    public double getLength() {
        return length;
    }


    public void setLength(double length) {
        this.length = length;
    }


    @Override
    public double getArea() {
        return length*length;
    }

    @Override
    public double getPerimeter() {
    return 2*(length+length);
    }
}
