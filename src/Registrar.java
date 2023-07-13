import java.io.*;

/**
 * 挂号收费员
 *
 * <p>登录账号后，选择执行挂号、退号、收费、退费、打印发票流程。</p>
 */
class Registrar extends User {

    /**
     * 从文件中读取下一个病历号
     */
    public static String readNextPatientID() throws IOException {
        File f = new File("./resource/next_patient_id.txt");
        Reader fileReader = new FileReader(f);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String nextPatientID;
        try {
            nextPatientID = bufferedReader.readLine();
        }
        finally {
            bufferedReader.close();
            fileReader.close();
        }
        return nextPatientID;
    }

    /**
     * 将下一个病历号写入文件
     */
    public static void writeNextPatientID(String nextPatientID) throws IOException {
        File f = new File("./resource/next_patient_id.txt");
        Writer fileWriter = new FileWriter(f);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try {
            bufferedWriter.write(nextPatientID);
        }
        finally {
            bufferedWriter.close();
            fileWriter.close();
        }
    }

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
        Reader fileReader = new FileReader("./resource/register_level");
        // todo 数据文件格式已经改变，处理流程也要改变
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        try {
            while (bufferedReader.ready()) {
                String[] classificationInfo = bufferedReader.readLine().split(" ");
                if (classificationInfo[0].equals(registerLevel)) {
                    fee += Integer.parseInt(classificationInfo[2]);
                    break;
                }
            }
        }
        finally {
            bufferedReader.close();
            fileReader.close();
        }

        return needHistoryBook ? (fee + 1) : fee;
    }


//    /**
//     * todo 打印发票
//     */
//    public static void printReceipt(String[] items, double received) {
//        float sum = 0;
//        System.out.println("----------发票联----------");
//        for (String item : items) {
//            String[] itemContent = item.split(" ");
//            System.out.println(itemContent[0] + "：￥" + itemContent[1]);
//            sum += Float.parseFloat(itemContent[1]);
//        }
//        System.out.print(
//                ">>>>>>>>>>>>>>>>>>>>\n" +
//                        "总计：￥" + String.format("%.2f", sum) + "\n" +
//                        "实收：￥" + String.format("%.2f", received) + "\n" +
//                        "找零：￥" + String.format("%.2f", received - sum) + "\n" +
//                        "-------------------------\n"
//        );
//    }


    /**
     * 挂号登记
     *
     * <p>输入患者基本信息，收取挂号费、打印发票，将患者病历写入文件。</p>
     *
     * <p>输入的信息格式：“姓名 性别 身份证号 挂号级别 科室 医生 是否需要病历本”</p>
     */
    public static void register() throws IOException {
        System.out.println("----------挂号登记----------");

        // 输入患者信息
        // todo 以“名值对”的格式输入信息，再将值匹配到对应的位置上
        // todo 校验输入值
        String name = scanner.next();
        String gender = scanner.next();
        String id = scanner.next();
        String registerLevel = scanner.next();
        String department = scanner.next();
        String doctorID = scanner.next();
        boolean needHistoryBook = scanner.nextBoolean();

        // 填写发票信息
        String registerFee = moneyInt2DecimalString(calculateRegisterFee(registerLevel, needHistoryBook));
        System.out.println("应收挂号费：￥" + registerFee);

//        System.out.println("实收：");
//        double received = scanner.nextDouble();

        System.out.print("确认：");
        if (scanner.next().equals("y")) {
//            printReceipt(new String[]{"挂号费 " + registerFee}, received);  // 打印发票
            // 分配病历号
            String patientID = readNextPatientID();
            writeNextPatientID(String.format("%06d", Integer.parseInt(patientID) + 1));  // 更新 nextPatientID
            // 挂号信息写入文件
            File f = new File("./resource/patient/" + patientID + "_" + doctorID);
            Writer fileWriter = new FileWriter(f);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            try {
                bufferedWriter.write(name + ' ' + gender + ' ' + id + ' ' + registerLevel + ' ' + department + ' ' + needHistoryBook + ' ' + false + '\n');
                bufferedWriter.flush();
            }
            finally {
                bufferedWriter.close();
                fileWriter.close();
            }
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


    public static void main(String[] args) throws IOException {
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

}