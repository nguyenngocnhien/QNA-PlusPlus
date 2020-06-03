package vn.plusplus.qna.interfaces;

import vn.plusplus.qna.model.Question;

import java.util.List;

public interface QNAInterface {
    List<Question> findQuestionByCondition();
    int checkAnswer();
    void saveAnswer();
}

