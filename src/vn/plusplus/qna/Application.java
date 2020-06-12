package vn.plusplus.qna;

import vn.plusplus.qna.model.Answer;
import vn.plusplus.qna.model.Question;
import vn.plusplus.qna.model.User;
import vn.plusplus.qna.service.QNAService;
import vn.plusplus.qna.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Xin moi lua chon chuc nang: ");
            System.out.println("1. Kiem tra nang luc");
            System.out.println("2. Xem lai bai thi");
            System.out.println("3. Tim kiem thi sinh diem cao nhat");
            System.out.println("4. Thoát chương trình");

            int option = scanner.nextInt();
            scanner.nextLine();
            QNAService qnaService = new QNAService();
            StudentService studentService = new StudentService();
            switch (option){
                case 1:
                    System.out.println("Xin moi nhap vao language: java/html");
                    String language = scanner.nextLine();
                    System.out.println("Xin moi lua chon level: 1/2");
                    String level = scanner.nextLine();
                    List<Question> questions = qnaService.findQuestionByCondition(language, level);
                    qnaService.displayQuestion(questions);
                    System.out.println("Nhap vao " + questions.size() +" cau tra loi theo thu tu: A,B,....");
                    String answerInput = scanner.nextLine();
                    String [] answerList = answerInput.split(",");
                    String answerText = "";
                    for (int i = 0; i<questions.size(); i++){
                        answerText = answerText + questions.get(i).qCode + ":" + answerList[i] + "#";
                    }
                    System.out.println("Nhap vao username:");
                    String userName = scanner.nextLine();
                    Answer answer = qnaService.buildAnswerFromUserInput(answerText, userName);
                    int result = qnaService.checkAnswer(questions, answer);
                    float score =(100/questions.size())*result;
                    System.out.println("Ket qua cua ban la: " + result + "/" + questions.size() + " cau dung: " + score);
                    System.out.println("Ban co muon luu lai ket qua khong? Yes/No ");
                    String savedAnswer = scanner.nextLine();
                    if(savedAnswer.equalsIgnoreCase("Yes")){
                        qnaService.saveAnswer(answer,language,level,score);
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Nhap vao username cua ban: ");
                    String user = scanner.nextLine();
                    Answer answerUser = studentService.findLastAnswerByUserName(user);
                    System.out.println("Bai thi gan nhat cua ban: ");
                    studentService.displayAnswer(answerUser);
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Nhap vao ngon ngu: java/html");
                    String languageF = scanner.nextLine();
                    System.out.println("Nhao vao level: 1/2");
                    String levelF = scanner.nextLine();

                    String userF = studentService.findHighestScoreUserName(languageF, levelF);
                    User userModel = studentService.findUserByUserName(userF);
                    studentService.displayUser(userModel);
                    System.out.println();
                    break;
                case 4:
                    return;
            }
        }
    }


}
