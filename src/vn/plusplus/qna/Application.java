package vn.plusplus.qna;

import vn.plusplus.qna.model.Answer;
import vn.plusplus.qna.model.Question;
import vn.plusplus.qna.service.QNAService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Question> listQuest = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        File file = new File("data/" + "question_java_1.txt");
        String filePath = file.getAbsolutePath();
        try {
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine())!=null){
                String[] question = line.split("#");
                Question q = new Question(question[0],question[1],question[2],question[3],question[4],question[5],question[6]);
                q.setqCode(question[0]);
                q.setqCOntent(question[1]);
                q.setqA(question[2]);
                q.setqB(question[3]);
                q.setqC(question[4]);
                q.setqD(question[5]);
                q.setqAnswer(question[6]);
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
        System.out.println(listQuest);
        List<String> correctAnswer = new ArrayList<>();
        for(Question q:listQuest){
            correctAnswer.add(q.getqAnswer());
        }
        System.out.println(correctAnswer.get(1));
        List<String> userAnswer = new ArrayList<>();
        userAnswer.add("D");
        userAnswer.add("D");
        userAnswer.add("D");
        userAnswer.add("D");
        userAnswer.add("D");
        int dem = 0;
        for(int i = 0; i< correctAnswer.size();i++){
            if (correctAnswer.get(i).equals(userAnswer.get(i))){
                dem++;
            }
        }
        System.out.println(dem);
    }
}
