package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.util.DBHelper;
import gr.aueb.cf.schoolapp.model.Student;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDAOTest {
    private static IStudentDAO studentDAO;

    @BeforeAll
    public static void setupClass() throws SQLException {
        studentDAO = new StudentDAOImpl();
        DBHelper.eraseStudentsData();
    }

    @BeforeEach
    public void setup() throws StudentDAOException {
        createDummyData();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        DBHelper.eraseStudentsData();
    }

    @AfterAll
    public static void tearAll() throws StudentDAOException, TeacherDAOException {
        createDummyData();
    }

    @Test
    void persistAndGetStudent() throws StudentDAOException {
        Student student = new Student(null, "Nikolas", "Theofanis", "1111111111", "Christos", "2102102100", "test@gmail.com",
                "Stadiou", "47", 1, "11111", null, null, null);
        studentDAO.insert(student);
        List<Student> students = studentDAO.getByLastname("Theofanis");
        assertEquals(1, students.size());
    }

    @Test
    void updateStudent() throws StudentDAOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");

        Student student = new Student(2L, "Μαρία", "Κολοκοτρώνη", "144445678", "Κώστας",
                "110345678", "maria@gmail.com", "Αθήνας", "34", 6, "88990", null, null,
                LocalDateTime.parse("18/3/2025 22:54", formatter));

        studentDAO.update(student);

        Student studentUpdated = studentDAO.getById(2L);
        assertEquals("Κώστας", studentUpdated.getFatherName());
        assertEquals("144445678", studentUpdated.getVat());
        assertEquals("110345678", studentUpdated.getPhoneNum());
    }

    @Test
    void getByVatPositiveScenario() throws StudentDAOException {
        Student student = studentDAO.getByVat("56783920");
        assertEquals(Long.valueOf(1), student.getId());
    }

    @Test
    void getByVatNegativeScenario() throws StudentDAOException {
        Student student = studentDAO.getByVat("999999999");
        assertNull(student);
    }

    @Test
    void getByUuidPositiveScenario() throws StudentDAOException {
       Student studentByID = studentDAO.getById(1L);
       Student student = studentDAO.getByUuid(studentByID.getUuid());
       assertEquals(studentByID.getId(), student.getId());
    }

    @Test
    void getByUuidNegativeScenario() throws StudentDAOException {
        Student student = studentDAO.getByUuid("eee");
        assertNull(student);
    }

    @Test
    void getStudentByLastname() throws StudentDAOException {
        List<Student> students = studentDAO.getByLastname("Παναγ");
        assertEquals(2, students.size());
    }

    @Test
    void getAllStudents() throws StudentDAOException {
        List<Student> students = studentDAO.getAll();
        assertEquals(10, students.size());
    }

    @Test
    void deleteStudent() throws StudentDAOException {
        studentDAO.delete(1L);
        Student deletedStudent = studentDAO.getById(1L);
        assertNull(deletedStudent);
    }



    public static void createDummyData() throws StudentDAOException {

        Student student1 = new Student(null, "Γεώργιος", "Παναγιώτου", "56783920", "Στέφανος",
                "6987654321", "george@gmail.com", "Σταδίου", "55", 2, "11223",
                null, null, null);

        Student student2 = new Student(null, "Μαρία", "Κολοκοτρώνη", "908172635", "Θεόδωρος",
                "6976543210", "maria@gmail.com", "Αθήνας", "34", 6, "88990",
                null, null, null);

        Student student3 = new Student(null, "Παναγιώτης", "Σωτηρόπουλος", "34215678", "Δημήτρης",
                "6932185476", "panos@gmail.com", "Λεωφόρος Συγγρού", "88", 4, "66778",
                null, null, null);

        Student student4 = new Student(null, "Ευαγγελία", "Παναγιώτου", "12987465", "Ιωάννης",
                "6923456789", "eva@gmail.com", "Ομόνοιας", "22", 7, "23456",
                null, null, null);

        Student student5 = new Student(null, "Στέφανος", "Καραγιάννης", "78345612", "Αντώνιος",
                "6909876543", "stefanos@gmail.com", "Κηφισίας", "101", 3, "77788",
                null, null, null);

        Student student6 = new Student(null, "Αικατερίνη", "Βασιλείου", "21436587", "Νικόλαος",
                "6987654098", "katerina@gmail.com", "Πανεπιστημίου", "66", 5, "54321",
                null, null, null);

        Student student7 = new Student(null, "Δημήτριος", "Λεβέντης", "90874532", "Μιχαήλ",
                "6935678901", "dimitris@gmail.com", "Βασιλίσσης Σοφίας", "45", 6, "33221",
                null, null, null);

        Student student8 = new Student(null, "Νικόλαος", "Σταματίου", "78234165", "Πέτρος",
                "6978123456", "nikos@gmail.com", "Ακαδημίας", "29", 2, "99887",
                null, null, null);

        Student student9 = new Student(null, "Χριστίνα", "Αναγνωστοπούλου", "32456789", "Εμμανουήλ",
                "6956781234", "christina@gmail.com", "Λιοσίων", "77", 4, "76543",
                null, null, null);

        Student student10 = new Student(null, "Απόστολος", "Διαμαντόπουλος", "11223344", "Σπυρίδων",
                "6934567890", "apostolos@gmail.com", "Χαριλάου Τρικούπη", "98", 1, "22110",
                null, null, null);

        studentDAO.insert(student1);
        studentDAO.insert(student2);
        studentDAO.insert(student3);
        studentDAO.insert(student4);
        studentDAO.insert(student5);
        studentDAO.insert(student6);
        studentDAO.insert(student7);
        studentDAO.insert(student8);
        studentDAO.insert(student9);
        studentDAO.insert(student10);
    }

}