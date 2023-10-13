import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame {
    private JPanel mainPanel;
    private JButton LOGINButton;
    private JTextField tfUsername;
    private JPasswordField tfPassword;

    public login() {
        add(mainPanel);
        setVisible(true);
        setSize(350, 350);
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = tfUsername.getText();
                String pass = String.valueOf(tfPassword.getPassword());

                if (user.equals("admin") && pass.equals("admin")) {
                    dispose();
                    teachersDashboard dashboard = new teachersDashboard();
                    dashboard.show();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password!", "Invalid Login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        new login();
    }
}
