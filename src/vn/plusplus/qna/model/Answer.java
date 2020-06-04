package vn.plusplus.qna.model;

import java.util.List;

public class Answer {
    String userName;
    List<String> answerItems;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getAnswerItems() {
        return answerItems;
    }

    public void setAnswerItems(List<String> answerItems) {
        this.answerItems = answerItems;
    }
}