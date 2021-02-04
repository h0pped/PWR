package task6;

public class Book {
    private String name;
    private Author author;
    private double price;
    private int qty = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price>0)
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        if(qty>=0)
        this.qty = qty;
    }

    public Book(String name, Author author, double price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }



    public Book(String name, Author author, double price, int qty) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Book: " +
                "Name='" + name + '\'' +
                ", Author=" + author.getName() +
                ", Price=" + price +
                ", Quantity=" + qty +"\n";
    }
}
