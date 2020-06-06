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
            while ((line = br.readLine())!=null){
                String[] answerArray = line.split("#");
                answerList.add(answerArray);
            }
            int highestScore = Integer.parseInt(answerList.get(0)[3]);
            //answerArray example: nhiempc,Java,1,80,JV1_1:A;JV1_2:B;....
            for(int i = 0;i<answerList.size();i++){
                if(Integer.parseInt(answerList.get(i)[3])>highestScore&&answerList.get(i)[1]==language&&answerList.get(i)[2]==level){
                    highestScore = Integer.parseInt(answerList.get(i)[3]);
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
        return null;
    }
}
