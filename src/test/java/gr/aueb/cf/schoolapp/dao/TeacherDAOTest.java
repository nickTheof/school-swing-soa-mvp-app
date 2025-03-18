package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.util.DBHelper;
import gr.aueb.cf.schoolapp.model.Teacher;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherDAOTest {
    private static ITeacherDAO teacherDAO;

    @BeforeAll
    public static void setupClass() throws SQLException {
        teacherDAO = new TeacherDAOImpl();
        DBHelper.eraseTeachersData();
    }

    @BeforeEach
    public void setup() throws TeacherDAOException {
        createDummyData();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        DBHelper.eraseTeachersData();
    }

    @AfterAll
    public static void tearAll() throws TeacherDAOException {
        createDummyData();
    }

    @Test
    void persistAndGetTeacher() throws TeacherDAOException {
        Teacher teacher = new Teacher(null, "Nikolas", "Theofanis", "1111111111", "Christos", "2102102100", "test@gmail.com",
                "Stadiou", "47", 1, "11111", null, null, null);
        teacherDAO.insert(teacher);
        List<Teacher> teachers = teacherDAO.getByLastname("Theofanis");
        assertEquals(1, teachers.size());
    }

    @Test
    void updateTeacher() throws TeacherDAOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");

        Teacher teacher = new Teacher(2L, "Άννα", "Γιαννούτσου", "144445678", "Κώστας", "110345678",
                "anna@gmail.com", "Γεωργούτσου", "12", 5, "67856",
                null,
                null,
                LocalDateTime.parse("18/3/2025 22:54", formatter));

        teacherDAO.update(teacher);

        Teacher teacherUpdated = teacherDAO.getById(2L);
        assertEquals("Κώστας", teacherUpdated.getFatherName());
        assertEquals("144445678", teacherUpdated.getVat());
        assertEquals("110345678", teacherUpdated.getPhoneNum());
    }

    @Test
    void getByVatPositiveScenario() throws TeacherDAOException {
        Teacher teacher = teacherDAO.getByVat("87654321");
        assertEquals(Long.valueOf(1), teacher.getId());
    }

    @Test
    void getByVatNegativeScenario() throws TeacherDAOException {
        Teacher teacher = teacherDAO.getByVat("999999999");
        assertNull(teacher);
    }

    @Test
    void getByUuidPositiveScenario() throws TeacherDAOException {
        Teacher teacherByID = teacherDAO.getById(1L);
        Teacher teacher = teacherDAO.getByUuid(teacherByID.getUuid());
        assertEquals(teacherByID.getId(), teacher.getId());
    }

    @Test
    void getByUuidNegativeScenario() throws TeacherDAOException {
        Teacher teacher = teacherDAO.getByUuid("eee");
        assertNull(teacher);
    }

    @Test
    void getTeacherByLastname() throws TeacherDAOException {
        List<Teacher> teachers = teacherDAO.getByLastname("Ανδρού");
        assertEquals(2, teachers.size());
    }

    @Test
    void getAllTeachers() throws TeacherDAOException {
        List<Teacher> teachers = teacherDAO.getAll();
        assertEquals(9, teachers.size());
    }

    @Test
    void deleteTeacher() throws TeacherDAOException {
        teacherDAO.delete(1L);
        Teacher deletedTeacher = teacherDAO.getById(1L);
        assertNull(deletedTeacher);
    }



    public static void createDummyData() throws TeacherDAOException {

        Teacher teacher1 = new Teacher(null, "Αθανάσιος", "Ανδρούτσος", "87654321", "Ανδρέας",
                "6935565765", "a8ana@gmail.com", "Πατησίων", "76",7, "10434",
                null, null, null);

        Teacher teacher2 = new Teacher(null, "Άννα", "Γιαννούτσου", "12345678", "Κώ", "12345678",
                "anna@gmail.com", "Γεωργούτσου", "12",5, "67856",
                null, null, null);


        Teacher teacher3 = new Teacher(null, "Μάκης", "Καπέτης", "987654321", "Ευάγγελος", "6935465768",
                "makis@gmail.com", "Πατησίων", "76",1, "10434",
                null, null, null);


        Teacher teacher4 = new Teacher(null, "Νίκη", "Γιαννούτσου", "918273645", "Αθανάσιος", "6934564890",
                "niki@gmail.com", "Λαμπρούτσου", "12",7, "65098",
                null, null, null);

        Teacher teacher5 = new Teacher(null, "Κωνσταντίνος", "Ρούμπας", "456782341", "Κλεάνθης", "69987678767",
                "kostis@gmail.com", "Φράγκου", "37", 3,"34567",
                null, null, null);

        Teacher teacher6 = new Teacher(null, "Ελένη", "Λαμπρούτσου", "9078563411", "Λάμπρος", "2111309876",
                "eleni@aueb.gr", "Αδριανής", "12",7, "98076",
                null, null, null);

        Teacher teacher7 = new Teacher(null, "Κυριάκος", "Νικολαϊδης", "76859401", "Νίκος", "90235674",
                "kyriakos@gmail.com", "Πατησίων", "76", 5,"89750",
                null, null, null);

        Teacher teacher8 = new Teacher(null, "Ανδρέας", "Ανδρούτσος", "9812002345", "Αθανάσιος", "2103098765",
                "andreas@gmail.com", "Ανακούς", "119", 5,"10434",
                null, null, null);

        Teacher teacher9 = new Teacher(null, "Ηφαιστίων", "Αλεξανδρής", "656565637", "Γρηγόριος", "2109876567",
                "ifaist@gmail.com", "Ανακούς", "77",1, "14341",
                null, null, null);

        teacherDAO.insert(teacher1);
        teacherDAO.insert(teacher2);
        teacherDAO.insert(teacher3);
        teacherDAO.insert(teacher4);
        teacherDAO.insert(teacher5);
        teacherDAO.insert(teacher6);
        teacherDAO.insert(teacher7);
        teacherDAO.insert(teacher8);
        teacherDAO.insert(teacher9);
    }

}