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
            while ((line = br.readLine())!=null){
                String[] answerItem = line.split("#");
                answerList.add(answerItem);
            }
            for (int i = answerList.size()-1;i>=0;i--){
                if(answerList.get(i)[0].equals(username)){
                    index = i;
                    //JV1_1:A;JV1_2:B;...
                    String answer = answerList.get(i)[4];
                    String[] answerArray = answer.split(";");
                    for(String as:answerArray){
                        String[] asItem = as.split(":");
                        listAnswerItem.add(new AnswerItem(asItem[0],asItem[1]));
                    }
                }else {
                    index = -1;
                }
            }
            asw.setUserName(username);
            asw.setAnswerItems(listAnswerItem);
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
        if(index == -1){
            return null;
        }else {
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
        List<User> user = new ArrayList<>();
        FileReader fl = null;
        BufferedReader br = null;
        File file = new File("data/user.txt");
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
