import javax.swing.*;
import java.awt.*;

public class LoginPage {

    public static void main(String[] args) {

        DBConnection.getConnection();


        JFrame frame = new JFrame("AI Smart Quiz System Login");
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel title = new JLabel("AI Smart Quiz System");
        title.setBounds(90,20,250,30);
        title.setFont(new Font("Arial",Font.BOLD,18));
        frame.add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50,80,100,25);
        frame.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(150,80,150,25);
        frame.add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50,120,100,25);
        frame.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(150,120,150,25);
        frame.add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(80,180,100,30);
        frame.add(loginBtn);
        loginBtn.addActionListener(e -> {

            String username = userField.getText();
            String password = passField.getText();

            try {
                java.sql.Connection con = DBConnection.getConnection();
                String query = "SELECT * FROM users WHERE username=? AND password=?";
                java.sql.PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);

                java.sql.ResultSet rs = pst.executeQuery();

                if(rs.next()){
                    JOptionPane.showMessageDialog(frame,"Login Successful 😎");
                    new QuizPage();

                } else {
                    JOptionPane.showMessageDialog(frame,"Invalid Username or Password");
                }

            } catch(Exception ex){
                ex.printStackTrace();
            }
        });


        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(200,180,100,30);
        frame.add(registerBtn);

        frame.setVisible(true);
        registerBtn.addActionListener(e -> {

            String username = userField.getText();
            String password = passField.getText();

            if(username.equals("") || password.equals("")){
                JOptionPane.showMessageDialog(frame,"Enter username & password");
            } else {
                try {
                    java.sql.Connection con = DBConnection.getConnection();
                    String query = "INSERT INTO users(username,password) VALUES(?,?)";
                    java.sql.PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, username);
                    pst.setString(2, password);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(frame,"Registration Successful 😎");

                } catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });

    }
}
