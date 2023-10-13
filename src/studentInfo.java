import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;

public class studentInfo extends JFrame {
    private JTable tblRecords;
    private JPanel mainPanel;
    private JTextField tfSearchID;
    private JButton searchButton;
    private JButton displayButton;
    private JButton clearAllButton;
    private JLabel lblback;
    private JButton removeStudentButton;
    private JButton refreshButton;
    private JButton updateStudentButton;
    private JComboBox cbYear;
    private JComboBox cbCourse;
    private JButton displayAllButton;

    public studentInfo() {
        add(mainPanel);
        setSize(900, 500);
        setTitle("Student Records");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tblRecords.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Student's ID", "Last Name", "First Name", "Middle Name", "Year Level", "Course", "Birth Date", "Address", "Guardian/Parent", "Contact"
                }
        ));

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) tblRecords.getModel();
                model.setRowCount(0);
                display();
            }
        });

        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) tblRecords.getModel();
                model.setRowCount(0);
            }
        });

        lblback.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                teachersDashboard backBtn = new teachersDashboard();
                backBtn.show();
            }
        });

        removeStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove();
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) tblRecords.getModel();
                model.setRowCount(0);
                display();
            }
        });
        updateStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        displayAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) tblRecords.getModel();
                model.setRowCount(0);
                displayAll();
            }
        });
    }

    private void update() {
        final String DB_URL = "jdbc:mysql://localhost/student_info?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        DefaultTableModel model = (DefaultTableModel) tblRecords.getModel();
        try {
            Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement st = con.createStatement();
            for(int i = 0; i < model.getRowCount(); i++){

                String student_ID = model.getValueAt(i, 0).toString();
                String lastname = model.getValueAt(i,1).toString();
                String firstname = model.getValueAt(i,2).toString();
                String middlename = model.getValueAt(i,3).toString();
                String year_level = model.getValueAt(i,4).toString();
                String course = model.getValueAt(i,5).toString();
                String birthDate = model.getValueAt(i,6).toString();
                String address = model.getValueAt(i,7).toString();
                String guardian = model.getValueAt(i, 8).toString();
                String contact = model.getValueAt(i, 9).toString();

                String updateQuery = "UPDATE students_tbl SET `firstname`='"+firstname+"',`lastname`='"+lastname+"',`middlename`='"+middlename+"',`birthDate`='"+birthDate+"',`year_level`='"+year_level+"',`contact`='"+contact+"',`address`='"+address+"',`guardian`='"+guardian+"',`course`='"+course+"' WHERE `student_ID`='"+student_ID+"'";

                st.addBatch(updateQuery);
            }
            int[] updatedRow = st.executeBatch();
            System.out.println(updatedRow.length);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void remove() {
        DefaultTableModel model = (DefaultTableModel) tblRecords.getModel();
        int row = tblRecords.getSelectedRow();
        String eve = model.getValueAt(row, 0).toString();
        String delRow = "delete from students_tbl where student_ID = '" + eve + "' ";

        try {
            final String DB_URL = "jdbc:mysql://localhost/student_info?serverTimezone=UTC";
            final String USERNAME = "root";
            final String PASSWORD = "";

            Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            PreparedStatement pst;
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this row?", "Delete", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_NO_OPTION) {
                pst = con.prepareStatement(delRow);
                pst.executeUpdate();
            } else if (choice == JOptionPane.NO_OPTION) {

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,  e.getMessage());
            e.printStackTrace();
        }
    }
    private void search() {
        String student_ID = tfSearchID.getText();
        String firstname = tfSearchID.getText();
        String lastname = tfSearchID.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/student_info?serverTimezone=UTC","root","");
            Statement st = con.createStatement();
            ResultSet temp = st.executeQuery("select * from students_tbl where student_ID LIKE '"+student_ID+"' OR lastname LIKE '"+lastname+"' OR firstname LIKE '"+firstname+"'");

            if(temp.next()){
                ResultSetMetaData rsd = temp.getMetaData();
                int c = rsd.getColumnCount();
                DefaultTableModel d = (DefaultTableModel)tblRecords.getModel();
                d.setRowCount(0);
                ResultSet rs = st.executeQuery("select * from students_tbl where student_ID LIKE '"+student_ID+"' OR lastname LIKE '"+lastname+"' OR firstname LIKE '"+firstname+"'");

                while(rs.next()){
                    Vector v2 = new Vector();
                    for(int i= 1; i<=c; i++){
                        v2.add(rs.getString("student_ID"));
                        v2.add(rs.getString("firstname"));
                        v2.add(rs.getString("lastname"));
                        v2.add(rs.getString("middlename"));
                        v2.add(rs.getString("year_level"));
                        v2.add(rs.getString("course"));
                        v2.add(rs.getString("birthDate"));
                        v2.add(rs.getString("address"));
                        v2.add(rs.getString("guardian"));
                        v2.add(rs.getString("contact"));
                    }
                    d.addRow(v2);
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "No Record Found");
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public void display() {
        try {
            String year = String.valueOf(cbYear.getSelectedItem());
            String cbcourse = String.valueOf(cbCourse.getSelectedItem());

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/student_info?serverTimezone=UTC","root","");
            Statement st = con.createStatement();
            ResultSet temp = st.executeQuery("select * from students_tbl where year_level = '"+year+"' and course = '"+cbcourse+"'");

            while(temp.next()) {
                String student_ID = temp.getString("student_ID");
                String firstname = temp.getString("firstname");
                String lastname = temp.getString("lastname");
                String middlename = temp.getString("middlename");
                String year_level = temp.getString("year_level");
                String course = temp.getString("course");
                String birthDate = temp.getString("birthDate");
                String address = temp.getString("address");
                String guardian = temp.getString("guardian");
                String contact = temp.getString("contact");

                String tbData [] = {student_ID, lastname, firstname, middlename, year_level, course, birthDate,address, guardian, contact};
                DefaultTableModel tblModel = (DefaultTableModel)tblRecords.getModel();

                tblModel.addRow(tbData);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void displayAll() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/student_info?serverTimezone=UTC","root","");
            Statement st = con.createStatement();
            ResultSet temp = st.executeQuery("select * from students_tbl");

            while(temp.next()) {
                String student_ID = temp.getString("student_ID");
                String firstname = temp.getString("firstname");
                String lastname = temp.getString("lastname");
                String middlename = temp.getString("middlename");
                String year_level = temp.getString("year_level");
                String course = temp.getString("course");
                String birthDate = temp.getString("birthDate");
                String address = temp.getString("address");
                String guardian = temp.getString("guardian");
                String contact = temp.getString("contact");

                String tbData [] = {student_ID, lastname, firstname, middlename, year_level, course, birthDate,address, guardian, contact};
                DefaultTableModel tblModel = (DefaultTableModel)tblRecords.getModel();

                tblModel.addRow(tbData);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new studentInfo();
    }
}
