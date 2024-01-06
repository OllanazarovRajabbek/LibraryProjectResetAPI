package com.example.controller;

import com.example.DTO.StudentDTO;
import com.example.exceptions.AppBadException;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private List<StudentDTO> studentList = new LinkedList<>();
    @Autowired
    private StudentService studentService;

    @PostMapping("")  //POST /student
    public ResponseEntity<?> createdStudent(@RequestBody StudentDTO student) {
        try {
            studentService.create(student);
        } catch (AppBadException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
//        ResponseEntity<?> response = new ResponseEntity<>(true, HttpStatus.OK);
        return ResponseEntity.ok(true); //200
    }

    @GetMapping("")    //GET /student
    public ResponseEntity<List<StudentDTO>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/{id}")   //GET /student/id
    public ResponseEntity<?> getId(@PathVariable("id") String id) {
        try {
            StudentDTO student = studentService.getById(id);
            return ResponseEntity.ok(student);
        } catch (AppBadException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")   //PUT /student/id
    public ResponseEntity<?> updateBook(@RequestBody StudentDTO student, @PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(studentService.update(id, student));
        } catch (AppBadException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")  //DELETE /student/id
    public ResponseEntity<Boolean> deleteStudent(@PathVariable("id") String id) {
        return ResponseEntity.ok(studentService.delete(id));
    }


    @GetMapping("/search/{name}/{surname}")
    public ResponseEntity<List<StudentDTO>> filter(@PathVariable(value = "name") String name,
                                                   @PathVariable(value = "surname") String surname) {
        return ResponseEntity.ok(studentService.search(name,surname));
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentDTO>> search(@RequestParam(value = "name", required = false) String name,
                                                    @RequestParam(value = "surname", defaultValue = "o") String surname) {
       return ResponseEntity.ok(studentService.search(name,surname));
    }


}
