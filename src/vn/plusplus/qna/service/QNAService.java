package vn.plusplus.qna.service;

import vn.plusplus.qna.interfaces.QNAInterface;
import vn.plusplus.qna.model.Answer;
import vn.plusplus.qna.model.AnswerItem;
import vn.plusplus.qna.model.Question;

import java.io.*;
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
        Map<String,String> correctAnswer = new HashMap<>();
        List<AnswerItem> answer = answers.getAnswerItems();
        for (Question question: questions){
            correctAnswer.put(question.getqCode(),question.getqAnswer());
        }
        int dem =0;
        for(AnswerItem answerItem: answer){
            if(answerItem.getAnswer().equals(correctAnswer.get(answerItem.getIdQuestion()))){
                dem++;
            }
        }
        return dem;
    }

    @Override
    public void saveAnswer(List<Answer> answers, String userName) {
        try {
            File file = new File("/QNA-PlusPlus/data/answer.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter br = new BufferedWriter(fileWriter);
            if (!file.exists()){
                file.createNewFile();
                br.write(answers.toString());
                br.close();
            }else {
                br.write(answers.toString());
                br.close();
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
}