package vn.plusplus.qna.service;

import vn.plusplus.qna.interfaces.StudentInterface;
import vn.plusplus.qna.model.Answer;
import vn.plusplus.qna.model.User;

public class StudentService implements StudentInterface {
    @Override
    public Answer findLastAnswerByUserName(String username) {
        return null;
    }

    @Override
    public User findHighestScoreStudent() {
        return null;
    }
}
