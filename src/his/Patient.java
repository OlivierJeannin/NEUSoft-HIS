package his;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 患者病历本
 *
 * <p>是一个患者一次就医的记录</p>
 */
public class Patient implements Serializable {
    private static String nextID;  // 下一个患者的病历号

    public final String ID;  // 病历号

    public final Map<String, String> registerInfo;  // 挂号信息
    public Map<String, Object> diagnosisInfo;  // 诊断信息
    public List<PaidItem>[] paidItems;  // 开立的付费项目，包含有4个列表，分别表示：检查、检验、处置、处方

    /**
     * 创建患者病历本
     *
     * @param registerInfo 挂号时填写的基本信息
     */
    public Patient(Map<String, String> registerInfo) {
        ID = nextID;
        nextID = String.format("%06d", Integer.parseInt(nextID) + 1);
        this.registerInfo = registerInfo;
        diagnosisInfo = new HashMap<>();
        paidItems = new List[]{new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()};
    }

    /**
     * 程序刚刚启动时，从数据文件中读取下一个患者的病历号
     *
     * <p>数据文件位于：{@code ./resource/next_patient_id.txt}</p>
     *
     * @throws IOException if there is any I/O error occurs with the file "{@code ./resource/next_patient_id.txt}".
     */
    public static void retrieveNextID() throws IOException {
        File f = new File("./resource/next_patient_id.txt");
        FileReader fileReader = new FileReader(f);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        try {
            nextID = bufferedReader.readLine();
        }
        finally {
            bufferedReader.close();
            fileReader.close();
        }
    }

    /**
     * 程序关闭之前，将下一个患者的病历号保存到数据文件
     *
     * <p>数据文件位于：{@code ./resource/next_patient_id.txt}</p>
     *
     * @throws IOException if there is any I/O error occurs with the file "{@code ./resource/next_patient_id.txt}".
     */
    public static void saveNextID() throws IOException {
        File f = new File("./resource/next_patient_id.txt");
        FileWriter fileWriter=new FileWriter(f);
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
        try {
            bufferedWriter.write(nextID);
        }
        finally {
            bufferedWriter.close();
            fileWriter.close();
        }
    }

}
