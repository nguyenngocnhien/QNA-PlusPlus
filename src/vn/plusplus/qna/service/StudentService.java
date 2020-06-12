package vn.plusplus.qna.service;

import vn.plusplus.qna.interfaces.StudentInterface;
import vn.plusplus.qna.model.Answer;
import vn.plusplus.qna.model.AnswerItem;
import vn.plusplus.qna.model.Question;
import vn.plusplus.qna.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements StudentInterface {

    @Override
    public Answer findLastAnswerByUserName(String username) {
        Answer asw = new Answer();
        FileReader fr = null;
        BufferedReader br = null;
        File file = new File("data/answer.txt");
        String filePath = file.getAbsolutePath();
        int index = 0;
        try {
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            String line = "";
            List<AnswerItem> listAnswerItem = new ArrayList<>();
            List<String[]> answerList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] answerItem = line.split("#");
                answerList.add(answerItem);
            }
            for (int i = answerList.size() - 1; i >= 0; i--) {
                if (answerList.get(i)[0].equals(username)) {
                    index = i;
                    //JV1_1:A;JV1_2:B;...
                    String answer = answerList.get(i)[4];
                    String[] answerArray = answer.split(";");
                    for (String as : answerArray) {
                        String[] asItem = as.split(":");
                        listAnswerItem.add(new AnswerItem(asItem[0], asItem[1]));
                    }
                    break;
                } else {
                    index = -1;
                }
            }
            asw.setUserName(username);
            asw.setAnswerItems(listAnswerItem);
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy dữ liệu!");
        } catch (IOException e) {
            System.out.println("Lỗi đọc file!");
        } finally {
            try {
                fr.close();
                br.close();
            } catch (Exception e) {
                System.out.println("Có lỗi xảy ra!");
            }
        }
        if (index == -1) {
            return null;
        } else {
            return asw;
        }
    }

    @Override
    public String findHighestScoreUserName(String language, String level) {
        QNAService qnaService = new QNAService();
        FileReader fr = null;
        BufferedReader br = null;
        File file = new File("data/answer.txt");
        String filePath = file.getAbsolutePath();
        String userHighestScore = "";
        try {
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            String line = "";
            List<String[]> answerList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] answerArray = line.split("#");
                answerList.add(answerArray);
            }
            Float highestScore = Float.valueOf(answerList.get(0)[3]);
            userHighestScore = answerList.get(0)[0];
            //answerArray example: nhiempc,Java,1,80,JV1_1:A;JV1_2:B;....
            for (int i = 0; i < answerList.size(); i++) {
                if (Float.valueOf(answerList.get(i)[3]) > highestScore &&
                        answerList.get(i)[1].equalsIgnoreCase(language) &&
                        answerList.get(i)[2].equalsIgnoreCase(level))
                {
                    highestScore = Float.valueOf(answerList.get(i)[3]);
                    userHighestScore = answerList.get(i)[0];
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy dữ liệu!");
        } catch (IOException e) {
            System.out.println("Lỗi đọc file!");
        } finally {
            try {
                fr.close();
                br.close();
            } catch (Exception e) {
                System.out.println("Có lỗi xảy ra!");
            }
        }
        return userHighestScore;
    }

    @Override
    public User findUserByUserName(String userName) {
        User user = new User();
        FileReader fr = null;
        BufferedReader br = null;
        File file = new File("data/user.txt");
        String abp = file.getAbsolutePath();
        try {
            fr = new FileReader(abp);
            br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] infoUser = line.split("#");
                user = new User(infoUser[0],infoUser[1],infoUser[2],infoUser[3]);
                System.out.println();
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void displayAnswer(Answer answer) {
        String qCode = answer.getAnswerItems().get(0).getIdQuestion();
        QNAService qnaService = new QNAService();
        List<Question> questions = new ArrayList<>();
        if (qCode.equals("JV1_1")) {
            questions = qnaService.findQuestionByCondition("java", "1");
        } else if (qCode.equals("JV2_2")) {
            questions = qnaService.findQuestionByCondition("java", "2");
        } else if (qCode.equals("HTML_1")) {
            questions = qnaService.findQuestionByCondition("html", "1");
        }
        for (int i = 0; i < questions.size(); i++) {
            System.out.println(questions.get(i).getqCode() + ":" + questions.get(i).getqCOntent());
            System.out.println("A:" + questions.get(i).getqA());
            System.out.println("B:" + questions.get(i).getqB());
            System.out.println("C:" + questions.get(i).getqC());
            System.out.println("D:" + questions.get(i).getqD());
            System.out.println("Đáp án đúng là:" + questions.get(i).getqAnswer());
            System.out.println("Đáp án bạn chọn là:" + answer.getAnswerItems().get(i).getAnswer());
            System.out.println();
        }
    }

    @Override
    public void displayUser(User user) {
        System.out.println("Thông tin người dùng:");
        System.out.println("Họ và tên: " + user.getFullName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Số điện thoại: " + user.getPhone());
    }
}
