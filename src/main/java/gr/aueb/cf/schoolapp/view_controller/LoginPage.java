package gr.aueb.cf.schoolapp.view_controller;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.authentication.AuthenticationManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.io.Serial;

public class LoginPage extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private final JTextField usernameField;
    private final JPasswordField passwordField;


    public LoginPage() {
        setTitle("Αυθεντικοποίηση Χρήστη");
        setIconImage(Toolkit.getDefaultToolkit().getImage(LoginPage.class.getResource("/images/eduv2.png")));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 580, 398);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblConnection = new JLabel("Σύνδεση");
        lblConnection.setBounds(250, 40, 80, 22);
        lblConnection.setFont(new Font("Tahoma", Font.BOLD, 18));
        contentPane.add(lblConnection);

        JLabel lblTextConnection = new JLabel("Παρακαλώ εισάγετε τους κωδικούς σας για να συνδεθείτε");
        lblTextConnection.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTextConnection.setBounds(80, 72, 420, 29);
        contentPane.add(lblTextConnection);

        JSeparator separator = new JSeparator();
        separator.setBounds(60, 111, 460, 2);
        contentPane.add(separator);

        JLabel lblUsername = new JLabel("Χρήστης:");
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblUsername.setBounds(130, 123, 108, 29);
        contentPane.add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(130, 158, 300, 35);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        JLabel lblPassword = new JLabel("Κωδικός:");
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPassword.setBounds(130, 198, 108, 29);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(130, 235, 300, 35);
        contentPane.add(passwordField);

        JButton btnLogin = new JButton("Σύνδεση");
        btnLogin.addActionListener(e -> {
            if (AuthenticationManager.validate(usernameField.getText(), passwordField.getPassword())) {
                Main.getDashboard().setVisible(true);
                Main.getLoginPage().setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Λάθος username ή password", "Αδυναμία Σύνδεσης", JOptionPane.ERROR_MESSAGE);
                usernameField.setText("");
                passwordField.setText("");
            }
        });
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnLogin.setBackground(new Color(0, 128, 255));
        btnLogin.setForeground(new Color(255, 255, 255));
        btnLogin.setBounds(130, 290, 300, 35);
        contentPane.add(btnLogin);

        getRootPane().setDefaultButton(btnLogin);
    }
}

