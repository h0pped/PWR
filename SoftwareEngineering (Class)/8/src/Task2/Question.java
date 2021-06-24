package Task2;

import java.util.List;

public class Question {
    private String description;
    private int score;
    private List<Answer> answers;

    public Question(String description, int score, List<Answer> answers) {
        this.description = description;
        this.score = score;
        this.answers = answers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
