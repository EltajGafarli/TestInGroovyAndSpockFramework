package com.example.springunittesting.mapper

import com.example.springunittesting.dto.StudentDto
import com.example.springunittesting.entity.Student
import org.mapstruct.factory.Mappers
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest
class StudentMapperTest extends Specification {

    @Shared
    private StudentMapper mapper = Mappers.getMapper(StudentMapper.class)

    def "test convert Student to StudentDto"() {
        given:
        Student student = Student.builder().id(1).firstName("Cristiano").lastName("Ronaldo").fatherName("Jose").email("ronaldo@gmail.com").build()
        when:
        def studentDto = mapper.studentToStudentDto(student)
        then:
        compareStudentAndStudentDto(student, studentDto)
    }

    def "test convert StudentDto to Student"() {
        given:
        StudentDto studentDto = StudentDto.builder().firstName("Cristiano").lastName("Ronaldo").fatherName("Jose").email("ronaldo@gmail.com").build()
        when:
        def student = mapper.studentDtoToStudent(studentDto)
        then:
        compareStudentAndStudentDto(student, studentDto)
    }


    private static void compareStudentAndStudentDto(Student student, StudentDto studentDto) {
        assert student.getFirstName() == studentDto.getFirstName()
        assert student.getLastName() == studentDto.getLastName()
        assert student.getFatherName() == studentDto.getFatherName()
        assert student.getEmail() == studentDto.getEmail()
    }
}
