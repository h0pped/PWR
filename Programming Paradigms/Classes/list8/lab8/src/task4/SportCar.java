package task4;

public class SportCar extends Car {
    private double maxSpeed;
    public SportCar(double price, double weight, String name,Color color,int horsePowers, double maxSpeed) {
        super(price, weight, name,color,horsePowers);
        this.maxSpeed = maxSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
