package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.exceptions.TeacherAlreadyExistsException;
import gr.aueb.cf.schoolapp.exceptions.TeacherNotFoundException;

import java.util.List;

public interface ITeacherService {
    TeacherReadOnlyDTO insertTeacher(TeacherInsertDTO teacherInsertDTO)
            throws TeacherDAOException, TeacherAlreadyExistsException;
    TeacherReadOnlyDTO updateTeacher(Long id, TeacherUpdateDTO teacherUpdateDTO)
        throws TeacherDAOException, TeacherNotFoundException, TeacherAlreadyExistsException;
    void deleteTeacher(Long id) throws TeacherDAOException, TeacherNotFoundException;
    TeacherReadOnlyDTO getTeacherById(Long id) throws TeacherDAOException, TeacherNotFoundException;
    List<TeacherReadOnlyDTO> getAllTeachers() throws TeacherDAOException;
    TeacherReadOnlyDTO getTeacherByUuid(String uuid) throws TeacherDAOException, TeacherNotFoundException;
    List<TeacherReadOnlyDTO> getTeachersByLastname(String lastname) throws TeacherDAOException;
}
