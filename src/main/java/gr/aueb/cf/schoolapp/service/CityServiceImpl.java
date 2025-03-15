package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ICityDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapp.model.City;

import java.util.ArrayList;
import java.util.List;

public class CityServiceImpl implements ICityService{
    private final ICityDAO cityDAO;

    public CityServiceImpl(ICityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    @Override
    public List<City> getAll() throws CityDAOException {
        List<City> cities = new ArrayList<>();
        try {
            cities = cityDAO.getAll();
            return cities;
        } catch (CityDAOException e) {
//            e.printStackTrace();
            throw e;
        }
    }
}
