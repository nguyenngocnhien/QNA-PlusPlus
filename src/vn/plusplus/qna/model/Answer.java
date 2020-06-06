package vn.plusplus.qna.model;

import java.util.List;

public class Answer {
    String userName;
    Float score;
    List<AnswerItem> answerItems;

    public Answer() {
    }
    public Answer(String userName, List<AnswerItem> answerItems,Float score) {
        this.userName = userName;
        this.answerItems = answerItems;
        this.score = score;
    }

    public Answer(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<AnswerItem> getAnswerItems() {
        return answerItems;
    }

    public void setAnswerItems(List<AnswerItem> answerItems) {
        this.answerItems = answerItems;
    }

    public Float getScore() {
        return 20f;
    }
    public void setScore(Float score) {
        this.score = score;
    }
}