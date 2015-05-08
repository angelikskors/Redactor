package sample;


import java.io.*;
import java.util.ArrayList;


public class FileHelper {
    private ArrayList info;
    private static String pathFile;
    private StringBuilder sb;

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

    public StringBuilder readFile(File f) throws IOException {
        //  info=new ArrayList();
        BufferedReader bf = new BufferedReader(new FileReader(f));
        if (f.exists()) {
            String str = null;
            while ((str = bf.readLine()) != null) {
                sb = new StringBuilder();
                sb.append(str);

            }
            bf.close();
            return sb;
        }

        return null;
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




