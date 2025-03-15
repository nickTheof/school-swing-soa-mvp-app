package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeacherDAOImpl implements ITeacherDAO {
    @Override
    public Teacher insert(Teacher teacher) throws TeacherDAOException {
        String sql = "INSERT INTO teachers (firstname, lastname, vat, father_name, phone_num," +
                " email, street, street_num, city_id, zipcode, uuid, created_at, updated_at)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Teacher insertedTeacher = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, teacher.getFirstname());
            ps.setString(2, teacher.getLastname());
            ps.setString(3, teacher.getVat());
            ps.setString(4, teacher.getFatherName());
            ps.setString(5, teacher.getPhoneNum());
            ps.setString(6, teacher.getEmail());
            ps.setString(7, teacher.getStreet());
            ps.setString(8, teacher.getStreetNum());
            ps.setInt(9, teacher.getCityId());
            ps.setString(10, teacher.getZipcode());
            ps.setString(11, UUID.randomUUID().toString());
            ps.setTimestamp(12, Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(13, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();

            ResultSet rsGeneratedKeys = ps.getGeneratedKeys();
            if (rsGeneratedKeys.next()) {
                Long generatedId = rsGeneratedKeys.getLong(1);
                insertedTeacher =  getById(generatedId);
            }
            // logging
            return insertedTeacher;
        } catch (SQLException e) {
            //e.printStackTrace();
            // logging
            throw new TeacherDAOException("SQL error in insert teacher with vat: " +
                    teacher.getVat());
        }
    }

    @Override
    public Teacher update(Teacher teacher) throws TeacherDAOException {
        String sql = "UPDATE teachers SET firstname = ?, lastname = ?, vat = ?, father_name = ?, phone_num = ?," +
                " email = ?, street = ?, street_num = ?, zipcode = ?, city_id = ?," +
                " updated_at = ? WHERE id = ?";
        Teacher updatedTeacher;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, teacher.getFirstname());
            ps.setString(2, teacher.getLastname());
            ps.setString(3, teacher.getVat());
            ps.setString(4, teacher.getFatherName());
            ps.setString(5, teacher.getPhoneNum());
            ps.setString(6, teacher.getEmail());
            ps.setString(7, teacher.getStreet());
            ps.setString(8, teacher.getStreetNum());
            ps.setString(9, teacher.getZipcode());
            ps.setInt(10, teacher.getCityId());
            ps.setTimestamp(11, Timestamp.valueOf(LocalDateTime.now()));
            ps.setLong(12, teacher.getId());

            ps.executeUpdate();

            updatedTeacher = getById(teacher.getId());
            // logging
            return updatedTeacher;
        } catch (SQLException e) {
//            e.printStackTrace();
            // logging
            throw new TeacherDAOException("SQL error in update teacher with vat: " + teacher.getVat());
        }
    }

    @Override
    public void delete(Long id) throws TeacherDAOException {
        String sql = "DELETE FROM teachers WHERE id = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            // logging
        } catch (SQLException e) {
            //  e.printStackTrace();
            throw new TeacherDAOException("SQL Error. Error getting teacher with id " + id);
        }
    }

    @Override
    public Teacher getById(Long id) throws TeacherDAOException {
        String sql = "SELECT * FROM teachers WHERE id = ?";
        ResultSet rs;
        Teacher teacher = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                teacher = new Teacher(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("vat"), rs.getString("father_name"), rs.getString("phone_num"),
                        rs.getString("email"), rs.getString("street"), rs.getString("street_num"),
                        rs.getInt("city_id"), rs.getString("zipcode"),rs.getString("uuid"), rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime());
            }
            return teacher;
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new TeacherDAOException("SQL Error. Error getting teacher with id " + id);
        }
    }

    @Override
    public List<Teacher> getAll() throws TeacherDAOException {
        String sql = "SELECT * FROM teachers";
        List<Teacher> teachers = new ArrayList<>();
        ResultSet rs;
        Teacher teacher;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                teacher = new Teacher(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("vat"), rs.getString("father_name"), rs.getString("phone_num"),
                        rs.getString("email"), rs.getString("street"), rs.getString("street_num"),
                        rs.getInt("city_id"), rs.getString("zipcode"),rs.getString("uuid"), rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime());
                teachers.add(teacher);
            }
            return teachers;
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new TeacherDAOException("SQL Error. Error getting all teachers");
        }
    }

    @Override
    public Teacher getByVat(String vat) throws TeacherDAOException {
        String sql = "SELECT * FROM teachers WHERE vat = ?";
        ResultSet rs;
        Teacher teacher = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, vat);
            rs = ps.executeQuery();
            if (rs.next()) {
                teacher = new Teacher(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("vat"), rs.getString("father_name"), rs.getString("phone_num"),
                        rs.getString("email"), rs.getString("street"), rs.getString("street_num"),
                        rs.getInt("city_id"), rs.getString("zipcode"),rs.getString("uuid"), rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime());
            }
            return teacher;
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new TeacherDAOException("SQL Error. Error getting teacher with vat " + vat);
        }
    }

    @Override
    public Teacher getByUuid(String uuid) throws TeacherDAOException {
        String sql = "SELECT * FROM teachers WHERE uuid = ?";
        ResultSet rs;
        Teacher teacher = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, uuid);
            rs = ps.executeQuery();
            if (rs.next()) {
                teacher = new Teacher(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("vat"), rs.getString("father_name"), rs.getString("phone_num"),
                        rs.getString("email"), rs.getString("street"), rs.getString("street_num"),
                        rs.getInt("city_id"), rs.getString("zipcode"),rs.getString("uuid"), rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime());
            }
            return teacher;
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new TeacherDAOException("SQL Error. Error getting teacher with uuid " + uuid);
        }
    }

    @Override
    public List<Teacher> getByLastname(String lastname) throws TeacherDAOException {
        String sql = "SELECT * FROM teachers WHERE lastname LIKE ?";
        List<Teacher> teachers = new ArrayList<>();
        ResultSet rs;
        Teacher teacher;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, lastname + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                teacher = new Teacher(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("vat"), rs.getString("father_name"), rs.getString("phone_num"),
                        rs.getString("email"), rs.getString("street"), rs.getString("street_num"),
                        rs.getInt("city_id"), rs.getString("zipcode"),rs.getString("uuid"), rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime());
                teachers.add(teacher);
            }
            return teachers;
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new TeacherDAOException("SQL Error. Error getting teachers with lastname " + lastname);
        }
    }
}
