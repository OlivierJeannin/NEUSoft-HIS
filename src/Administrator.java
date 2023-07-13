import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Administrator {

    /**
     * 添加疾病条目
     *
     * <p> 将新的疾病信息，以 json 格式，添加到数据文件 {@code "./resource/disease"} 中。 </p>
     *
     * @param ICDCode 疾病的 ICD 编码
     * @param name 疾病名称
     */
    public static void addDiseaseItem(String ICDCode, String name) throws IOException {
        File f = new File("./resource/disease");
        FileWriter fileWriter = new FileWriter(f, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try {
            bufferedWriter.append("\n{\"ICD\":\"" + ICDCode + "\",\"名称\":\"" + name + "\"}");
        }
        finally {
            bufferedWriter.close();
            fileWriter.close();
        }
    }

    public static void main(String[] args) throws IOException {
    }

}
