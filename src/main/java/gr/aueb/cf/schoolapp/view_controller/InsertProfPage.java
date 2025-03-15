package gr.aueb.cf.schoolapp.view_controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.CityDAOImpl;
import gr.aueb.cf.schoolapp.dao.ICityDAO;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp.exceptions.TeacherAlreadyExistsException;
import gr.aueb.cf.schoolapp.model.City;
import gr.aueb.cf.schoolapp.service.CityServiceImpl;
import gr.aueb.cf.schoolapp.service.ICityService;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.validator.PersonValidator;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Map;

public class InsertProfPage extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private final JTextField nameField;
    private final JTextField vatField;
    private final JTextField phoneField;
    private final JTextField addressField;
    private final JTextField lastnameField;
    private final JTextField fatherNameField;
    private final JTextField mailField;
    private final JTextField streetNoField;
    private final JTextField postalCodeField;
    private final JComboBox<City> cityComboBox;
    private JLabel errorFirstname;
    private JLabel errorVat;
    private JLabel errorLastName;
    private JLabel errorFatherName;
    private JLabel errorMail;
    private JLabel errorStreetNo;
    private JLabel errorPostalCode;
    private JLabel errorPhoneNumber;
    private JLabel errorAddress;
    private List<City> cities = new ArrayList<>();


    private final ICityDAO cityDAO = new CityDAOImpl();
    private final ICityService cityService = new CityServiceImpl(cityDAO);
    private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private final ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    public InsertProfPage() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                try {
                    cities = cityService.getAll();
                    cities.forEach(cityComboBox::addItem);
                    resetForm();
                } catch (CityDAOException ex) {
                    JOptionPane.showMessageDialog(null, "Get cities fatal error. " + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        setTitle("Ποιότητα στην Εκπαίδευση");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/images/eduv2.png")));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 1200, 673);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBackground(new Color(0, 52, 117));
        header.setBounds(0, 0, 1186, 62);
        contentPane.add(header);

        JLabel lblImgHeader = new JLabel("");
        lblImgHeader.setIcon(new ImageIcon(Dashboard.class.getResource("/images/gov_logo_small.png")));
        lblImgHeader.setBounds(73, 10, 114, 42);
        header.add(lblImgHeader);

        JLabel lblUsername = new JLabel("ΝΙΚΟΛΑΟΣ ΘΕΟΦΑΝΗΣ");
        lblUsername.setForeground(new Color(255, 255, 255));
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblUsername.setBounds(950, 18, 195, 26);
        header.add(lblUsername);

        JSeparator headerSeparator = new JSeparator();
        headerSeparator.setForeground(new Color(0, 251, 255));
        headerSeparator.setBounds(0, 64, 1186, 2);
        contentPane.add(headerSeparator);

        JSeparator footerSeparator = new JSeparator();
        footerSeparator.setForeground(new Color(0, 251, 255));
        footerSeparator.setBounds(0, 550, 1186, 2);
        contentPane.add(footerSeparator);

        JPanel footer = new JPanel();
        footer.setLayout(null);
        footer.setBounds(0, 560, 1186, 90);
        contentPane.add(footer);

        JLabel lblManual = new JLabel("Εγχειρίδιο Χρήσης");
        lblManual.setForeground(new Color(0, 52, 117));
        lblManual.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblManual.setBounds(194, 10, 136, 30);
        footer.add(lblManual);

        JLabel lblOftenQuestions = new JLabel("Συχνές Ερωτήσεις");
        lblOftenQuestions.setForeground(new Color(0, 52, 117));
        lblOftenQuestions.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblOftenQuestions.setBounds(524, 10, 136, 30);
        footer.add(lblOftenQuestions);

        JLabel lblCitizenSupport = new JLabel("Υποστήριξη Πολιτών");
        lblCitizenSupport.setForeground(new Color(0, 52, 117));
        lblCitizenSupport.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCitizenSupport.setBounds(854, 10, 136, 30);
        footer.add(lblCitizenSupport);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 74, 1186, 478);
        contentPane.add(mainPanel);
        mainPanel.setLayout(null);

        JLabel lblDataProf = new JLabel("Στοιχεία Εκπαιδευτή");
        lblDataProf.setBounds(470, 5, 259, 29);
        lblDataProf.setFont(new Font("Tahoma", Font.BOLD, 24));
        mainPanel.add(lblDataProf);

        JLabel lblFirstname = new JLabel("'Ονομα*");
        lblFirstname.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblFirstname.setBounds(130, 62, 80, 13);
        mainPanel.add(lblFirstname);

        nameField = new JTextField();
        nameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String firstname = nameField.getText().trim();
                errorFirstname.setText(firstname.isEmpty() ? "Το όνομα είναι υποχρεωτικό" : "");
            }

        });
        nameField.setBounds(220, 50, 314, 40);
        mainPanel.add(nameField);
        nameField.setColumns(10);

        JLabel lblVat = new JLabel("ΑΦΜ*");
        lblVat.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblVat.setBounds(130, 132, 80, 13);
        mainPanel.add(lblVat);

        JLabel lblPhoneNumber = new JLabel("Τηλέφωνο*");
        lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPhoneNumber.setBounds(130, 202, 80, 13);
        mainPanel.add(lblPhoneNumber);

        vatField = new JTextField();
        vatField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String vat = vatField.getText().trim();
                errorVat.setText(vat.isEmpty() ? "Το ΑΦΜ είναι υποχρεωτικό" : "");
            }
        });
        vatField.setBounds(220, 120, 314, 40);
        mainPanel.add(vatField);
        vatField.setColumns(10);

        phoneField = new JTextField();
        phoneField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String phoneNumber = phoneField.getText().trim();
                errorPhoneNumber.setText(phoneNumber.isEmpty() ? "Ο τηλεφωνικός αριθμός είναι υποχρεωτικός" : "");
            }
        });
        phoneField.setColumns(10);
        phoneField.setBounds(220, 190, 314, 40);
        mainPanel.add(phoneField);

        JLabel lblAddress = new JLabel("Διεύθυνση*");
        lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblAddress.setBounds(130, 272, 80, 13);
        mainPanel.add(lblAddress);

        addressField = new JTextField();
        addressField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String address = addressField.getText().trim();
                errorAddress.setText(address.isEmpty() ? "Η διεύθυνση είναι υποχρεωτικη" : "");
            }
        });
        addressField.setColumns(10);
        addressField.setBounds(220, 260, 314, 40);
        mainPanel.add(addressField);

        JLabel lblCity = new JLabel("Πόλη*");
        lblCity.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCity.setBounds(130, 343, 80, 13);
        mainPanel.add(lblCity);

        cityComboBox = new JComboBox<>();
        cityComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
        cityComboBox.setBounds(220, 330, 314, 40);
        mainPanel.add(cityComboBox);

        JButton btnCloseApp = new JButton("Κλείσιμο");
        btnCloseApp.addActionListener(e -> {
            Main.getDashboard().setEnabled(true);
            Main.getInsertProfPage().setVisible(false);
        });
        btnCloseApp.setBackground(new Color(192, 192, 192));
        btnCloseApp.setForeground(new Color(0, 0, 0));
        btnCloseApp.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCloseApp.setBounds(220, 395, 314, 50);
        mainPanel.add(btnCloseApp);

        JLabel lblLastname = new JLabel("Επώνυμο*");
        lblLastname.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblLastname.setBounds(636, 63, 80, 13);
        mainPanel.add(lblLastname);

        lastnameField = new JTextField();
        lastnameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String lastname = lastnameField.getText().trim();
                errorLastName.setText(lastname.isEmpty() ? "Το επώνυμο είναι υποχρεωτικό" : "");
            }
        });
        lastnameField.setColumns(10);
        lastnameField.setBounds(727, 50, 314, 40);
        mainPanel.add(lastnameField);

        JLabel lblFatherName = new JLabel("Πατρώνυμο*");
        lblFatherName.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblFatherName.setBounds(636, 133, 80, 13);
        mainPanel.add(lblFatherName);

        fatherNameField = new JTextField();
        fatherNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String fatherName = fatherNameField.getText().trim();
                errorFatherName.setText(fatherName.isEmpty() ? "Το πατρώνυμο είναι υποχρεωτικό" : "");
            }
        });
        fatherNameField.setColumns(10);
        fatherNameField.setBounds(727, 120, 314, 40);
        mainPanel.add(fatherNameField);

        JLabel lblMail = new JLabel("e-mail*");
        lblMail.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMail.setBounds(636, 203, 80, 13);
        mainPanel.add(lblMail);

        mailField = new JTextField();
        mailField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String mail = mailField.getText().trim();
                errorMail.setText(mail.isEmpty() ? "Το email είναι υποχρεωτικό" : "");
            }
        });
        mailField.setColumns(10);
        mailField.setBounds(727, 190, 314, 40);
        mainPanel.add(mailField);

        JLabel lblStreetNo = new JLabel("Αριθμός*");
        lblStreetNo.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblStreetNo.setBounds(636, 273, 80, 13);
        mainPanel.add(lblStreetNo);

        streetNoField = new JTextField();
        streetNoField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String streetNo = streetNoField.getText().trim();
                errorStreetNo.setText(streetNo.isEmpty() ? "Ο αριθμός διεύθυνσης είναι υποχρεωτικός" : "");
            }
        });
        streetNoField.setColumns(10);
        streetNoField.setBounds(727, 260, 314, 40);
        mainPanel.add(streetNoField);

        JLabel lblpostalCode = new JLabel("ΤΚ*");
        lblpostalCode.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblpostalCode.setBounds(636, 344, 80, 13);
        mainPanel.add(lblpostalCode);

        postalCodeField = new JTextField();
        postalCodeField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String postalCode = postalCodeField.getText().trim();
                errorPostalCode.setText(postalCode.isEmpty() ? "Ο ταχυδρομικός κώδικας είναι υποχρεωτικός" : "");
            }
        });
        postalCodeField.setColumns(10);
        postalCodeField.setBounds(727, 331, 314, 40);
        mainPanel.add(postalCodeField);

        JButton btnSubmit = new JButton("Υποβολή");
        btnSubmit.addActionListener(e -> {
            TeacherReadOnlyDTO teacherReadOnlyDTO;
//                // Data Binding
            TeacherInsertDTO insertDTO = doDataBinding();
//                // Data Validation
            Map<String, String> errors = PersonValidator.validate(insertDTO);
            if (!errors.isEmpty()){
                errorFirstname.setText(errors.getOrDefault("firstname", ""));
                errorVat.setText(errors.getOrDefault("vat", ""));
                errorLastName.setText(errors.getOrDefault("lastname", ""));
                errorFatherName.setText(errors.getOrDefault("fatherName", ""));
                errorMail.setText(errors.getOrDefault("email", ""));
                errorStreetNo.setText(errors.getOrDefault("streetNumber", ""));
                errorPostalCode.setText(errors.getOrDefault("zipCode", ""));
                errorPhoneNumber.setText(errors.getOrDefault("phoneNumber", ""));
                errorAddress.setText(errors.getOrDefault("street", ""));
                return;
            }
            // Insert
            try {
                teacherReadOnlyDTO = teacherService.insertTeacher(insertDTO);
                Main.getInsertProfPage().setEnabled(false);
                Main.getInsertProfSuccessPage().setUuidText(teacherReadOnlyDTO.getUuid());
                Main.getInsertProfSuccessPage().setVisible(true);
                resetForm();
            } catch (TeacherDAOException ex1) {
                JOptionPane.showMessageDialog(null,  "Insertion error", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (TeacherAlreadyExistsException ex2) {
                JOptionPane.showMessageDialog(null, "Teacher already exists", "Error", JOptionPane.ERROR_MESSAGE);
            }
//
        });
        btnSubmit.setForeground(new Color(255, 255, 255));
        btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSubmit.setBackground(new Color(64, 128, 128));
        btnSubmit.setBounds(727, 395, 314, 50);
        mainPanel.add(btnSubmit);

        errorFirstname = new JLabel("");
        errorFirstname.setFont(new Font("Tahoma", Font.PLAIN, 12));
        errorFirstname.setForeground(new Color(255, 0, 0));
        errorFirstname.setBounds(220, 91, 314, 22);
        mainPanel.add(errorFirstname);

        errorVat = new JLabel("");
        errorVat.setForeground(new Color(255, 0, 0));
        errorVat.setFont(new Font("Tahoma", Font.PLAIN, 12));
        errorVat.setBounds(220, 159, 314, 22);
        mainPanel.add(errorVat);

        errorLastName = new JLabel("");
        errorLastName.setForeground(Color.RED);
        errorLastName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        errorLastName.setBounds(727, 91, 314, 22);
        mainPanel.add(errorLastName);

        errorFatherName = new JLabel("");
        errorFatherName.setForeground(Color.RED);
        errorFatherName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        errorFatherName.setBounds(727, 159, 314, 22);
        mainPanel.add(errorFatherName);

        errorMail = new JLabel("");
        errorMail.setForeground(Color.RED);
        errorMail.setFont(new Font("Tahoma", Font.PLAIN, 12));
        errorMail.setBounds(727, 228, 314, 22);
        mainPanel.add(errorMail);

        errorStreetNo = new JLabel("");
        errorStreetNo.setForeground(Color.RED);
        errorStreetNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        errorStreetNo.setBounds(727, 299, 314, 22);
        mainPanel.add(errorStreetNo);

        errorPostalCode = new JLabel("");
        errorPostalCode.setForeground(Color.RED);
        errorPostalCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
        errorPostalCode.setBounds(727, 370, 314, 22);
        mainPanel.add(errorPostalCode);

        errorPhoneNumber = new JLabel("");
        errorPhoneNumber.setForeground(Color.RED);
        errorPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
        errorPhoneNumber.setBounds(220, 228, 314, 22);
        mainPanel.add(errorPhoneNumber);

        errorAddress = new JLabel("");
        errorAddress.setForeground(Color.RED);
        errorAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
        errorAddress.setBounds(220, 299, 314, 22);
        mainPanel.add(errorAddress);
    }

    private void resetForm() {
        nameField.setText("");
        vatField.setText("");
        phoneField.setText("");
        addressField.setText("");
        lastnameField.setText("");
        fatherNameField.setText("");
        mailField.setText("");
        streetNoField.setText("");
        postalCodeField.setText("");
        cityComboBox.setSelectedIndex(0);
        errorFirstname.setText("");
        errorVat.setText("");
        errorLastName.setText("");
        errorFatherName.setText("");
        errorMail.setText("");
        errorStreetNo.setText("");
        errorPostalCode.setText("");
        errorPhoneNumber.setText("");
        errorAddress.setText("");
    }

    private TeacherInsertDTO doDataBinding() {
        String firstname = nameField.getText().trim();
        String lastname = lastnameField.getText().trim();
        String vat = vatField.getText().trim();
        String phoneNumber = phoneField.getText().trim();
        String address = addressField.getText().trim();
        String fatherName = fatherNameField.getText().trim();
        String mail = mailField.getText().trim();
        String streetNo = streetNoField.getText().trim();
        String postalCode = postalCodeField.getText().trim();
        City selectedCity = (City) cityComboBox.getSelectedItem();
        int cityId = (selectedCity != null) ? selectedCity.getId() : 1;
        return new TeacherInsertDTO(firstname, lastname, vat, fatherName, phoneNumber, mail, address, streetNo, cityId, postalCode);
    }
}

