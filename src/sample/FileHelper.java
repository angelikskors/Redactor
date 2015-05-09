package sample;


import java.io.*;
import java.util.ArrayList;


public class FileHelper {
    private ArrayList info;
    private static String pathFile;
    private StringBuilder sb;

    private static String ReadFile(File file) {    //�� ���� ������������ � ��� �����

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
        return s;    //������� ������ � ������� �� �����
    }

    public static void save(String pathFile) {
        File file = new File(pathFile);
        if (!file.exists()) {

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static void writeIntoFile(File f, String str) throws IOException {
        FileWriter writer = new FileWriter(f, false);
        BufferedWriter df = new BufferedWriter(writer);
        df.write(str);
        df.flush();
        df.close();


    }

    public StringBuilder readFile(File f) {
        StringBuilder sb = new StringBuilder();
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(f));
            String str = null;
            while ((str = bf.readLine()) != null) {
                sb.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bf != null) {
                try {
                    bf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb;
    }

    public static InputStream getInputStreamForFile(String path) {
        try {
            return new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}




