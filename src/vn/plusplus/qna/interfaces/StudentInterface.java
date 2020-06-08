package vn.plusplus.qna.interfaces;

import vn.plusplus.qna.model.Answer;
import vn.plusplus.qna.model.User;

public interface StudentInterface {
    Answer findLastAnswerByUserName(String username);
    String findHighestScoreUserName(String language, String level);
    User findUserByUserName(String userName);

    void displayAnswer(Answer answer);

    void displayUser(User user);
}
