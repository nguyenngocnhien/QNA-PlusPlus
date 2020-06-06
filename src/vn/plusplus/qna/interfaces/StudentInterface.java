package vn.plusplus.qna.interfaces;

import vn.plusplus.qna.model.Answer;
import vn.plusplus.qna.model.User;

public interface StudentInterface {
    Answer findLastAnswerByUserName(String username);
    String findHighestScoreUserName();
    User findUserByUserName(String userName);
}
