package gr.aueb.cf.schoolapp.dao.util;

import gr.aueb.cf.schoolapp.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private DBHelper() {

    }

    public static void eraseTeachersData() throws SQLException {
        String sqlFkOff = "SET @@foreign_key_checks = 0";
        String sqlFkOn = "SET @@foreign_key_checks = 1";
        String sqlDeleteCities = "DELETE FROM teachers";
        String sqlAutoInc = "ALTER TABLE teachers AUTO_INCREMENT = 1";


        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps1 = connection.prepareStatement(sqlFkOff);
             PreparedStatement ps2 = connection.prepareStatement(sqlDeleteCities);
             PreparedStatement ps3 = connection.prepareStatement(sqlAutoInc);
             PreparedStatement ps4 = connection.prepareStatement(sqlFkOn)){
            ps1.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();
            ps4.executeUpdate();
        } catch (SQLException e) {
//            e.printStackTrace();
            throw e;
        }
    }

    public static void eraseStudentsData() throws SQLException {
        String sqlFkOff = "SET @@foreign_key_checks = 0";
        String sqlFkOn = "SET @@foreign_key_checks = 1";
        String sqlDeleteCities = "DELETE FROM students";
        String sqlAutoInc = "ALTER TABLE students AUTO_INCREMENT = 1";


        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps1 = connection.prepareStatement(sqlFkOff);
             PreparedStatement ps2 = connection.prepareStatement(sqlDeleteCities);
             PreparedStatement ps3 = connection.prepareStatement(sqlAutoInc);
             PreparedStatement ps4 = connection.prepareStatement(sqlFkOn)){
            ps1.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();
            ps4.executeUpdate();
        } catch (SQLException e) {
//            e.printStackTrace();
            throw e;
        }
    }

    public static void eraseCityData() throws SQLException {
        String sqlFkOff = "SET @@foreign_key_checks = 0";
        String sqlFkOn = "SET @@foreign_key_checks = 1";
        String sqlDeleteCities = "DELETE FROM cities";
        String sqlAutoInc = "ALTER TABLE cities AUTO_INCREMENT = 1";


        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps1 = connection.prepareStatement(sqlFkOff);
             PreparedStatement ps2 = connection.prepareStatement(sqlDeleteCities);
             PreparedStatement ps3 = connection.prepareStatement(sqlAutoInc);
             PreparedStatement ps4 = connection.prepareStatement(sqlFkOn)){
            ps1.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();
            ps4.executeUpdate();
        } catch (SQLException e) {
//            e.printStackTrace();
            throw e;
        }
    }

}
