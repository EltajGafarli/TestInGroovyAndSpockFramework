package com.example.springunittesting.service

import com.example.springunittesting.dto.StudentDto
import com.example.springunittesting.entity.Student
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class StudentServiceTest extends Specification {


    private StudentService studentService = Mock(StudentService)

    def setup() {
        studentService.findAll() >> Arrays.asList(
                Student.builder().firstName("Jack").lastName("Daniels").fatherName("Taylor").email("jackd@gmail.com").build(),
                Student.builder().firstName("Leonardo").lastName("Da Vinchi").fatherName("Ancelotti").email("leo@gmail.com").build(),
                Student.builder().firstName("Cristiano").lastName("Ronaldo").fatherName("Jose").email("ronaldo@gmail.com").build()
        )

        studentService.findById(1L) >> StudentDto.builder().firstName("Cristiano").lastName("Ronaldo").fatherName("Jose").email("ronaldo@gmail.com").build()


    }

    def "test findAll method"() {
        when:

        def result = studentService.findAll()
        then:
        result.size() == 3
    }

    def "test findBy id"() {
        given:
        def student = StudentDto.builder().firstName("Cristiano").lastName("Ronaldo").fatherName("Jose").email("ronaldo@gmail.com").build()
        when:
        def studentById = this.studentService.findById(1L)
        then:
        compareStudents(student, studentById)
    }

    def "test save Student method"() {
        given:
        def student = StudentDto.builder().firstName("Cristiano").lastName("Ronaldo").fatherName("Jose").email("ronaldo@gmail.com").build()
        when:
        studentService.saveStudent(student)
        then:
        1 * studentService.saveStudent(_)

    }

    def "test delete student by id method"() {
        when:
        studentService.deleteById(1L)
        studentService.deleteById(2L)
        then:
        2 * studentService.deleteById(_)

    }

    def "should update student by id"() {
        given:
        def studentDto = StudentDto.builder().firstName("Cristiano").lastName("Ronaldo").fatherName("Jose").email("ronaldo@gmail.com").build()

        when:
        studentService.updateStudent(1L, studentDto)
        then:
        1 * studentService.updateStudent(_, _)
    }


    void compareStudents(StudentDto student1, StudentDto student2) {
        assert student1.getFirstName() == student2.getFirstName()
        assert student1.getFatherName() == student2.getFatherName()
        assert student1.getLastName() == student2.getLastName()
        assert student1.getEmail() == student2.getEmail()
    }
}
