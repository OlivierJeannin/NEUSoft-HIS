package his.user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

/**
 * 系统用户
 */
public abstract class User {
    static String loginID;  // 当前用户的id

    static Scanner scanner;

    /**
     * 登录
     *
     * @return 是否登录成功
     */
    public static boolean login() throws IOException {
        System.out.println("----------登录----------");
        scanner = new Scanner(System.in);

        System.out.print("账号：");
        String inputID = scanner.nextLine();
        System.out.print("密码：");
        String inputPassword = scanner.nextLine();

        Reader fileReader = new FileReader("./resource/user.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        try {
            while (bufferedReader.ready()) {
                String[] userInfo = bufferedReader.readLine().split(" ");
                if (userInfo[0].equals(inputID) && userInfo[1].equals(inputPassword)) {
                    loginID = userInfo[0];
                    System.out.println("登录成功！");
                    return true;
                }
            }
        }
        finally {
            bufferedReader.close();
            fileReader.close();
        }
        System.out.println("登录失败！");
        return false;
    }

}
