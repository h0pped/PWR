package Task2;

public class Account {
    private String name;
    private String surname;
    private String login;
    private String password;

    public Account(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.setLogin(login);
        this.setPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if(login!=password){
            this.login = login;
        }
        else{
            System.out.println("Login and password must be different");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password!=login){
            this.password = password;
        }
        else{
            System.out.println("Login and password must be different");
        }
    }
}
