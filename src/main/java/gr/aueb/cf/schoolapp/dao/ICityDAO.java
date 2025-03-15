package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapp.model.City;

import java.util.List;

public interface ICityDAO {
    City insert(City city) throws CityDAOException;
    City update(City city) throws CityDAOException;
    void delete(Integer id) throws CityDAOException;
    City getById(Integer id) throws CityDAOException;
    List<City> getAll() throws CityDAOException;
}
