package com.samfresh09.studentmanagebackend.services.impl;

import com.samfresh09.studentmanagebackend.entities.Student;
import com.samfresh09.studentmanagebackend.repositories.StudentDao;
import com.samfresh09.studentmanagebackend.services.interfaces.StudentService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;
    @Override
    public Student saveSudent(Student student) {
        if (this.studentDao.existsByCode(student.getCode())){
            throw new RuntimeException("Student already exists");
        }
        return this.studentDao.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        try {
            return this.studentDao.findAll();
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }

    }

    @Override
    public Student getStudentById(UUID id) {

        return this.studentDao.findById(id).orElseThrow(() -> new RuntimeException("Error: No Element found"));
    }

    @Override
    public Student findStudentByCode(String code) {
        if (!this.studentDao.existsByCode(code)){
            throw new RuntimeException("No Element found");
        }
        return this.studentDao.findStudentByCode(code);
    }

    @Override
    public boolean getStudentExist(String code) {
        return this.studentDao.existsByCode(code);
    }

    @Override
    public boolean deleteStudentById(UUID id) {
        boolean result = false;
        if (this.studentDao.existsById(id)){
            this.studentDao.deleteById(id);
            result = true;
        }
        return result;
    }

    @Override
    public Student updateStudent(UUID id, Student student) {
        if (!(student.getId()!=null && student.getId().equals(id))){
            throw new RuntimeException("Erreur client: Data is not correct");
        }
        return this.studentDao.save(student);
    }
}
