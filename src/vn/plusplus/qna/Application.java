package vn.plusplus.qna;

import vn.plusplus.qna.service.QNAService;

import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap vao ngon ngu: ");
        String language = scanner.nextLine();
        System.out.println("Nhap vao level: ");
        String level = scanner.nextLine();

        QNAService qnaService = new QNAService();
        qnaService.findQuestionByCondition(language, level);
    }
}
