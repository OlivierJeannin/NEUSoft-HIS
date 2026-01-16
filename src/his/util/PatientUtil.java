package his.util;

import java.io.*;

public class PatientUtil {
    private static final String NextPatientSequenceFilePath = "./resource/next_patient_sequence.txt";

    /**
     * 程序刚刚启动时，从数据文件 {@code NextPatientSequenceFilePath} 中读取病历序列号
     *
     * @return 读到的序列号
     * @throws IOException if there is any error with file "{@code NextPatientSequenceFilePath}".
     */
    public static int retrieveNextSequence() throws IOException, NumberFormatException {
        File f = new File(NextPatientSequenceFilePath);
        FileReader fileReader = new FileReader(f);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int nextSequence;
        try {
            nextSequence = Integer.parseInt(bufferedReader.readLine());
        }
        finally {
            bufferedReader.close();
            fileReader.close();
        }

        return nextSequence;
    }

    /**
     * 程序关闭之前，将下一个可用的病历序列号保存到数据文件 {@code NextPatientSequenceFilePath}
     *
     * @param nextSequence 要写入文件的序列号
     * @throws IOException if there is any error occurs with file {@code NextPatientSequenceFilePath}.
     */
    public static void saveNextSequence(int nextSequence) throws IOException {
        File f = new File(NextPatientSequenceFilePath);
        FileWriter fileWriter = new FileWriter(f);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try {
            bufferedWriter.write(String.format("%06d", nextSequence));
        }
        finally {
            bufferedWriter.close();
            fileWriter.close();
        }
    }

}
