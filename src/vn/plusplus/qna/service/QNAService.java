package vn.plusplus.qna.service;

import vn.plusplus.qna.interfaces.QNAInterface;
import vn.plusplus.qna.model.Answer;
import vn.plusplus.qna.model.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class QNAService implements QNAInterface {
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
        return listQuest;
    }

    @Override
    public int checkAnswer(List<Question> questions, Answer answers) {
        return 0;
    }

    @Override
    public void saveAnswer(List<Answer> answers, String userName) {
        FileWriter fileWriter = null;
        BufferedOutputStream bufferedOutputStream = null;
        File file = new File("/data"+"saveAnswer"+".txt");
        if (file.exists()){
            System.out.println("Đã tồn tại file");
        }else System.out.println("Chưa có file");
        String absolutePath = file.getAbsolutePath();
        try {
            fileWriter = new FileWriter(absolutePath);
//            bufferedOutputStream = new BufferedOutputStream(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("Checked exception");
        }

    }
}