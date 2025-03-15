package gr.aueb.cf.schoolapp.view_controller;

import gr.aueb.cf.schoolapp.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ItemEvent;

import java.io.Serial;

public class LandingPage extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;


    public LandingPage() {
        setTitle("Ποιότητα στην Εκπαίδευση");
        setIconImage(Toolkit.getDefaultToolkit().getImage(LandingPage.class.getResource("/images/eduv2.png")));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 901, 404);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel header = new JPanel();
        header.setBackground(new Color(0, 52, 117));
        header.setBounds(0, 0, 887, 62);
        contentPane.add(header);
        header.setLayout(null);

        JLabel lblImgHeader = new JLabel("");
        lblImgHeader.setIcon(new ImageIcon(LandingPage.class.getResource("/images/gov_logo_small.png")));
        lblImgHeader.setBounds(73, 10, 114, 42);
        header.add(lblImgHeader);

        JSeparator headerSeparator = new JSeparator();
        headerSeparator.setForeground(new Color(0, 251, 255));
        headerSeparator.setBounds(0, 63, 887, 2);
        contentPane.add(headerSeparator);

        JLabel lblAuthRequired = new JLabel("Απαιτείται ταυτοποίηση");
        lblAuthRequired.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblAuthRequired.setBounds(35, 70, 311, 50);
        contentPane.add(lblAuthRequired);

        JLabel lblConnectionRequired = new JLabel("Για να προχωρήσετε πρέπει να συνδεθείτε");
        lblConnectionRequired.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblConnectionRequired.setBounds(35, 130, 336, 30);
        contentPane.add(lblConnectionRequired);

        JButton btnConnection = new JButton("Σύνδεση");
        btnConnection.addActionListener(e -> {
            Main.getLoginPage().setVisible(true);
            Main.getLandingPage().setVisible(false);
        });
        btnConnection.setBackground(new Color(0, 128, 0));
        btnConnection.setForeground(new Color(255, 255, 255));
        btnConnection.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnConnection.setEnabled(false);
        btnConnection.setBounds(35, 215, 152, 40);
        contentPane.add(btnConnection);

        JCheckBox checkBoxPrivacy = new JCheckBox("Δηλώνω ότι αποδέχομαι τη");
        checkBoxPrivacy.addItemListener(e -> btnConnection.setEnabled(e.getStateChange() == ItemEvent.SELECTED));
        checkBoxPrivacy.setFont(new Font("Tahoma", Font.PLAIN, 14));
        checkBoxPrivacy.setBounds(35, 170, 196, 30);
        contentPane.add(checkBoxPrivacy);

        JLabel lblPrivacy = new JLabel("Δήλωση Ιδιωτικότητας");
        lblPrivacy.setForeground(new Color(0, 0, 255));
        lblPrivacy.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPrivacy.setBounds(232, 170, 174, 30);
        contentPane.add(lblPrivacy);



        JSeparator footerSeparator = new JSeparator();
        footerSeparator.setForeground(new Color(0, 251, 255));
        footerSeparator.setBounds(0, 275, 887, 2);
        contentPane.add(footerSeparator);

        JPanel footer = new JPanel();
        footer.setBounds(0, 280, 887, 90);
        contentPane.add(footer);
        footer.setLayout(null);

        JLabel lblManual = new JLabel("Εγχειρίδιο Χρήσης");
        lblManual.setForeground(new Color(0, 52, 117));
        lblManual.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblManual.setBounds(119, 30, 136, 30);
        footer.add(lblManual);

        JLabel lblOftenQuestions = new JLabel("Συχνές Ερωτήσεις");
        lblOftenQuestions.setForeground(new Color(0, 52, 117));
        lblOftenQuestions.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblOftenQuestions.setBounds(374, 30, 136, 30);
        footer.add(lblOftenQuestions);

        JLabel lblCitizenSupport = new JLabel("Υποστήριξη Πολιτών");
        lblCitizenSupport.setForeground(new Color(0, 52, 117));
        lblCitizenSupport.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCitizenSupport.setBounds(629, 30, 136, 30);
        footer.add(lblCitizenSupport);
    }
}

