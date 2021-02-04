package task5;

public class task5main {
    public static void main(String[]args){
        User u1 = new User(0,"user1","notuser1@gmail.com","228332");
        System.out.println(u1.equals(new User(0,"user1","notuser1@gmail.com","228332"))); //should be true

    }
}
