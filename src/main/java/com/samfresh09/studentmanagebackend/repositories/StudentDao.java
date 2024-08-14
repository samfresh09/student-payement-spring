package com.samfresh09.studentmanagebackend.repositories;

import com.samfresh09.studentmanagebackend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentDao extends JpaRepository<Student, UUID> {
    Student findStudentByCode(String code);
    boolean existsByCode(String code);
}
