package vn.plusplus.qna;

import vn.plusplus.qna.model.Answer;
import vn.plusplus.qna.model.Question;
import vn.plusplus.qna.service.QNAService;
import vn.plusplus.qna.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Xin moi lua chon chuc nang: ");
        System.out.println("1. Kiem tra nang luc");
        System.out.println("2. Xem lai bai thi");
        System.out.println("3. Tim kiem thi sinh diem cao nhat");

        int option = scanner.nextInt();
        scanner.nextLine();
        QNAService qnaService = new QNAService();
        switch (option){
            case 1:
                System.out.println("Xin moi nhap vao language: ");
                String language = scanner.nextLine();
                System.out.println("Xin moi lua chon level: ");
                String level = scanner.nextLine();
                List<Question> questions = qnaService.findQuestionByCondition(language, level);
                qnaService.displayQuestion(questions);
                System.out.println("Nhap vao cau tra loi theo dinh dang: MA_CAU_HOI_1:CAU_TRA_LOI_1#MA_CAU_HOI_2:CAU_TRA_LOI_2");
                String answerText = scanner.nextLine();
                System.out.println("Nhap vao username:");
                String userName = scanner.nextLine();
                Answer answer = qnaService.buildAnswerFromUserInput(answerText, userName);
                int result = qnaService.checkAnswer(questions, answer);
                float score =(100/questions.size())*result;
                System.out.println("Ket qua cua ban la: " + result + "/" + questions.size() + " cau dung"+score);
                System.out.println("Ban co muon luu lai ket qua khong? Yes/No ");
                String savedAnswer = scanner.nextLine();
                if(savedAnswer.equals("Yes")){
                    qnaService.saveAnswer(answer,language,level,score);
                }
        }
    }


}
