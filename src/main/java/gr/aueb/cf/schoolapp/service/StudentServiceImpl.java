package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.IStudentDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.dto.StudentInsertDTO;
import gr.aueb.cf.schoolapp.dto.StudentReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolapp.exceptions.StudentAlreadyExistsException;
import gr.aueb.cf.schoolapp.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolapp.mapper.Mapper;
import gr.aueb.cf.schoolapp.model.Student;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentServiceImpl implements IStudentService {
    private final IStudentDAO studentDAO;

    public StudentServiceImpl(IStudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }


    @Override
    public StudentReadOnlyDTO insertStudent(StudentInsertDTO studentInsertDTO) throws StudentDAOException, StudentAlreadyExistsException {
        Student insertedStudent;
        Student student;
        try {
            student = Mapper.mapStudentInsertDTOToModel(studentInsertDTO);

            if (studentDAO.getByVat(student.getVat()) != null) {
                throw new StudentAlreadyExistsException("Student with vat " + student.getVat() + " already exists");
            }
            insertedStudent = studentDAO.insert(student);
            // logging
            return Mapper.mapStudentToReadOnlyDTO(insertedStudent).orElse(null);
        } catch (StudentDAOException | StudentAlreadyExistsException e) {
//            e.printStackTrace();
            // logging
            // rollback
            throw e;
        }
    }

    @Override
    public StudentReadOnlyDTO updateStudent(Long id, StudentUpdateDTO studentUpdateDTO) throws StudentDAOException, StudentAlreadyExistsException, StudentNotFoundException {
        Student fetchedStudent;
        Student student;
        Student updatedStudent;
        try {
            if (studentDAO.getById(id) == null) {
                throw new StudentNotFoundException("Student with id " + id + " was not found");
            }

            fetchedStudent = studentDAO.getByVat(studentUpdateDTO.getVat());
            if (fetchedStudent != null && !fetchedStudent.getId().equals(studentUpdateDTO.getId())){
                throw new StudentAlreadyExistsException("Student with vat " + studentUpdateDTO.getVat() + " already exists");
            }
            student = Mapper.mapStudentUpdateDTOToModel(studentUpdateDTO);
            updatedStudent = studentDAO.update(student);
            // logging
            return Mapper.mapStudentToReadOnlyDTO(updatedStudent).orElse(null);
        } catch (StudentDAOException | StudentAlreadyExistsException | StudentNotFoundException e) {
//            e.printStackTrace();
            // logging
            // rollback
            throw e;
        }
    }

    @Override
    public void deleteStudent(Long id) throws StudentDAOException, StudentNotFoundException {
        try {
            if (studentDAO.getById(id) == null) {
                throw new StudentNotFoundException("Student with id " + id + " was not found");
            }
            studentDAO.delete(id);
            // logging
        } catch (StudentDAOException | StudentNotFoundException e) {
//            e.printStackTrace();
            // logging
            throw e;
        }
    }

    @Override
    public StudentReadOnlyDTO getStudentById(Long id) throws StudentDAOException, StudentNotFoundException {
        Student student;
        try {
            student = studentDAO.getById(id);
            return Mapper.mapStudentToReadOnlyDTO(student).orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " was not found"));
        } catch (StudentDAOException | StudentNotFoundException e) {
//            e.printStackTrace();
            // logging
            throw e;
        }

    }

    @Override
    public List<StudentReadOnlyDTO> getAllStudents() throws StudentDAOException {
        List<Student> students = new ArrayList<>();
        try {
            students = studentDAO.getAll();
            return students
                    .stream()
                    .map(Mapper::mapStudentToReadOnlyDTO)
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());
        } catch (StudentDAOException e) {
//            e.printStackTrace();
            // logging
            throw e;
        }
    }

    @Override
    public StudentReadOnlyDTO getStudentByUuid(String uuid) throws StudentDAOException, StudentNotFoundException {
        Student student;
        try {
            student = studentDAO.getByUuid(uuid);
            return Mapper.mapStudentToReadOnlyDTO(student).orElseThrow(() -> new StudentNotFoundException("Student with uuid " + uuid + " was not found"));
        } catch (StudentDAOException | StudentNotFoundException e) {
//            e.printStackTrace();
            // logging
            throw e;
        }
    }


    @Override
    public List<StudentReadOnlyDTO> getStudentsByLastname(String lastname) throws StudentDAOException {
        List<Student> students = new ArrayList<>();
        try {
            students = studentDAO.getByLastname(lastname);
            return students
                    .stream()
                    .map(Mapper::mapStudentToReadOnlyDTO)
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());
        } catch (StudentDAOException e) {
//            e.printStackTrace();
            // logging
            throw e;
        }
    }
}
