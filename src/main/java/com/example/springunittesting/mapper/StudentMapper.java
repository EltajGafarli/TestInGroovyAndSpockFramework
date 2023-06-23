package com.example.springunittesting.mapper;


import com.example.springunittesting.dto.StudentDto;
import com.example.springunittesting.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)

public interface StudentMapper {
    Student studentDtoToStudent(StudentDto studentDto);

    StudentDto studentToStudentDto(Student student);

    void studentDtoUpdateStudent(@MappingTarget Student student, StudentDto studentDto);
}
