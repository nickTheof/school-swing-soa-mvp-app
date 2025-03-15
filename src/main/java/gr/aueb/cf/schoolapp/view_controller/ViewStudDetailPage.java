package gr.aueb.cf.schoolapp.view_controller;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.*;
import gr.aueb.cf.schoolapp.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.dto.StudentReadOnlyDTO;
import gr.aueb.cf.schoolapp.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolapp.model.City;
import gr.aueb.cf.schoolapp.service.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class ViewStudDetailPage extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel kwdikosText;
    private JLabel firstnameText;
    private JLabel lastnameText;
    private JLabel vatText;
    private JLabel fathernameText;
    private JLabel phoneNumText;
    private JLabel emailText;
    private JLabel streetText;
    private JLabel streetNumText;
    private JLabel cityText;
    private JLabel zipcodeText;

    private List<City> cities = new ArrayList<>();
    private final ICityDAO cityDao = new CityDAOImpl();
    private final ICityService cityService = new CityServiceImpl(cityDao);

    private final IStudentDAO studentDAO= new StudentDAOImpl();
    private final IStudentService studentService = new StudentServiceImpl(studentDAO);

    public ViewStudDetailPage() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                try {
                    cities = cityService.getAll();
                } catch (CityDAOException ex) {
                    JOptionPane.showMessageDialog(null, "Get cities fatal error.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                fetchStudentFromDatabase(Main.getViewStudsPage().getSelectedId());
            }
        });
        setTitle("Ποιότητα στην Εκπαίδευση");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/images/eduv2.png")));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 993, 673);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBackground(new Color(0, 52, 117));
        header.setBounds(0, 0, 986, 62);
        contentPane.add(header);

        JLabel lblImgHeader = new JLabel("");
        lblImgHeader.setIcon(new ImageIcon(Dashboard.class.getResource("/images/gov_logo_small.png")));
        lblImgHeader.setBounds(73, 10, 114, 42);
        header.add(lblImgHeader);

        JLabel lblUsername = new JLabel("ΝΙΚΟΛΑΟΣ ΘΕΟΦΑΝΗΣ");
        lblUsername.setForeground(new Color(255, 255, 255));
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblUsername.setBounds(750, 18, 195, 26);
        header.add(lblUsername);

        JSeparator headerSeparator = new JSeparator();
        headerSeparator.setForeground(new Color(0, 251, 255));
        headerSeparator.setBounds(0, 62, 986, 1);
        contentPane.add(headerSeparator);

        JSeparator footerSeparator = new JSeparator();
        footerSeparator.setForeground(new Color(0, 251, 255));
        footerSeparator.setBounds(0, 560, 986, 1);
        contentPane.add(footerSeparator);

        JPanel footer = new JPanel();
        footer.setLayout(null);
        footer.setBounds(0, 560, 986, 90);
        contentPane.add(footer);

        JLabel lblManual = new JLabel("Εγχειρίδιο Χρήσης");
        lblManual.setForeground(new Color(0, 52, 117));
        lblManual.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblManual.setBounds(144, 10, 136, 30);
        footer.add(lblManual);

        JLabel lblOftenQuestions = new JLabel("Συχνές Ερωτήσεις");
        lblOftenQuestions.setForeground(new Color(0, 52, 117));
        lblOftenQuestions.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblOftenQuestions.setBounds(424, 10, 136, 30);
        footer.add(lblOftenQuestions);

        JLabel lblCitizenSupport = new JLabel("Υποστήριξη Πολιτών");
        lblCitizenSupport.setForeground(new Color(0, 52, 117));
        lblCitizenSupport.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCitizenSupport.setBounds(704, 10, 136, 30);
        footer.add(lblCitizenSupport);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(-17, 62, 986, 478);
        contentPane.add(mainPanel);
        mainPanel.setLayout(null);

        JLabel lblProfRequestData = new JLabel("Αίτηση Σπουδαστή");
        lblProfRequestData.setBounds(363, 5, 259, 29);
        lblProfRequestData.setFont(new Font("Tahoma", Font.BOLD, 20));
        mainPanel.add(lblProfRequestData);

        JLabel lblKwdikos = new JLabel("Κωδικός Σπουδαστή");
        lblKwdikos.setBounds(302, 44, 180, 27);
        mainPanel.add(lblKwdikos);

        kwdikosText = new JLabel("Κωδικός Σπουδαστή");
        kwdikosText.setBounds(509, 46, 280, 27);
        mainPanel.add(kwdikosText);

        JLabel lblFirstname = new JLabel("Όνομα Σπουδαστή");
        lblFirstname.setBounds(302, 69, 180, 27);
        mainPanel.add(lblFirstname);

        firstnameText = new JLabel("Όνομα Σπουδαστή");
        firstnameText.setBounds(509, 71, 180, 27);
        mainPanel.add(firstnameText);

        JLabel lblLastname = new JLabel("Επώνυμο Σπουδαστή");
        lblLastname.setBounds(302, 96, 180, 27);
        mainPanel.add(lblLastname);

        lastnameText = new JLabel("Επώνυμο Σπουδαστή");
        lastnameText.setBounds(509, 98, 180, 27);
        mainPanel.add(lastnameText);

        JLabel lblVat = new JLabel("ΑΦΜ Σπουδαστή");
        lblVat.setBounds(302, 147, 180, 27);
        mainPanel.add(lblVat);

        vatText = new JLabel("ΑΦΜ Σπουδαστή");
        vatText.setBounds(509, 147, 180, 27);
        mainPanel.add(vatText);

        JLabel lblFathername = new JLabel("Πατρώνυμο Σπουδαστή");
        lblFathername.setBounds(302, 167, 180, 27);
        mainPanel.add(lblFathername);

        JLabel lblPhoneNum = new JLabel("Τηλέφωνο Σπουδαστή");
        lblPhoneNum.setBounds(302, 214, 180, 27);
        mainPanel.add(lblPhoneNum);

        JLabel lblEmail = new JLabel("e-mail Σπουδαστή");
        lblEmail.setBounds(302, 241, 180, 27);
        mainPanel.add(lblEmail);

        JLabel lblStreet = new JLabel("Διεύθυνση Σπουδαστή");
        lblStreet.setBounds(302, 265, 180, 27);
        mainPanel.add(lblStreet);

        fathernameText = new JLabel("Πατρώνυμο Σπουδαστή");
        fathernameText.setBounds(509, 167, 180, 27);
        mainPanel.add(fathernameText);

        phoneNumText = new JLabel("Τηλέφωνο Σπουδαστή");
        phoneNumText.setBounds(509, 214, 180, 27);
        mainPanel.add(phoneNumText);

        emailText = new JLabel("email Σπουδαστή");
        emailText.setBounds(509, 241, 180, 27);
        mainPanel.add(emailText);

        streetText = new JLabel("Διεύθυνση Σπουδαστή");
        streetText.setBounds(509, 265, 180, 27);
        mainPanel.add(streetText);

        JLabel lblStreetNum = new JLabel("Αριθμός Διεύθυνσης");
        lblStreetNum.setBounds(302, 289, 180, 27);
        mainPanel.add(lblStreetNum);

        streetNumText = new JLabel("Αριθμός Διεύθυνσης");
        streetNumText.setBounds(509, 289, 172, 27);
        mainPanel.add(streetNumText);

        JLabel lblCity = new JLabel("Πόλη Σπουδαστή");
        lblCity.setBounds(302, 337, 180, 27);
        mainPanel.add(lblCity);

        cityText = new JLabel("Πόλη Σπουδαστή");
        cityText.setBounds(509, 337, 180, 27);
        mainPanel.add(cityText);

        zipcodeText = new JLabel("ΤΚ");
        zipcodeText.setBounds(509, 361, 180, 27);
        mainPanel.add(zipcodeText);

        JLabel lblZipcode = new JLabel("ΤΚ");
        lblZipcode.setBounds(302, 361, 180, 27);
        mainPanel.add(lblZipcode);

        JButton btnClose = new JButton("Κλείσιμο");
        btnClose.addActionListener(e -> {
            Main.getViewStudsPage().setEnabled(true);
            Main.getViewStudDetailPage().setVisible(false);
        });

        btnClose.setBounds(635, 390, 153, 54);
        mainPanel.add(btnClose);

        JSeparator separator = new JSeparator();
        separator.setBounds(194, 133, 597, 1);
        mainPanel.add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(194, 201, 597, 1);
        mainPanel.add(separator_1);

        JSeparator separator_1_1 = new JSeparator();
        separator_1_1.setBounds(194, 324, 597, 1);
        mainPanel.add(separator_1_1);
    }

    private void fetchStudentFromDatabase(Long id) {
        try {
            StudentReadOnlyDTO dto = studentService.getStudentById(id);

            kwdikosText.setText(dto.getUuid());
            firstnameText.setText(dto.getFirstname());
            lastnameText.setText(dto.getLastname());
            vatText.setText(dto.getVat());
            fathernameText.setText(dto.getFatherName());
            phoneNumText.setText(dto.getPhoneNum());
            emailText.setText(dto.getEmail());
            streetText.setText(dto.getStreet());
            streetNumText.setText(dto.getStreetNum());
            int cityIdFromDB = dto.getCityId();
            cities.stream()
                    .filter(city -> city.getId() == cityIdFromDB)
                    .findFirst().ifPresent(selectedCity -> cityText.setText(selectedCity.getName()));
            zipcodeText.setText(dto.getZipcode());
        } catch (StudentDAOException | StudentNotFoundException e) {
//            e.printStackTrace();
            JOptionPane.showMessageDialog(null,  "Select error in fetch student", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}