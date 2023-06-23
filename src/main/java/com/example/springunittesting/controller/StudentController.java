package com.example.springunittesting.controller;

import com.example.springunittesting.dto.StudentDto;
import com.example.springunittesting.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;


    @GetMapping(path = "id/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public StudentDto findById(@PathVariable long id) {
        return this.studentService.findById(id);
    }

    @GetMapping(path = "/all")
    @ResponseStatus(code = HttpStatus.OK)
    public List<StudentDto> findAll() {
        return this.studentService.findAll();
    }


    @PostMapping(path = "save")
    public ResponseEntity<String> saveStudent(@RequestBody StudentDto studentDto) {
        this.studentService.saveStudent(studentDto);
        return new ResponseEntity<>("Student saved successfully", HttpStatus.CREATED);
    }


    @PutMapping(path = "update/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable long id, @RequestBody StudentDto studentDto) {
        this.studentService.updateStudent(id, studentDto);
        return new ResponseEntity<>("Student updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable long id) {
        this.studentService.deleteById(id);
        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
    }

}
