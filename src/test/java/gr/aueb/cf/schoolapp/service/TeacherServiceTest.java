package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.util.DBHelper;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.exceptions.TeacherAlreadyExistsException;
import gr.aueb.cf.schoolapp.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp.model.Teacher;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherServiceTest {

    private static ITeacherDAO teacherDAO;
    private static ITeacherService teacherService;

    @BeforeAll
    public static void setupClass() throws SQLException {
        teacherDAO = new TeacherDAOImpl();
        teacherService = new TeacherServiceImpl(teacherDAO);
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
    public void insertTeacher() throws TeacherDAOException, TeacherAlreadyExistsException {
        TeacherInsertDTO insertDTO = new TeacherInsertDTO("Αθανάσιος", "Ιωάννου", "080654320", "Ανδρέας",
                "6935565765", "a8ana@gmail.com", "Πατησίων", "76", 7, "10434");

        teacherService.insertTeacher(insertDTO);

        List<TeacherReadOnlyDTO> teachers = teacherService.getTeachersByLastname("Ιωάννου");
        assertEquals(1, teachers.size());
    }

    @Test
    public void insertTeacherNegative() {
        TeacherInsertDTO insertDTO = new TeacherInsertDTO("Αθανάσιος", "Ιωάννου", "87654321", "Ανδρέας",
                "6935565765", "a8ana@gmail.com", "Πατησίων", "76", 7, "10434");

        assertThrows(TeacherAlreadyExistsException.class, ()-> {
            teacherService.insertTeacher(insertDTO);
        });
    }

    @Test
    public void updateTeacher()
            throws TeacherDAOException, TeacherNotFoundException, TeacherAlreadyExistsException {

        TeacherUpdateDTO updateDTO = new TeacherUpdateDTO(9L, "Αθανάσιος", "Ιωάννου", "080654320", "Ανδρέας",
                "6935565765", "a8ana@gmail.com", "Πατησίων", "76",7, "10434");

        teacherService.updateTeacher(9L, updateDTO);

        TeacherReadOnlyDTO teacher = teacherService.getTeacherById(9L);
        assertEquals("080654320", teacher.getVat());
        assertEquals("Αθανάσιος", teacher.getFirstname());
        assertEquals("Ιωάννου", teacher.getLastname());
    }

    @Test
    public void updateTeacherVatExistsNegative() {
        TeacherUpdateDTO updateDTO = new TeacherUpdateDTO(9L, "Αθανάσιος", "Ιωάννου", "87654321", "Ανδρέας",
                "6935565765", "a8ana@gmail.com", "Πατησίων", "76",7, "10434");
        assertThrows(TeacherAlreadyExistsException.class, () -> {
            teacherService.updateTeacher(9L, updateDTO);
        });
    }

    @Test
    public void updateTeacherNotExistsNegative() {
        TeacherUpdateDTO updateDTO = new TeacherUpdateDTO(15L, "Αθανάσιος", "Ιωάννου", "959595667", "Ανδρέας",
                "6935565765", "a8ana@gmail.com", "Πατησίων", "76",7, "10434");
        assertThrows(TeacherNotFoundException.class, () -> {
            teacherService.updateTeacher(15L, updateDTO);
        });
    }


    @Test
    public void deleteTeacherPositive()
            throws TeacherDAOException, TeacherNotFoundException {

        teacherService.deleteTeacher(1L);
        assertThrows(TeacherNotFoundException.class, () -> {
            teacherService.getTeacherById(1L);
        });
    }

    @Test
    public void deleteTeacherNegative() {
        assertThrows(TeacherNotFoundException.class, () -> {
            teacherService.deleteTeacher(15L);
        });
    }

    @Test
    void getTeacherByIdPositive() throws TeacherDAOException, TeacherNotFoundException {
        TeacherReadOnlyDTO teacher = teacherService.getTeacherById(1L);
        assertEquals("Ανδρούτσος", teacher.getLastname());
    }


    @Test
    void getTeacherByIdNegative()  {
        assertThrows(TeacherNotFoundException.class, () -> {
            teacherService.getTeacherById(15L);
        });
    }

    @Test
    void getTeacherByUuidPositive() throws TeacherDAOException, TeacherNotFoundException {
        TeacherReadOnlyDTO teacher = teacherService.getTeacherById(1L);
        TeacherReadOnlyDTO teacher2 = teacherService.getTeacherByUuid(teacher.getUuid());
        assertEquals(Long.valueOf(1), teacher2.getId());
    }

    @Test
    void getTeacherByUuidNegative()  {
        assertThrows(TeacherNotFoundException.class, () -> {
            teacherService.getTeacherByUuid("eee");
        });
    }

    @Test
    void getTeacherByLastname() throws TeacherDAOException {
        List<TeacherReadOnlyDTO> teachers = teacherService.getTeachersByLastname("Ανδρούτσος");
        assertEquals(2, teachers.size());
    }

    @Test
    void getTeacherByLastnameNegative() throws TeacherDAOException {
        List<TeacherReadOnlyDTO> teachers = teacherService.getTeachersByLastname("Miller");
        assertEquals(0, teachers.size());
    }

    @Test
    void getAllTeachers() throws TeacherDAOException {
        List<TeacherReadOnlyDTO> teachers = teacherService.getAllTeachers();
        assertEquals(9, teachers.size());
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