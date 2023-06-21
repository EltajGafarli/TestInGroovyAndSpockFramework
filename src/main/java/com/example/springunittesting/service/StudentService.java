package com.example.springunittesting.service;

import com.example.springunittesting.dto.StudentDto;
import com.example.springunittesting.entity.Student;
import com.example.springunittesting.exception.StudentNotFoundException;
import com.example.springunittesting.mapper.StudentMapper;
import com.example.springunittesting.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public List<StudentDto> findAll() {
        return this.studentRepository.findAll()
                .stream()
                .map(studentMapper::studentToStudentDto)
                .toList();
    }

    public StudentDto findById(long id) {
        Student student = this.studentRepository.findById(id).orElseThrow(
                () -> new StudentNotFoundException("Student not found with this id"));
        return this.studentMapper.studentToStudentDto(student);
    }


    @Transactional
    public void saveStudent(StudentDto studentDto) {
        Student student = this.studentMapper.studentDtoToStudent(studentDto);
        this.studentRepository.save(student);
    }

    @Transactional
    public void deleteById(long id) {
        this.studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(long id, StudentDto studentDto) {
        Student student = this.studentRepository.findById(id)
                .orElseThrow(
                        () -> new StudentNotFoundException("Student not found with this id"));

        this.studentMapper.studentDtoUpdateStudent(student, studentDto);
        this.studentRepository.save(student);
    }

}
