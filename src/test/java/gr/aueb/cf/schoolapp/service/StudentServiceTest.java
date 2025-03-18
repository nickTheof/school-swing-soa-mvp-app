package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.IStudentDAO;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.StudentDAOImpl;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.util.DBHelper;
import gr.aueb.cf.schoolapp.dto.*;
import gr.aueb.cf.schoolapp.exceptions.StudentAlreadyExistsException;
import gr.aueb.cf.schoolapp.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolapp.exceptions.TeacherAlreadyExistsException;
import gr.aueb.cf.schoolapp.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp.model.Student;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    private static IStudentDAO studentDAO;
    private static IStudentService studentService;

    @BeforeAll
    public static void setupClass() throws SQLException {
        studentDAO = new StudentDAOImpl();
        studentService = new StudentServiceImpl(studentDAO);
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
    public static void tearAll() throws StudentDAOException {
        createDummyData();
    }

    @Test
    public void insertStudent() throws StudentDAOException, StudentAlreadyExistsException {
        StudentInsertDTO insertDTO = new StudentInsertDTO("Αθανάσιος", "Ιωάννου", "080654320", "Ανδρέας",
                "6935565765", "a8ana@gmail.com", "Πατησίων", "76", 7, "10434");

        studentService.insertStudent(insertDTO);
        List<StudentReadOnlyDTO> students = studentService.getStudentsByLastname("Ιωάννου");
        assertEquals(1, students.size());
    }

    @Test
    public void insertStudentNegative() {
        StudentInsertDTO insertDTO = new StudentInsertDTO("Γεώργιος", "Παναγιώτου", "56783920", "Στέφανος",
                "6987654321", "george@gmail.com", "Σταδίου", "55", 2, "11223");

        assertThrows(StudentAlreadyExistsException.class, ()-> {
            studentService.insertStudent(insertDTO);
        });
    }

    @Test
    public void updateStudent()
            throws  StudentDAOException, StudentNotFoundException, StudentAlreadyExistsException {

        StudentUpdateDTO updateDTO = new StudentUpdateDTO(9L, "Αθανάσιος", "Ιωάννου", "080654320", "Ανδρέας",
                "6935565765", "a8ana@gmail.com", "Πατησίων", "76",7, "10434");

        studentService.updateStudent(9L, updateDTO);

        StudentReadOnlyDTO student = studentService.getStudentById(9L);
        assertEquals("080654320", student.getVat());
        assertEquals("Αθανάσιος", student.getFirstname());
        assertEquals("Ιωάννου", student.getLastname());
    }

    @Test
    public void updateStudentVatExistsNegative() {
        StudentUpdateDTO updateDTO = new StudentUpdateDTO(9L, "Αθανάσιος", "Ιωάννου", "56783920", "Ανδρέας",
                "6935565765", "a8ana@gmail.com", "Πατησίων", "76",7, "10434");
        assertThrows(StudentAlreadyExistsException.class, () -> {
            studentService.updateStudent(9L, updateDTO);
        });
    }

    @Test
    public void updateStudentNotExistsNegative() {
        StudentUpdateDTO updateDTO = new StudentUpdateDTO(15L, "Αθανάσιος", "Ιωάννου", "959595667", "Ανδρέας",
                "6935565765", "a8ana@gmail.com", "Πατησίων", "76",7, "10434");
        assertThrows(StudentNotFoundException.class, () -> {
            studentService.updateStudent(15L, updateDTO);
        });
    }


    @Test
    public void deleteStudentPositive()
            throws StudentDAOException, StudentNotFoundException {

        studentService.deleteStudent(1L);
        assertThrows(StudentNotFoundException.class, () -> {
            studentService.getStudentById(1L);
        });
    }

    @Test
    public void deleteStudentNegative() {
        assertThrows(StudentNotFoundException.class, () -> {
            studentService.deleteStudent(15L);
        });
    }

    @Test
    void getStudentByIdPositive() throws StudentDAOException, StudentNotFoundException {
        StudentReadOnlyDTO student = studentService.getStudentById(1L);
        assertEquals("Παναγιώτου", student.getLastname());
    }


    @Test
    void getStudentByIdNegative()  {
        assertThrows(StudentNotFoundException.class, () -> {
            studentService.getStudentById(15L);
        });
    }

    @Test
    void getStudentByUuidPositive() throws StudentDAOException, StudentNotFoundException {
        StudentReadOnlyDTO student = studentService.getStudentById(1L);
        StudentReadOnlyDTO student2 = studentService.getStudentByUuid(student.getUuid());
        assertEquals(Long.valueOf(1), student2.getId());
    }

    @Test
    void getStudentByUuidNegative()  {
        assertThrows(StudentNotFoundException.class, () -> {
            studentService.getStudentByUuid("eee");
        });
    }

    @Test
    void getStudentsByLastname() throws StudentDAOException {
        List<StudentReadOnlyDTO> students = studentService.getStudentsByLastname("Παναγ");
        assertEquals(2, students.size());
    }

    @Test
    void getStudentByLastnameNegative() throws StudentDAOException {
        List<StudentReadOnlyDTO> students = studentService.getStudentsByLastname("Miller");
        assertEquals(0, students.size());
    }

    @Test
    void getAllStudents() throws StudentDAOException {
        List<StudentReadOnlyDTO> students = studentService.getAllStudents();
        assertEquals(10, students.size());
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