package com.example.service;

import com.example.DTO.StudentDTO;
import com.example.exceptions.AppBadException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    private List<StudentDTO> studentList = new LinkedList<>();

    public StudentService() {
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

    public Boolean create(StudentDTO student) {
        if (student.getName() == null || student.getName().trim().length() < 3) {
            throw new AppBadException("Student name required");
        }
        if (student.getSurname() == null || student.getSurname().trim().length() < 3) {
            throw new AppBadException("Student surname required");
        }
        student.setId(UUID.randomUUID().toString());
        student.setCreatedDate(LocalDate.now());
        studentList.add(student);

        return true;
    }

    public List<StudentDTO> getAll() {
        return studentList;
    }

    public StudentDTO getById(String id) {
        for (StudentDTO student : studentList) {
            if (student != null) {
                if (student.getId().equals(id)) {
                    return student;
                }
            }
        }
        throw new AppBadException("Student not found");
    }

    public StudentDTO update(String id, StudentDTO student) {
        if (student.getName() == null || student.getName().trim().length() < 3) {
            throw new AppBadException("Student name required");
        }
        if (student.getSurname() == null || student.getSurname().trim().length() < 3) {
            throw new AppBadException("Student surname required");
        }
        for (StudentDTO studentDTO : studentList) {
            if (studentDTO != null) {
                if (studentDTO.getId().equals(id)) {
                    studentDTO.setName(student.getName());
                    studentDTO.setSurname(student.getSurname());
                    studentDTO.setPhone(student.getPhone());
                    return studentDTO;
                }
            }
        }
        throw new AppBadException("Student not found");
    }

    public Boolean delete(String id) {
        return studentList.removeIf(studentDTO -> studentDTO.getId().equals(id));
    }

    public List<StudentDTO> search(String name, String surname) {
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentDTO student : studentList) {
            if (student != null) {
                if (student.getName().toLowerCase().contains(name.toLowerCase()) ||
                        student.getSurname().toLowerCase().contains(surname.toLowerCase())) {
                    dtoList.add(student);
                }
            }
        }
        return dtoList;
    }
}
