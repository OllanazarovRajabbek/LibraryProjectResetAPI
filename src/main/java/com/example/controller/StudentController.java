package com.example.controller;

import com.example.DTO.StudentDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentController {
    private List<StudentDTO> studentList = new LinkedList<>();

    public StudentController() {
        StudentDTO student1 = new StudentDTO();
        student1.setId(UUID.randomUUID().toString());
        student1.setName("Ali");
        student1.setSurname("Aliyev");
        student1.setPhone("999999");
        student1.setCreatedDate(LocalDate.now());
        studentList.add(student1);

        StudentDTO student2 = new StudentDTO();
        student2.setId(UUID.randomUUID().toString());
        student2.setName("Vali");
        student2.setSurname("Valiyev");
        student2.setPhone("0000000");
        student2.setCreatedDate(LocalDate.now());
        studentList.add(student2);

        StudentDTO student3 = new StudentDTO();
        student3.setId(UUID.randomUUID().toString());
        student3.setName("Rasul");
        student3.setSurname("Rasulov");
        student3.setPhone("222222");
        student3.setCreatedDate(LocalDate.now());
        studentList.add(student3);
    }

    @PostMapping("/create")
    public Boolean createdStudent(@RequestBody StudentDTO student) {
        student.setId(UUID.randomUUID().toString());
        student.setCreatedDate(LocalDate.now());
        studentList.add(student);
        return true;
    }

    @GetMapping("/all")
    public List<StudentDTO> getAll() {
        return studentList;
    }

    @GetMapping("/{id}")
    public StudentDTO getId(@PathVariable("id") String id) {
        for (StudentDTO student : studentList) {
            if (student != null) {
                if (student.getId().equals(id)) {
                    return student;
                }
            }
        }
        return null;
    }

    @PutMapping("/update/{id}")   //PUT
    public Boolean updateBook(@RequestBody StudentDTO student, @PathVariable("id") String id) {
        for (StudentDTO studentDTO : studentList) {
            if (studentDTO != null) {
                if (studentDTO.getId().equals(id)) {
                    studentDTO.setName(student.getName());
                    studentDTO.setSurname(student.getSurname());
                    studentDTO.setPhone(student.getPhone());
                    return true;
                }
            }
        }
        return false;
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteStudent(@PathVariable("id") String id) {
        return studentList.removeIf(studentDTO -> studentDTO.getId().equals(id));
    }
}
