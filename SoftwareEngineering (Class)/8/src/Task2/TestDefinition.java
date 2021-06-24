package Task2;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class TestDefinition {
    private String title;
    private String subject;
    private Date date;
    private LocalTime timeFrom;
    private LocalTime timeTo;
    private List<Question> questions;
    private List<Test> tests;

    public TestDefinition(String title, String subject, Date date, LocalTime timeFrom, LocalTime timeTo, List<Question> questions, List<Test> tests) {
        this.title = title;
        this.subject = subject;
        this.date = date;
        this.setTimeTo(timeTo);
        this.setTimeFrom(timeFrom);
        this.questions = questions;
        this.tests = tests;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(LocalTime timeFrom) {
        if(timeFrom.isBefore(timeTo)){
            this.timeFrom = timeFrom;
        }
        else{
            System.out.println("Wrong time");
        }
    }

    public LocalTime getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(LocalTime timeTo) {
        if(timeTo.isAfter(timeFrom)) {
            this.timeTo = timeTo;
        }
        else{
            System.out.println("Wrong time");
        }
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
    public int findTests(int grade){
       return (int) tests.stream().filter(c->c.getResult()==grade).count();
    }
    public Set<StudentAccount> bestStudent(){
        double maxRes = tests.stream().max(Comparator.comparing(Test::getResult)).get().getResult();
        return tests.stream().filter(x->x.getResult()==maxRes).map(x->x.getStudent()).collect(Collectors.toSet());
    }
    public int score(){
         return questions.stream().mapToInt(x->x.getScore()).sum();
    }

}
