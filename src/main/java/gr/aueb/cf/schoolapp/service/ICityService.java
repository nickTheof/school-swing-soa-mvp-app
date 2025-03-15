package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapp.model.City;

import java.util.List;

public interface ICityService {
    List<City> getAll() throws CityDAOException;
}
