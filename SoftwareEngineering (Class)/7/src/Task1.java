import java.util.*;

public class Task1 {
    static class Person{
        private int ID;
        private String name;
        private String surname;

        public Person(int id,String name,String surname){
            this.ID = id;
            this.name = name;
            this.surname = surname;
        }

        private void assertNameAndSurname(){
            String concat = name.concat(surname);
            boolean containDigits = false;
            for(char x :this.name.toCharArray()){
                if(containDigits = Character.isDigit((x))){
                    break;
                }
            }
                assert containDigits == true: "Name or surname contains digits";
        }
        private void assertID(){
            assert ID>=10000: "ID Should contain at least 5 digits";
        }
    }
    class Team{
        private List<Person> teammates;

        private void assertTeammates(){
            assert  teammates.size()>=3 && teammates.size()<=5 : "Team should contain from 3 to 5 persons";
        }
    }
    class Project{
        private Team team;
        private String name;
        private Date startDate;
        private Date endDate;
        private List<Task> tasks;

        private void assertTasks(){
            Set<Task> duplicates = new LinkedHashSet<>();
            Set<Task> uniques = new HashSet<>();

            for(Task t : tasks) {
                if(!uniques.add(t)) {
                    duplicates.add(t);
                }
            }
            assert duplicates.size()>0: "Project should contain only unique tasks";
        }
        private void checkDate(){
            assert startDate.before(endDate) : "StartDate should be before the endDate";
        }
    }
    class Task{
        private int subsequent;
        private int duration;
        private String goal;
        private Person person;
    }
    public static void main(String[]args){
        Person p = new Person(123, "Str2", "strr");

    }
}
