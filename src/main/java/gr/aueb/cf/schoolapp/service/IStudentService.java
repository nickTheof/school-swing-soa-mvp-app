package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.dto.StudentInsertDTO;
import gr.aueb.cf.schoolapp.dto.StudentReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolapp.exceptions.StudentAlreadyExistsException;
import gr.aueb.cf.schoolapp.exceptions.StudentNotFoundException;

import java.util.List;

public interface IStudentService {
    StudentReadOnlyDTO insertStudent(StudentInsertDTO studentInsertDTO)
        throws StudentDAOException, StudentAlreadyExistsException;
    StudentReadOnlyDTO updateStudent(Long id, StudentUpdateDTO studentUpdateDTO)
        throws StudentDAOException, StudentAlreadyExistsException, StudentNotFoundException;
    void deleteStudent(Long id) throws StudentDAOException, StudentNotFoundException;
    StudentReadOnlyDTO getStudentById(Long id) throws StudentDAOException, StudentNotFoundException;
    List<StudentReadOnlyDTO> getAllStudents() throws StudentDAOException;
    StudentReadOnlyDTO getStudentByUuid(String uuid) throws StudentDAOException, StudentNotFoundException;
    List<StudentReadOnlyDTO> getStudentsByLastname(String lastname) throws StudentDAOException;



}
