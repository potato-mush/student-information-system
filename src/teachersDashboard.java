import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class teachersDashboard extends JFrame{
    private JButton ADDSTUDENTButton;
    private JButton VIEWRECORDButton;
    private JButton LOGOUTButton;
    private JPanel mainPanel;

    public teachersDashboard() {
        add(mainPanel);
        setVisible(true);
        setSize(400, 300);
        setTitle("Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        VIEWRECORDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                studentInfo viewBtn = new studentInfo();
                viewBtn.show();
            }
        });

        ADDSTUDENTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                addStudent addBtn = new addStudent();
                addBtn.show();
            }
        });

        LOGOUTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                login Login = new login();
                Login.show();
            }
        });
    }

    public static void main(String[] args) {
        new teachersDashboard();
    }
}
