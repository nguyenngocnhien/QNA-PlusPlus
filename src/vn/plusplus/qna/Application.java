package vn.plusplus.qna;

import vn.plusplus.qna.model.Question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;

public class Application {
    public static void main(String[] args) {
        File file = new File("question_java_1.txt");
        try {
            FileReader fr = new FileReader("question_java_1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Không tìm thấy file!");
        }
        System.out.println(file.getAbsolutePath());
    }
}
