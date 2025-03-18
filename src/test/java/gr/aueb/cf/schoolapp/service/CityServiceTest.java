package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.CityDAOImpl;
import gr.aueb.cf.schoolapp.dao.ICityDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapp.model.City;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CityServiceTest {
    private static ICityDAO cityDAO;
    private static ICityService cityService;

    @BeforeAll
    public static void setupClass() {
        cityDAO = new CityDAOImpl();
        cityService = new CityServiceImpl(cityDAO);
    }

    @Test
    void getAllTeachers() throws CityDAOException {
        List<City> cities = cityService.getAll();
        assertEquals(11, cities.size());
    }

}