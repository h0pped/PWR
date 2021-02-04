package task4;

import java.util.List;

enum Properties{AERODYNAMIC, LIGHTWEIGHT, TWO_SEATER,BUTTERFLY_DOORS}
public class SuperCar extends SportCar {
    public SuperCar(double price, double weight, String name, Color color, int horsePowers, double maxSpeed, List<Properties> properties) {
        super(price, weight, name, color, horsePowers, maxSpeed);
        this.properties = properties;
    }

    public List<Properties> getProperties() {
        return properties;
    }

    public void setProperties(List<Properties> properties) {
        this.properties = properties;
    }

    List<Properties>properties;
}
