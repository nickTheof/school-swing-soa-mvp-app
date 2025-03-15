package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapp.model.City;
import gr.aueb.cf.schoolapp.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAOImpl implements ICityDAO {
    @Override
    public City insert(City city) throws CityDAOException {
        String sql = "INSERT INTO cities (name) VALUES (?)";
        City insertedCity = null;
        ResultSet rsGenKeys;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, city.getName());
            ps.executeUpdate();
            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next()) {
                insertedCity = getById(rsGenKeys.getInt(1));
            }
            // logging
            return insertedCity;
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new CityDAOException("SQL Error. Error inserting city with name: " + city.getName());
        }
    }

    @Override
    public City update(City city) throws CityDAOException {
        String sql = "UPDATE cities SET name = ? WHERE id = ?";
        City updatedCity = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, city.getName());
            ps.setInt(2, city.getId());
            ps.executeUpdate();
            updatedCity = getById(city.getId());
            // logging
            return updatedCity;
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new CityDAOException("SQL Error. Error updating city with id: " + city.getId());
        }
    }

    @Override
    public void delete(Integer id) throws CityDAOException {
        String sql = "DELETE FROM cities WHERE id = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            // logging
        } catch (SQLException e) {
            //  e.printStackTrace();
            throw new CityDAOException("SQL Error. Error getting city with id " + id);
        }
    }

    @Override
    public City getById(Integer id) throws CityDAOException {
        String sql = "SELECT * FROM cities WHERE id = ?";
        ResultSet rs;
        City city = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                city = new City(rs.getInt("id"), rs.getString("name"));
            }
            return city;
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new CityDAOException("SQL Error. Error getting city with id " + id);
        }
    }

    @Override
    public List<City> getAll() throws CityDAOException {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM cities ORDER BY name";
        ResultSet rs;
        City city;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                city = new City(rs.getInt("id"), rs.getString("name"));
                cities.add(city);
            }
            return cities;
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new CityDAOException("SQL Error. Error getting all cities");
        }
    }
}
