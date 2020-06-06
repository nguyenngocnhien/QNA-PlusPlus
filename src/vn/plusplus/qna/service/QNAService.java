package vn.plusplus.qna.service;

import vn.plusplus.qna.interfaces.QNAInterface;
import vn.plusplus.qna.model.Answer;
import vn.plusplus.qna.model.AnswerItem;
import vn.plusplus.qna.model.Question;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public void saveAnswer(Answer answer) {
        try {
            FileWriter fileWriter = null;
            BufferedWriter br = null;
            File file = new File("data/answer.txt");
            List<AnswerItem> answerItems = answer.getAnswerItems();
            if (!file.exists()){

                //Get current path of project in local computer
                Path currentRelativePath = Paths.get("");
                String s = currentRelativePath.toAbsolutePath().toString();

                //Create new file answer.txt with absoluatePath
                fileWriter = new FileWriter(s +"/data/answer.txt");
                br = new BufferedWriter(fileWriter);
                file.createNewFile();
                br.write(answer.getUserName()+"#");
                for (AnswerItem answerItem:answerItems){
                    br.write(answerItem.toString());
                }
                br.close();
            }else {

                // Append mode when file is existed
                fileWriter = new FileWriter(file, true);
                br = new BufferedWriter(fileWriter);
                br.newLine();
                br.write(answer.getUserName()+"#");
                for (AnswerItem answerItem:answerItems){
                    br.write(answerItem.toString());
                }
                br.close();
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
    @Override
    public void displayQuestion(List<Question> questions) {
        questions = new ArrayList<>();
        for (int i=0;i<questions.size();i++){
            System.out.println(questions.get(i).getqCode()+" : "+questions.get(i).getqCOntent());
            System.out.println("A:"+questions.get(i).getqA());
            System.out.println("B:"+questions.get(i).getqB());
            System.out.println("C:"+questions.get(i).getqC());
            System.out.println("D:"+questions.get(i).getqD());
        }
    }

    @Override
    public Answer buildAnswerFromUserInput(String answer, String userName) {
        //answer example: JV1_1:A#JV1_2:B#....
        Answer asw = new Answer();
        String[] answerArray = answer.split("#");
        List<AnswerItem> listAnswerItem = new ArrayList<>();
        for (String anserItem: answerArray){
            String[] answerArray2 = anserItem.split(":");
            listAnswerItem.add(new AnswerItem(answerArray2[0],answerArray2[1]));
        }
        asw.setAnswerItems(listAnswerItem);
        asw.setUserName(userName);
        return asw;
    }

    @Override
    public Question findQuestionByCode(String qCode) {
        return null;
    }
}