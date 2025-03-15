package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.exceptions.TeacherAlreadyExistsException;
import gr.aueb.cf.schoolapp.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp.mapper.Mapper;
import gr.aueb.cf.schoolapp.model.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TeacherServiceImpl implements ITeacherService {
    private final ITeacherDAO teacherDAO;

    public TeacherServiceImpl(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public TeacherReadOnlyDTO insertTeacher(TeacherInsertDTO teacherInsertDTO) throws TeacherDAOException, TeacherAlreadyExistsException {
        Teacher insertedTeacher;
        Teacher teacher;
        try {
            teacher = Mapper.mapTeacherInsertDTOToModel(teacherInsertDTO);

            if (teacherDAO.getByVat(teacher.getVat()) != null) {
                throw new TeacherAlreadyExistsException("Teacher with vat " + teacherInsertDTO.getVat() + " already exists");
            }
            insertedTeacher = teacherDAO.insert(teacher);
            // logging
            return Mapper.mapTeacherToReadOnlyDTO(insertedTeacher).orElse(null);
        } catch (TeacherDAOException | TeacherAlreadyExistsException e) {
//            e.printStackTrace();
            // logging
            // rollback
            throw e;
        }
    }

    @Override
    public TeacherReadOnlyDTO updateTeacher(Long id, TeacherUpdateDTO teacherUpdateDTO) throws TeacherDAOException, TeacherNotFoundException, TeacherAlreadyExistsException {
        Teacher fetchedTeacher;
        Teacher teacher;
        Teacher updatedTeacher;
        try {
            if (teacherDAO.getById(id) == null) {
                throw new TeacherNotFoundException("Teacher with id " + id + " was not found");
            }
            fetchedTeacher = teacherDAO.getByVat(teacherUpdateDTO.getVat());
            if (fetchedTeacher != null && !fetchedTeacher.getId().equals(teacherUpdateDTO.getId())){
                throw new TeacherAlreadyExistsException("Teacher with vat " + teacherUpdateDTO.getVat() + " already exists");
            }
            teacher = Mapper.mapTeacherUpdateDTOToModel(teacherUpdateDTO);
            updatedTeacher = teacherDAO.update(teacher);
            // logging
            return Mapper.mapTeacherToReadOnlyDTO(updatedTeacher).orElse(null);
        } catch (TeacherDAOException | TeacherNotFoundException | TeacherAlreadyExistsException e) {
//            e.printStackTrace();
            // logging
            throw e;

        }
    }

    @Override
    public void deleteTeacher(Long id) throws TeacherDAOException, TeacherNotFoundException {
        try {
            if (teacherDAO.getById(id) == null) {
                throw new TeacherNotFoundException("Teacher with id " + id + " was not found");
            }
            teacherDAO.delete(id);
            // logging
        } catch (TeacherDAOException | TeacherNotFoundException e) {
//            e.printStackTrace();
            // logging
            throw e;
        }
    }

    @Override
    public TeacherReadOnlyDTO getTeacherById(Long id) throws TeacherDAOException, TeacherNotFoundException {
        Teacher teacher;
        try {
            teacher = teacherDAO.getById(id);
            return Mapper.mapTeacherToReadOnlyDTO(teacher).orElseThrow(() -> new TeacherNotFoundException("Teacher with id " + id + " was not found"));
        } catch (TeacherDAOException | TeacherNotFoundException e) {
//            e.printStackTrace();
            // logging
            throw e;
        }
    }

    @Override
    public List<TeacherReadOnlyDTO> getAllTeachers() throws TeacherDAOException {
        List<Teacher> teachers = new ArrayList<>();
        try {
            teachers = teacherDAO.getAll();
            return teachers
                    .stream()
                    .map(Mapper::mapTeacherToReadOnlyDTO)
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());
        } catch (TeacherDAOException e) {
//            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public TeacherReadOnlyDTO getTeacherByUuid(String uuid) throws TeacherDAOException, TeacherNotFoundException {
        Teacher teacher;
        try {
            teacher = teacherDAO.getByUuid(uuid);
            return Mapper.mapTeacherToReadOnlyDTO(teacher).orElseThrow(() -> new TeacherNotFoundException("Teacher with uuid " + uuid + " was not found"));
        } catch (TeacherDAOException | TeacherNotFoundException e) {
//            e.printStackTrace();
            // logging
            throw e;
        }
    }

    @Override
    public List<TeacherReadOnlyDTO> getTeachersByLastname(String lastname) throws TeacherDAOException {
        List<Teacher> teachers = new ArrayList<>();
        try {
            teachers = teacherDAO.getByLastname(lastname);
            return teachers
                    .stream()
                    .map(Mapper::mapTeacherToReadOnlyDTO)
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());
        } catch (TeacherDAOException e) {
//            e.printStackTrace();
            throw e;
        }
    }
}
