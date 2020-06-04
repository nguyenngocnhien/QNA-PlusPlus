package vn.plusplus.qna.service;

import vn.plusplus.qna.interfaces.QNAInterface;
import vn.plusplus.qna.model.Answer;
import vn.plusplus.qna.model.Question;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QNAService implements QNAInterface {
    @Override
    public List<Question> findQuestionByCondition(String language, String level){
        List<Question> listQuest = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        File file = new File("data/" + "question_"+language+"_"+level+".txt");
        String filePath = file.getAbsolutePath();
        try {
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine())!=null){
                String[] question = line.split("#");
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
        return null;
    }

    @Override
    public int checkAnswer(List<Question> questions, List<Answer> answers) {
        return 0;
    }

    @Override
    public void saveAnswer(List<Answer> answers, String userName) {

    }
}