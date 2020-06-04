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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                Question q = new Question(question[0],question[1],question[2],question[3],question[4],question[5],question[6]);
                listQuest.add(q);
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
        return listQuest;
    }

    @Override
    public int checkAnswer(List<Question> questions, Answer answers) {
//        List<String> correctAnswer = new ArrayList<>();
//        List<String> userAnswer = answers.
//        List<String> questionCode = new ArrayList<>();
//        for(Question q:questions){
//            correctAnswer.add(q.getqAnswer());
//            questionCode.add(q.getqCode());
//        }
//        int dem = 0;
//        for(int i = 0; i< correctAnswer.size();i++){
//            if (correctAnswer.get(i).equals(userAnswer.get(i))){
//                dem++;
//            }
//        }
        List<String> questionCode = new ArrayList<>();
        for (Question q: questions){
            questionCode.add(q.getqCode());
        }
        Map<String,String> userAnswer = new HashMap<>();
        String userName = answers.getUserName();

        return 0;
    }

    @Override
    public void saveAnswer(List<Answer> answers, String userName) {

    }
}