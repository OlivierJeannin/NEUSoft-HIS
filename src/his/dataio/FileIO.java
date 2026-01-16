package his.dataio;

import java.io.*;
import java.util.ArrayList;

/**
 * 系统对文件的读写操作
 */
// todo 程序中的文件读写操作，都调用这个类
public class FileIO {

    public static ArrayList<String> readStrings(String pathname) throws IOException {
        Reader fileReader = new FileReader(pathname);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            while (bufferedReader.ready())
                arrayList.add(bufferedReader.readLine());
            return arrayList;
        }
        finally {
            bufferedReader.close();
            fileReader.close();
        }
    }

    public static void writeString(String pathname, boolean append, String str) throws IOException {
        Writer fileWriter = new FileWriter(pathname, append);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try {
            bufferedWriter.write(str);
            bufferedWriter.flush();
        }
        finally {
            bufferedWriter.close();
            fileWriter.close();
        }
    }

    public static ArrayList<Object> readObjects(String pathname) throws IOException, ClassNotFoundException {
        InputStream fileInputStream = new FileInputStream(pathname);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ArrayList<Object> arrayList = new ArrayList<>();
        try {
            while (objectInputStream.available() > 0)
                arrayList.add(objectInputStream.readObject());
            return arrayList;
        }
        finally {
            objectInputStream.close();
            fileInputStream.close();
        }
    }

    public static void writeObject(String pathname, boolean append, Object obj) throws IOException {
        OutputStream fileOutputStream = new FileOutputStream(pathname, append);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        try {
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
        }
        finally {
            objectOutputStream.close();
            fileOutputStream.close();
        }
    }

}
