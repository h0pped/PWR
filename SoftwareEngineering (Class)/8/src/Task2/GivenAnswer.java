package Task2;

public class GivenAnswer {
    private Boolean isSelected;
    private Answer answer;

    public GivenAnswer(Boolean isSelected, Answer answer) {
        this.isSelected = isSelected;
        this.answer = answer;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
