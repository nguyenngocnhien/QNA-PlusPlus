package vn.plusplus.qna.model;

import vn.plusplus.qna.interfaces.QNAInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Question implements QNAInterface {
    String qCode,qCOntent,qA,qB,qC,qD,qAnswer;
    public Question(){};
    public Question(String qCode,String qCOntent,String qA,String qB,String qC,String qD,String qAnswer){};

    public String getqCode() {
        return qCode;
    }

    public void setqCode(String qCode) {
        this.qCode = qCode;
    }

    public String getqCOntent() {
        return qCOntent;
    }

    public void setqCOntent(String qCOntent) {
        this.qCOntent = qCOntent;
    }

    public String getqA() {
        return qA;
    }

    public void setqA(String qA) {
        this.qA = qA;
    }

    public String getqB() {
        return qB;
    }

    public void setqB(String qB) {
        this.qB = qB;
    }

    public String getqC() {
        return qC;
    }

    public void setqC(String qC) {
        this.qC = qC;
    }

    public String getqD() {
        return qD;
    }

    public void setqD(String qD) {
        this.qD = qD;
    }

    public String getqAnswer() {
        return qAnswer;
    }

    public void setqAnswer(String qAnswer) {
        this.qAnswer = qAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "qCode='" + qCode + '\'' +
                ", qCOntent='" + qCOntent + '\'' +
                ", qA='" + qA + '\'' +
                ", qB='" + qB + '\'' +
                ", qC='" + qC + '\'' +
                ", qD='" + qD + '\'' +
                ", qAnswer='" + qAnswer + '\'' +
                '}';
    }

    public List<Question> findQuestionByCondition(String language, String level) throws IOException {
        List<Question> listQuest = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        File file = new File("question_"+language+"_"+level+".txt");
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
        }finally {
            fr.close();
            br.close();
        }
        return null;
    }
}
