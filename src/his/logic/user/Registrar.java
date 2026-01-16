package his.logic.user;

import his.dataio.FileIO;
import his.logic.Patient;
import his.logic.perma_data.RegisterLevel;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 挂号收费员
 *
 * <p>登录账号后，选择执行挂号、退号、收费、退费、打印发票流程。</p>
 */
// todo 后面考虑在系统中保存收据和冲红
abstract class Registrar extends User {
    private static final List<RegisterLevel> registerLevels = new ArrayList<>();

    /**
     * 金额形式转换（展示给用户的形式 -> 程序中运算、储存的形式）
     *
     * @param moneyString 保留2位小数的金额字符串（单位：元）
     *
     * @return 整型金额数字（单位：分）
     */
    public static int moneyDecimalString2Int(String moneyString) {
        assert moneyString.indexOf(".") == (moneyString.length() - 3)
                && moneyString.lastIndexOf(".") == (moneyString.length() - 3);
        String[] a = moneyString.split("\\.");
        return Integer.parseInt(a[0]) * 100 + Integer.parseInt(a[1]);
    }

    /**
     * 金额形式转换（程序中运算、储存的形式 -> 展示给用户的形式）
     *
     * @param moneyInt 整型金额数字（单位：分）
     *
     * @return 保留2位小数的金额字符串（单位：元）
     */
    public static String moneyInt2DecimalString(int moneyInt) {
        return String.valueOf(moneyInt / 100) + '.' + String.valueOf(moneyInt % 100);
    }

    /**
     * 计算挂号费。
     *
     * <p>根据：挂号级别、是否需要病历本。</p>
     *
     * @return 挂号费，保留2位小数的字符串
     */
    public static int calculateRegisterFee(String registerLevel, boolean needHistoryBook) throws IOException {
        int fee = 0;
//        todo 此函数的返回值类型是？？？？
/*
        ArrayList<RegisterLevel> arrayList = (ArrayList<RegisterLevel>)FileIO.readObjects("./resource/register_level");

        for (RegisterLevel level : arrayList) {
            if (registerLevel.equals(level.name)) {
                fee = level.fee;
            }
        }
*/
        return needHistoryBook ? (fee + 1) : fee;
    }

    /**
     * 挂号登记
     *
     * <p>输入患者基本信息，收取挂号费、打印发票，将患者病历写入文件。</p>
     *
     * <p>输入的信息格式：“姓名 性别 身份证号 挂号级别 科室 医生 是否需要病历本”</p>
     */
    public static void register() throws IOException {
        System.out.println("----------挂号登记----------");
        String[] registerInfoKeys = {"姓名", "性别", "身份证号", "挂号级别", "挂号科室", "挂号医生", "是否需要病历本"};

        // 输入患者信息
        // todo 检查输入值
        Map<String, Object> registerInfo = new HashMap<>();
        for (String key : registerInfoKeys) {
            System.out.println(key + "：");
            registerInfo.put(key, scanner.next());
        }

        // 收费
        String registerFee = moneyInt2DecimalString(calculateRegisterFee((String)registerInfo.get("挂号级别"), (Boolean)registerInfo.get("是否需要病历本")));
        System.out.println("应收挂号费：￥" + registerFee);

//        System.out.println("实收：");
//        double received = scanner.nextDouble();

        System.out.print("确认：");
        if (scanner.next().equals("y")) {
//            printReceipt(new String[]{"挂号费 " + registerFee}, received);  // 打印发票
            Patient patient = new Patient(registerInfo);
            FileIO.writeObject("./resource/patient/" + patient.getIDString() + "_" + registerInfo.get("挂号医生"), false, patient);
            System.out.println("挂号完成！");
        }
        else System.out.println("已取消挂号！");
    }

    /**
     * 退号取消登记
     *
     * <p>输入病历号，显示挂号信息，删除病历文件，退挂号费、打印发票。</p>
     */
    public static void unregister() throws IOException {
        System.out.println("----------退号取消登记----------");

        System.out.print("病历号：");
        String patientID = scanner.next();
        // 根据病历号查找挂号信息
        File dir = new File("./resource/patient");
        String[] filenames = dir.list();
        if (filenames != null) {
            for (String filename : filenames) {
                // 查到病例
                if (filename.startsWith(patientID)) {
                    // 显示挂号信息
                    File f = new File(dir, filename);
                    Reader fileReader = new FileReader(f);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String[] registerInfo;
                    try {
                        registerInfo = bufferedReader.readLine().split(" ");
                    }
                    finally {
                        bufferedReader.close();
                        fileReader.close();
                    }
                    System.out.print(
                            "***挂号信息***\n" +
                                    "病历号：" + patientID + " 姓名：" + registerInfo[0] + " 身份证号：" + registerInfo[2] + " 看诊科室：" + registerInfo[4] + " 看诊状态：" + registerInfo[6] + "\n"
                    );
                    // 判断是否已诊
                    if (Boolean.parseBoolean(registerInfo[6])) System.out.println("患者已诊，无法退号！");
                    else {
                        String registerFee = moneyInt2DecimalString(calculateRegisterFee(registerInfo[3], Boolean.parseBoolean(registerInfo[5])));
                        // 填发票信息
                        System.out.println("应退挂号费：￥" + registerFee);

                        System.out.println("确认退号：");
                        if (scanner.next().equals("y")) {
                            f.delete();
//                            printReceipt(new String[]{"挂号费 -" + registerFee}, 0-registerFee);  // 打印发票
                        }
                        else System.out.println("已取消退号！");
                    }
                    return;
                }
            }
        }
        // 查不到病历信息
        System.out.println("查不到该患者信息，请确认是否已挂号！");
    }

    public static void run() throws IOException {
        if (login()) {
            int choice;
            while (true) {
                System.out.print("0-挂号 1-退号 2-收费 3-退费\n选择功能：");
                choice = scanner.nextInt();
                if (choice == 0) register();
                else if (choice == 1) unregister();
                else break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        run();
    }

}