package his.logic;

import his.guictrl.RegistrarControl;

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
    private static String ID;  // 病历号

    public final Map<String, Object> registerInfo;  // 挂号信息

    public boolean isDiagnosed;  // 是否已看诊
    public Map<String, Object> diagnosisInfo;  // 诊断信息
    public List<PaidItem>[] paidItems;  // 开立的付费项目，包含有4个列表，分别表示：检查、检验、处置、处方

    /**
     * 创建患者病历本
     *
     * @param registerInfo 挂号时填写的基本信息
     */
    public Patient(Map<String, Object> registerInfo) {
        ID = RegistrarControl.patientID();
        this.registerInfo = registerInfo;
        isDiagnosed = false;
        diagnosisInfo = new HashMap<>();
        paidItems = new List[]{new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()};
    }

    /**
     * 返回字符串格式的病历号
     */
    public String getIDString() {
        return ID;
    }

}
