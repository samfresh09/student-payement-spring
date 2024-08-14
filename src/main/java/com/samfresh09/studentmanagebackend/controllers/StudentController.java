package com.samfresh09.studentmanagebackend.controllers;

import com.samfresh09.studentmanagebackend.entities.Student;
import com.samfresh09.studentmanagebackend.services.interfaces.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/student")
public class StudentController {
    private StudentService studentService;

    @PostMapping("/")
    public Student saveSudent(@RequestBody Student student) {
        return this.studentService.saveSudent(student);
    }

    @GetMapping("/")
    public List<Student> getAllStudents() {
       return this.studentService.getAllStudents();
    }

    @GetMapping("filter-code/{code}")
    public Student getStudentExist(@PathVariable String code) {
        return this.studentService.findStudentByCode(code);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable UUID id) {
        return this.studentService.getStudentById(id);
    }


   @DeleteMapping("/{id}")
    public boolean deleteStudentById(@PathVariable UUID id) {
        return this.studentService.deleteStudentById(id);
    }

    @PutMapping("/")
    public Student updateStudent(UUID id, Student student) {
        return this.studentService.updateStudent(id, student);
    }
}
