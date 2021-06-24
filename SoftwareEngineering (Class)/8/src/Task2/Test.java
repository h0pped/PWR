package Task2;

import java.util.List;

public class Test {
    private StudentAccount student;
    private float result;
    private List<GivenAnswer> givenAnswers;
    private TestDefinition definition;

    public Test(StudentAccount student, float result, List<GivenAnswer> givenAnswers, TestDefinition definition) {
        this.student = student;
        this.result = result;
        this.givenAnswers = givenAnswers;
        this.definition = definition;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public List<GivenAnswer> getGivenAnswers() {
        return givenAnswers;
    }

    public void setGivenAnswers(List<GivenAnswer> givenAnswers) {
        this.givenAnswers = givenAnswers;
    }

    public StudentAccount getStudent() {
        return student;
    }

    public void setStudent(StudentAccount student) {
        this.student = student;
    }

    public TestDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(TestDefinition definition) {
        this.definition = definition;
    }
}
