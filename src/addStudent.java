import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class addStudent extends JFrame{
    private JPanel mainPanel;
    private JButton ENROLLButton;
    private JButton EXITButton;
    private JTextField tfLname;
    private JTextField tfFname;
    private JTextField tfMname;
    private JComboBox cbYear;
    private JComboBox cbCourse;
    private JTextField tfAddress;
    private JTextField tfContact;
    private JPanel birthdatecal;
    private JLabel lblZero;
    private JLabel lblFirst;
    private JLabel lblDash;
    private JLabel lbllast;
    private JLabel lblmid;
    private JTextField tfGuardian;

    JDateChooser calendar = new JDateChooser();
    Random ran = new Random();
    int mid = ran.nextInt(9999 - 1000) + 1000;
    int last = ran.nextInt(999 - 100) + 100;

    public addStudent () {
        add(mainPanel);
        setVisible(true);
        setTitle("Enrollment Form");
        setSize(750, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        birthdatecal.add(calendar);
        lblmid.setText("" + mid);
        lbllast.setText("" + last);

        ENROLLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerStudent();
            }
        });
        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Do you wish to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_NO_OPTION) {
                    dispose();
                } else if (response == JOptionPane.NO_OPTION) {

                }
            }
        });

        tfContact.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int length = String.valueOf(tfContact.getText()).length();
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
                    tfContact.setEditable(true);
                    if (length == 0) {
                        tfContact.setEditable(true);
                    } else if (length == 10){
                        tfContact.setEditable(false);
                    } else if (length < 9) {
                    }
                } else if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    tfContact.setEditable(true);
                } else {
                    tfContact.setEditable(false);
                }
            }
        });
    }

    private void registerStudent() {
        String student_ID = lblFirst.getText() + lblmid.getText() + lblDash.getText() + lbllast.getText();
        String firstname = tfFname.getText();
        String lastname = tfLname.getText();
        String middlename = tfMname.getText();
        String year_level = String.valueOf(cbYear.getSelectedItem());
        String course = String.valueOf(cbCourse.getSelectedItem());
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = calendar.getDate();
        String birthDate = date_format.format(date);
        String address = tfAddress.getText();
        String guardian = tfGuardian.getText();
        String contact = lblZero.getText() + tfContact.getText();

        user = addStudentData(student_ID, firstname, lastname, middlename, year_level, course, birthDate, address, guardian, contact);
        if (firstname.isEmpty() || lastname.isEmpty() || address.isEmpty() || contact.isEmpty() || year_level.equals("<default>") || course.equals("<default>") || birthDate.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        } else if (user != null) {
            JOptionPane.showMessageDialog(this, "Enrollment Done", "Notice", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            teachersDashboard dashboard = new teachersDashboard();
            dashboard.show();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public UserInfo user;

    public UserInfo addStudentData(String student_ID, String firstname, String lastname, String middlename, String year_level, String course, String birthDate, String address, String guardian,String contact) {
        UserInfo user = null;
        final String DB_URL = "jdbc:mysql://localhost/student_info?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO students_tbl (student_ID, firstname, lastname, middlename, year_level, course, birthDate, address, guardian, contact) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, student_ID);
            preparedStatement.setString(2, firstname);
            preparedStatement.setString(3, lastname);
            preparedStatement.setString(4, middlename);
            preparedStatement.setString(5, year_level);
            preparedStatement.setString(6, course);
            preparedStatement.setString(7, birthDate);
            preparedStatement.setString(8, address);
            preparedStatement.setString(9, guardian);
            preparedStatement.setString(10, contact);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new UserInfo();
                user.student_ID = student_ID;
                user.firstname = firstname;
                user.lastname = lastname;
                user.middlename = middlename;
                user.year_level = year_level;
                user.course = course;
                user.birthDate = birthDate;
                user.address = address;
                user.guardian = guardian;
                user.contact = contact;
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args) {
        new addStudent();
    }
}
