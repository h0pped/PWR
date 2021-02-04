package task6;

public class task6main {
    public static void main(String[]args){
        Author a1 = new Author("kent","kent@gmail.com",'m');
        Book b1 = new Book("Guide how to become kent",a1,228,1);
        System.out.println(b1.toString());
    }
}
