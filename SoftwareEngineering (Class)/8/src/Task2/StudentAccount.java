package Task2;

import javax.security.auth.Subject;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentAccount extends Account{
    private int album;
    private String faculty;
    private String major;
    private float active;
    private List<Test> tests;


    public StudentAccount(String name, String surname, String login, String password, int album, String faculty, String major, float active, List<Test> tests) {
        super(name, surname, login, password);
        this.album = album;
        this.faculty = faculty;
        this.major = major;
        this.active = active;
        this.tests = tests;
    }

    public int getAlbum() {
        return album;
    }

    public void setAlbum(int album) {
        this.album = album;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public float getActive() {
        return active;
    }

    public void setActive(float active) {
        this.active = active;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
    public int failedSubjects(){
        List<String>subjects =  tests.stream().map(x->x.getDefinition().getSubject()).distinct().collect(Collectors.toList());
        return (int)tests.stream().filter(x->x.getResult()==2.0 &&subjects.contains(x.getDefinition().getSubject())).count();
    }
    public Set<String> failedSubjectsSet(){
        List<String>subjects =  tests.stream().map(x->x.getDefinition().getSubject()).distinct().collect(Collectors.toList());
        return tests.stream().filter(x->x.getResult()==2.0 &&subjects.contains(x.getDefinition().getSubject())).map(x->x.getDefinition().getSubject()).collect(Collectors.toSet());
    }
}
