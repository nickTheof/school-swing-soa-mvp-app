package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentDAOImpl implements IStudentDAO{
    @Override
    public Student insert(Student student) throws StudentDAOException {
        String sql = "INSERT INTO students (firstname, lastname, vat, father_name, phone_num," +
                " email, street, street_num, city_id, zipcode, uuid, created_at, updated_at)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Student insertedStudent = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, student.getFirstname());
            ps.setString(2, student.getLastname());
            ps.setString(3, student.getVat());
            ps.setString(4, student.getFatherName());
            ps.setString(5, student.getPhoneNum());
            ps.setString(6, student.getEmail());
            ps.setString(7, student.getStreet());
            ps.setString(8, student.getStreetNum());
            ps.setInt(9, student.getCityId());
            ps.setString(10, student.getZipcode());
            ps.setString(11, UUID.randomUUID().toString());
            ps.setTimestamp(12, Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(13, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();

            ResultSet rsGeneratedKeys = ps.getGeneratedKeys();
            if (rsGeneratedKeys.next()) {
                Long generatedId = rsGeneratedKeys.getLong(1);
                insertedStudent =  getById(generatedId);
            }
            // logging
            return insertedStudent;
        } catch (SQLException e) {
            //e.printStackTrace();
            // logging
            throw new StudentDAOException("SQL error in insert student with vat: " +
                    student.getVat());
        }
    }

    @Override
    public Student update(Student student) throws StudentDAOException {
        String sql = "UPDATE students SET firstname = ?, lastname = ?, vat = ?, father_name = ?, phone_num = ?," +
                " email = ?, street = ?, street_num = ?, city_id = ?, zipcode = ?, updated_at = ?" +
                " WHERE id = ?";
        Student updatedStudent = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, student.getFirstname());
            ps.setString(2, student.getLastname());
            ps.setString(3, student.getVat());
            ps.setString(4, student.getFatherName());
            ps.setString(5, student.getPhoneNum());
            ps.setString(6, student.getEmail());
            ps.setString(7, student.getStreet());
            ps.setString(8, student.getStreetNum());
            ps.setInt(9, student.getCityId());
            ps.setString(10, student.getZipcode());
            ps.setTimestamp(11, Timestamp.valueOf(LocalDateTime.now()));
            ps.setLong(12, student.getId());
            ps.executeUpdate();

            updatedStudent = getById(student.getId());
            // logging
            return updatedStudent;
        } catch (SQLException e) {
            //e.printStackTrace();
            // logging
            throw new StudentDAOException("SQL error in update student with vat: " +
                    student.getVat());
        }
    }

    @Override
    public void delete(Long id) throws StudentDAOException {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            // logging
        } catch (SQLException e) {
            //  e.printStackTrace();
            throw new StudentDAOException("SQL Error. Error getting student with id " + id);
        }
    }

    @Override
    public Student getById(Long id) throws StudentDAOException {
        String sql = "SELECT * FROM students WHERE id = ?";
        ResultSet rs;
        Student student = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("vat"), rs.getString("father_name"), rs.getString("phone_num"),
                        rs.getString("email"), rs.getString("street"), rs.getString("street_num"),
                        rs.getInt("city_id"), rs.getString("zipcode"),rs.getString("uuid"), rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime());
            }
            return student;
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new StudentDAOException("SQL Error. Error getting student with id " + id);
        }
    }

    @Override
    public List<Student> getAll() throws StudentDAOException {
        String sql = "SELECT * FROM students";
        List<Student> students = new ArrayList<>();
        ResultSet rs;
        Student student;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                student = new Student(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("vat"), rs.getString("father_name"), rs.getString("phone_num"),
                        rs.getString("email"), rs.getString("street"), rs.getString("street_num"),
                        rs.getInt("city_id"), rs.getString("zipcode"),rs.getString("uuid"), rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime());
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new StudentDAOException("SQL Error. Error getting all students");
        }
    }

    @Override
    public Student getByVat(String vat) throws StudentDAOException {
        String sql = "SELECT * FROM students WHERE vat = ?";
        ResultSet rs;
        Student student = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, vat);
            rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("vat"), rs.getString("father_name"), rs.getString("phone_num"),
                        rs.getString("email"), rs.getString("street"), rs.getString("street_num"),
                        rs.getInt("city_id"), rs.getString("zipcode"),rs.getString("uuid"), rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime());
            }
            return student;
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new StudentDAOException("SQL Error. Error getting student with vat " + vat);
        }
    }

    @Override
    public Student getByUuid(String uuid) throws StudentDAOException {
        String sql = "SELECT * FROM students WHERE uuid = ?";
        ResultSet rs;
        Student student = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, uuid);
            rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("vat"), rs.getString("father_name"), rs.getString("phone_num"),
                        rs.getString("email"), rs.getString("street"), rs.getString("street_num"),
                        rs.getInt("city_id"), rs.getString("zipcode"),rs.getString("uuid"), rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime());
            }
            return student;
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new StudentDAOException("SQL Error. Error getting student with uuid " + uuid);
        }
    }

    @Override
    public List<Student> getByLastname(String lastname) throws StudentDAOException {
        String sql = "SELECT * FROM students WHERE lastname LIKE ?";
        List<Student> students = new ArrayList<>();
        ResultSet rs;
        Student student;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, lastname + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                student = new Student(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("vat"), rs.getString("father_name"), rs.getString("phone_num"),
                        rs.getString("email"), rs.getString("street"), rs.getString("street_num"),
                        rs.getInt("city_id"), rs.getString("zipcode"),rs.getString("uuid"), rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime());
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new StudentDAOException("SQL Error. Error getting all students with lastname " + lastname);
        }
    }
}
