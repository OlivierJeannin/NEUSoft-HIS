package his.guictrl;

import his.util.PatientUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class RegistrarControl {
    private static String currentDate;  // 当前日期
    private static int patientSequence;  // 下一个病历序列号

    /**
     * 程序启动，初始化。
     */
    public static void initiate() throws RuntimeException {
        currentDate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);

        /* 读取文件数据 */

        try {
            patientSequence = PatientUtil.retrieveNextSequence();
        }
        catch (IOException | NumberFormatException e) {
            System.err.println("文件异常，初始化失败");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * 程序关闭时的善后。
     */
    public static void close() {
        try {
            PatientUtil.saveNextSequence(patientSequence);
        }
        catch (IOException e) {
            System.err.println("文件异常");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * 分配一个可用的病历号字符串
     *
     * @return 由当前日期和6位序列号拼接而成的字符串
     */
    public static String patientID() {
        String patientID = currentDate + String.format("%06d", patientSequence);
        patientSequence++;
        return patientID;
    }

    public static void main(String[] args) {
        initiate();
        System.out.println(patientID());
        close();
    }

}
