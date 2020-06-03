package vn.plusplus.qna.model;

import java.util.List;

public class Answer {
    String userName;
    List<AnswerItem> answerItems;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}