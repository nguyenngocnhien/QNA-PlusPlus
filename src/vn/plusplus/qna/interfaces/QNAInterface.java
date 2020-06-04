package vn.plusplus.qna.interfaces;

import vn.plusplus.qna.model.Answer;
import vn.plusplus.qna.model.Question;

import java.io.IOException;
import java.util.List;

public interface QNAInterface {
    List<Question> findQuestionByCondition(String language, String level) throws IOException;

    int checkAnswer(List<Question> questions, Answer answers);

    void saveAnswer(List<Answer> answers, String userName);
}

