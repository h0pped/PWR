package task1;

enum Behavior{ SITTING, SLEEPING, PLAYING, WALKING, RUNNING}
enum State{ FUNNY, NOT_FUNNY}

public class Dog {
    private String name; //identity
    private String breed;//identity
    private int age; //identity
    private Behavior behavior;
    private State state;

    public Dog(){
        name = "unnamed";
        breed = "unknown";
        age = -1;
        behavior = Behavior.SLEEPING;
    }
    public Dog(String name, String breed, int age, Behavior behavior){
        this.name = name;
        this. breed = breed;
        this.age = age;
        this.behavior = behavior;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public State getState() {
        return state;
    }

    public String getBreed() {
        return breed;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }
}

