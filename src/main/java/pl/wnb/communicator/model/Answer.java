package pl.wnb.communicator.model;

public class Answer {
    private String answer;
    private String from;
    public Answer() {
    }

    public Answer(String answer, String from) {
        this.answer = answer;
        this.from = from;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}