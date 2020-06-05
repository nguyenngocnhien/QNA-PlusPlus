package vn.plusplus.qna;

import vn.plusplus.qna.model.Answer;
import vn.plusplus.qna.model.AnswerItem;
import vn.plusplus.qna.service.QNAService;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        QNAService qnaService = new QNAService();
        List<AnswerItem> answerItems = new ArrayList<>();
        answerItems.add(new AnswerItem("JV1_1", "A"));
        Answer answer = new Answer("kiemnx", answerItems);
        qnaService.saveAnswer(answer);
    }
}
