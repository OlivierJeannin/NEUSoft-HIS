package his.gui;

import javax.swing.*;

public class GUI {

    public static void showLoginWindow() {
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.setTitle("用户登陆");
        loginWindow.setBounds(10, 10, 370, 600);
        loginWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginWindow.setVisible(true);
    }

    public static void main(String[] args) {
        showLoginWindow();
    }

}
