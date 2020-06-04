package vn.plusplus.qna.model;

public class AnswerItem {
    String idQuestion,answer;

    public AnswerItem() {
    }

    public AnswerItem(String idQuestion, String answer) {
        this.idQuestion = idQuestion;
        this.answer = answer;
    }

    public String getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
