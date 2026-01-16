package his.logic.user;

import his.dataio.FileIO;
import his.logic.perma_data.RegisterLevel;

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
        // todo
    }

    public static void addRegisterLevel(String name, int fee) throws IOException {
        RegisterLevel rl = new RegisterLevel(name, fee);
        FileIO.writeObject("./resource/register_level", true, rl);
    }

    public static void main(String[] args) throws IOException {

    }

}
