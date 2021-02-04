package task4;

enum Color{BLACK, WHITE,RED,PURPLE,BLUE,YELLOW,OTHER}

public abstract class Car {
    public Car(double price, double weight, String name,Color color,int horsePowers) {
        this.price = price;
        this.weight = weight;
        this.name = name;
        this.color = color;
        this.horsePowers  = horsePowers;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    private int horsePowers;

    public int getHorsePowers() {
        return horsePowers;
    }

    public void setHorsePowers(int horsePowers) {
        this.horsePowers = horsePowers;
    }

    private Color color;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private double weight;
    private String name;

}
