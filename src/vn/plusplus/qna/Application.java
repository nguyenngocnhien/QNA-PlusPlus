package vn.plusplus.qna;

import vn.plusplus.qna.model.Answer;
import vn.plusplus.qna.model.Question;
import vn.plusplus.qna.service.QNAService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        QNAService qnaService = new QNAService();
        qnaService.saveAnswer(new Answer(), "kiemnx");
    }
}
