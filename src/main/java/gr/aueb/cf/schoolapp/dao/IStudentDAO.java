package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.model.Student;

import java.util.List;

public interface IStudentDAO {
    Student insert(Student student) throws StudentDAOException;
    Student update(Student student) throws StudentDAOException;
    void delete(Long id) throws StudentDAOException;
    Student getById(Long id) throws StudentDAOException;
    List<Student> getAll() throws StudentDAOException;
    Student getByVat(String vat) throws StudentDAOException;
    Student getByUuid(String uuid) throws StudentDAOException;
    List<Student> getByLastname(String lastname) throws StudentDAOException;
}
