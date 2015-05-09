package sample;

import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

    public int countStrings(String str)  {
        StringBuilder b = new StringBuilder();
        b.append(str);
        int index = -1;
        int count = -1;
        do {
            index = b.indexOf("\n", index + 1);
            count++;
        } while (index != -1);
        return count;
    }

    public int countWords(String str) throws IOException {

        String text1[] = str.split("\\s");
        ArrayList<String> array = new ArrayList();

        for (int j = 0; j < text1.length - 1; j++) {
            array.add(text1[j]);

        }
        if(array.size()==0){return 0;}
        return array.size()+1;
    }

    public int countSymbols(String str) {
        String text1[] = str.split("\\s");
        StringBuilder symbols = new StringBuilder();
        for (int i = 0; i < text1.length; i++) {
            symbols.append(text1[i]);
        }

        return symbols.length();
    }

    }




