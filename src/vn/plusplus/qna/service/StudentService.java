package vn.plusplus.qna.service;

import vn.plusplus.qna.interfaces.StudentInterface;
import vn.plusplus.qna.model.Answer;
import vn.plusplus.qna.model.Question;
import vn.plusplus.qna.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements StudentInterface {

    @Override
    public Answer findLastAnswerByUserName(String username) {
        return null;
    }

    @Override
    public String findHighestScoreUserName(String language, String level) {
        QNAService qnaService = new QNAService();
        FileReader fr = null;
        BufferedReader br = null;
        File file = new File("data/" + "answer_"+language+"_"+level+".text");
        String filePath = file.getAbsolutePath();
        String userHighestScore = "";
        try {
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            String line = "";
            List<String[]> answerList = new ArrayList<>();
            while ((line = br.readLine())!=null){
                String[] answerArray = line.split("#");
                answerList.add(answerArray);
            }
            int highestScore = Integer.parseInt(answerList.get(0)[1]);
            //answerArray example: nhiempc,80,JV1_1:A;JV1_2:B;....
            for(int i = 0;i<answerList.size();i++){
                if(Integer.parseInt(answerList.get(i)[1])>highestScore){
                    highestScore = Integer.parseInt(answerList.get(i)[1]);
                    userHighestScore = answerList.get(i)[0];
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy dữ liệu!");
        } catch (IOException e){
            System.out.println("Lỗi đọc file!");
        } finally {
            try {
                fr.close();
                br.close();
            } catch (Exception e){
                System.out.println("Có lỗi xảy ra!");
            }
        }
        return userHighestScore;
    }

    @Override
    public User findUserByUserName(String userName) {
        List<User> user = new ArrayList<>();
        FileReader fl = null;
        BufferedReader br = null;
        File file = new File("data/user.text");
        String abp = file.getAbsolutePath();
        try {
            fl = new FileReader(abp);
            br = new BufferedReader(fl);
            String line="";
            while ((line=br.readLine())!=null){
                String[] infoUser = line.split("#");
                if (userName.equals(infoUser[0])){
                    System.out.println("UserName :"+infoUser[0]);
                    System.out.println("FullName :"+infoUser[1]);
                    System.out.println("Email :"+infoUser[2]);
                    System.out.println("Phone :"+infoUser[3]);
                }
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
