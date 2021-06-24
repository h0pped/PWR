package Task2;

import java.util.List;

enum deg {
    bachelor, master, doctor, professor
}
public class TeacherAccount {
    private String degree;
    private List<TestDefinition> testDefinitions;

    public TeacherAccount(String degree, List<TestDefinition> testDefinitions) {
        this.degree = degree;
        this.testDefinitions = testDefinitions;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        try{
            this.degree = deg.valueOf(degree).toString();
        }catch(Exception ex){
            System.out.println("Degree bad value");
        }
    }

    public List<TestDefinition> getTestDefinitions() {
        return testDefinitions;
    }

    public void setTestDefinitions(List<TestDefinition> testDefinitions) {
        this.testDefinitions = testDefinitions;
    }
}
