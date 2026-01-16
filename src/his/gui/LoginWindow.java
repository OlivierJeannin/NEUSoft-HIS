package his.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame implements ActionListener {
    private final Container container;

    private final JLabel usernameLabel;
    private final JTextField usernameField;

    private final JLabel passwordLabel;
    private final JPasswordField passwordField;

    private final JButton loginButton;
    private final JButton cancelButton;

    public LoginWindow() {
        container = getContentPane();

        usernameLabel = new JLabel("用户名：");
        usernameField = new JTextField();

        passwordLabel = new JLabel("密码：");
        passwordField = new JPasswordField();

        loginButton = new JButton("登陆");
        cancelButton = new JButton("退出");

        setLayout();
        addComponents();
        registerActionListeners();
    }

    private void setLayout() {
        this.setLayout(null);
        usernameLabel.setBounds(50, 150, 100, 30);
        usernameField.setBounds(150, 150, 150, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        passwordField.setBounds(150, 220, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        cancelButton.setBounds(200, 300, 100, 30);
    }

    private void addComponents() {
        container.add(usernameLabel);
        container.add(usernameField);
        container.add(passwordLabel);
        container.add(passwordField);
        container.add(loginButton);
        container.add(cancelButton);
    }

    private void registerActionListeners() {
        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

//    todo 考虑把 ActionListener 放进 guictrl 模块
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            System.out.println(username + ":" + password);
        }
        if (e.getSource() == cancelButton) {
            this.dispose();
        }
    }

}
