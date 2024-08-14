package com.samfresh09.studentmanagebackend.services.interfaces;

import com.samfresh09.studentmanagebackend.entities.Student;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    Student saveSudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(UUID id);
    Student findStudentByCode(String code);
    boolean getStudentExist(String code);
    boolean deleteStudentById(UUID id);
    Student updateStudent(UUID id, Student student);
}
