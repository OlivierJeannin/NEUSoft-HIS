import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 门诊医生
 *
 * <p>填写患者病历，确诊病情，开立检查、检验、处置、处方等收费项目。</p>
 */
public class Clinic extends User {

    /**
     * 显示所有挂号到该医生下的患者
     *
     * @throws NullPointerException 发生IO错误时抛出
     */
    public static void listPatients() throws NullPointerException {
        System.out.println("----------患者列表----------");
        File f = new File("./resource/patien");
        String[] patients = f.list();
        // 筛选挂号到当前医生下的患者
        for (String patient : patients) {
            if (patient.split("_")[0].equals(loginID))
                System.out.println(patient.split("_")[0]);
        }
    }

    /**
     * 填写患者病历信息
     */
    public static void fillInDiagnosis() {
        System.out.println("----------初步诊断----------");

        // 填写信息
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"主诉\":\"" + scanner.next() + "\",");
        stringBuilder.append("\"现病史\":\"" + scanner.next() + "\",");
        stringBuilder.append("\"既往史\":\"" + scanner.next() + "\",");

        // todo 增删诊断疾病项目
        int diseaseNumber = scanner.nextInt();
        stringBuilder.append("\"初步诊断\":[");
        for (int i = 0; i < diseaseNumber; i++) {
            stringBuilder.append("{\"ICD编码\":\"" + scanner.next() + "\",\"疾病名称\":\"" + scanner.next() + "\"}");
            if (i != diseaseNumber - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("],");

        stringBuilder.append("\"检查建议\":\"" + scanner.next() + "\",");
        stringBuilder.append("\"注意事项\":\"" + scanner.next() + "\"}\n");
        String diagnosisString = stringBuilder.toString();

        System.out.println(diagnosisString);

        System.out.println("确认");
        if (scanner.next().equals("y")) {
            // 写入病历本文件
        }
    }

    public static void main(String[] args) throws IOException {
        if (login()) {
            listPatients();

            System.out.println("选择患者：");
            String currentPatientID = scanner.nextLine();
            File f = new File("./resource/patient/" + currentPatientID + "_" + loginID);
            Reader fileReader = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            try {
                // 读病历文件所有内容，并显示
                while (bufferedReader.ready()) {
                    System.out.println(bufferedReader.readLine());
                }
            }
            finally {
                bufferedReader.close();
                fileReader.close();
            }
            fillInDiagnosis();
        }
    }

}
