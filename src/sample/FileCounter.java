package sample;

import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by PC Kors on 08.05.2015.
 */
public class FileCounter {

    private static String ReadFile(File file) {    //на вход расположение и имя файла

        String s = null;

        StringBuilder contents = new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;


            while ((text = reader.readLine()) != null) {
                contents.append(text)
                        .append(System.getProperty(
                                "line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        s = contents.toString();
        return s;    //возврат строки с текстом из файла
    }

    public int countStrings(TextArea textArea) throws IOException {
        int count = 0;
        BufferedReader bf = new BufferedReader(new FileReader(textArea.getText()));
        String str = null;
        while ((str = bf.readLine()) != null) {
            count++;

        }
        bf.close();
        return count;
    }

    public int countWords(TextArea textArea) throws IOException {

        String text1[] = textArea.getText().split("\\s");
        ArrayList<String> array = new ArrayList();

        for (int j = 0; j < text1.length - 1; j++) {
            array.add(text1[j]);

        }
        return array.size();
    }

    public int countSymbols(TextArea textArea) {
        String text1[] = textArea.getText().split("\\s");
        StringBuilder symbols = new StringBuilder();
        for (int i = 0; i < text1.length; i++) {
            symbols.append(text1[i]);
        }

        return symbols.length();
    }


}
