package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;

import java.util.List;

public interface ITeacherDAO {
    Teacher insert(Teacher teacher) throws TeacherDAOException;
    Teacher update(Teacher teacher) throws TeacherDAOException;
    void delete(Long id) throws TeacherDAOException;
    Teacher getById(Long id) throws TeacherDAOException;
    List<Teacher> getAll() throws TeacherDAOException;
    Teacher getByVat(String vat) throws TeacherDAOException;
    Teacher getByUuid(String uuid) throws TeacherDAOException;
    List<Teacher> getByLastname(String lastname) throws TeacherDAOException;
}
