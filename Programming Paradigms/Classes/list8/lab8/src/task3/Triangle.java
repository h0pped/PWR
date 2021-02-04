package task3;

public class Triangle implements Shape {
    public double a,b,c;

    public Triangle(double a,double b,double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    public void setC(double c) {
        this.c = c;
    }

    @Override
    public double getArea() {
        return 0.25* Math.sqrt((a + b + c) * (-a + b + c) * (a - b + c) * (a + b - c));
    }

    @Override
    public double getPerimeter() {
        return a+b+c;
    }
}
