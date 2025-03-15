package gr.aueb.cf.schoolapp.view_controller;


import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.CityDAOImpl;
import gr.aueb.cf.schoolapp.dao.ICityDAO;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp.service.CityServiceImpl;
import gr.aueb.cf.schoolapp.service.ICityService;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serial;
import java.util.List;

public class ViewProfsPage extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private final JTextField lastnameField;
    private final JTable table;
    private final DefaultTableModel model;
    private Long selectedId;

    private final ICityDAO cityDAO = new CityDAOImpl();
    private final ICityService cityService = new CityServiceImpl(cityDAO);
    private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private final ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);


    public ViewProfsPage() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                clearForm();
                buildTable();
            }
            @Override
            public void windowActivated(WindowEvent e) {
                buildTable();
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
        lblManual.setBounds(190, 10, 136, 30);
        footer.add(lblManual);

        JLabel lblOftenQuestions = new JLabel("Συχνές Ερωτήσεις");
        lblOftenQuestions.setForeground(new Color(0, 52, 117));
        lblOftenQuestions.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblOftenQuestions.setBounds(520, 10, 136, 30);
        footer.add(lblOftenQuestions);

        JLabel lblCitizenSupport = new JLabel("Υποστήριξη Πολιτών");
        lblCitizenSupport.setForeground(new Color(0, 52, 117));
        lblCitizenSupport.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCitizenSupport.setBounds(850, 10, 136, 30);
        footer.add(lblCitizenSupport);

        JButton viewBtn = new JButton("Προβολή");
        viewBtn.setEnabled(false);
        JButton updateBtn = new JButton("Επεξεργασία");
        updateBtn.setEnabled(false);
        JButton btnDelete = new JButton("Διαγραφή");
        btnDelete.setEnabled(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 74, 1186, 478);
        contentPane.add(mainPanel);
        mainPanel.setLayout(null);

        JLabel lblDataProf = new JLabel("Μητρώο Εκπαιδευτών");
        lblDataProf.setBounds(470, 5, 288, 29);
        lblDataProf.setFont(new Font("Tahoma", Font.BOLD, 24));
        mainPanel.add(lblDataProf);

        JLabel lblLastname = new JLabel("Επώνυμο");
        lblLastname.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblLastname.setBounds(120, 60, 80, 40);
        mainPanel.add(lblLastname);

        lastnameField = new JTextField();
        lastnameField.setBounds(210, 60, 240, 40);
        mainPanel.add(lastnameField);
        lastnameField.setColumns(10);

        JButton btnSearch = new JButton("Αναζήτηση");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateBtn.setEnabled(false);
                btnDelete.setEnabled(false);
                viewBtn.setEnabled(false);
                buildTable();
            }
        });
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSearch.setBackground(new Color(0, 128, 0));
        btnSearch.setForeground(new Color(255, 255, 255));
        btnSearch.setBounds(470, 60, 150, 40);
        mainPanel.add(btnSearch);

        JButton btnCleanUp = new JButton("Εκκαθάριση");
        btnCleanUp.addActionListener(e -> {
            updateBtn.setEnabled(false);
            btnDelete.setEnabled(false);
            viewBtn.setEnabled(false);
            clearForm();
            buildTable();
        });
        btnCleanUp.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnCleanUp.setForeground(new Color(192, 192, 192));
        btnCleanUp.setBounds(640, 60, 150, 40);
        mainPanel.add(btnCleanUp);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Check if the selection is still adjusting
                if (!e.getValueIsAdjusting()) {
                    // Get the selected row index
                    int selectedRow = table.getSelectedRow();
                    // Check if a row is selected
                    if (selectedRow != -1) {
                        // Get data from the selected row
                        updateBtn.setEnabled(true);
                        btnDelete.setEnabled(true);
                        viewBtn.setEnabled(true);
                        selectedId = (Long) model.getValueAt(selectedRow, 0);
                    }
                }
            }
        });

        table.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"Κωδικός", "Όνομα", "Επώνυμο"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        table.setBounds(120, 110, 670, 360);
        model = (DefaultTableModel) table.getModel();


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(120, 110, 670, 360);
        mainPanel.add(scrollPane);


        viewBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getViewProfsPage().setEnabled(false);
                Main.getViewProfDetailPage().setVisible(true);
                updateBtn.setEnabled(false);
                btnDelete.setEnabled(false);
                viewBtn.setEnabled(false);
            }
        });
        viewBtn.setBackground(new Color(0, 128, 0));
        viewBtn.setForeground(new Color(255, 255, 255));
        viewBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        viewBtn.setBounds(874, 207, 220, 50);
        mainPanel.add(viewBtn);


        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getViewProfsPage().setEnabled(false);
                Main.getUpdateProfPage().setVisible(true);
                updateBtn.setEnabled(false);
                btnDelete.setEnabled(false);
                viewBtn.setEnabled(false);
            }
        });
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        updateBtn.setBackground(new Color(0, 128, 0));
        updateBtn.setBounds(874, 267, 220, 50);
        mainPanel.add(updateBtn);

        btnDelete.addActionListener(e -> {
            int response;
            try {
                response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος;", "Warning", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    teacherService.deleteTeacher(selectedId);
                    JOptionPane.showMessageDialog(null, "Teacher was deleted successfully", "Delete",
                            JOptionPane.INFORMATION_MESSAGE);
                    updateBtn.setEnabled(false);
                    btnDelete.setEnabled(false);
                    viewBtn.setEnabled(false);
                }
            } catch (TeacherDAOException ex) {
                //ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "DB error in delete. " + ex.getMessage(), "DB error", JOptionPane.ERROR_MESSAGE);
            } catch (TeacherNotFoundException ex) {
                //ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnDelete.setBackground(new Color(0, 128, 0));
        btnDelete.setBounds(874, 327, 220, 50);
        mainPanel.add(btnDelete);

        JButton btnClose = new JButton("Κλείσιμο");
        btnClose.addActionListener(e -> {
            Main.getDashboard().setEnabled(true);
            Main.getViewProfsPage().setVisible(false);
        });
        btnClose.setBackground(new Color(192, 192, 192));
        btnClose.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnClose.setBounds(874, 420, 220, 50);
        mainPanel.add(btnClose);

    }

    public Long getSelectedId() {
        return selectedId;
    }

    private void buildTable() {
        List<TeacherReadOnlyDTO> teachers;
        try {
            for (int i =  model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            teachers = teacherService.getTeachersByLastname(lastnameField.getText().trim());
            teachers.stream()
                    .map(t -> new Object[] {t.getId(), t.getFirstname(), t.getLastname()})
                    .forEach(model::addRow);
        } catch (TeacherDAOException e) {
//            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error in building the table", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void clearForm() {
        lastnameField.setText("");
    }
}

