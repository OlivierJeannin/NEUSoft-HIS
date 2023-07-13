import javax.swing.*;

/**
 * 系统入口
 */
public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("First Window");
        frame.setBounds(100, 500, 80, 200);
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel=new JPanel();

        JLabel label=new JLabel("Name:");
        label.setSize(100, 60);

        JTextField field=new JTextField(20);

        JButton button = new JButton("OK");
        button.setLocation(20, 56);
        button.setSize(20, 56);

        frame.add(panel);
        panel.add(label);
        panel.add(field);
        panel.add(button);

        frame.setVisible(true);
    }

}
