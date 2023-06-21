package com.example.springunittesting.controller

import com.example.springunittesting.dto.StudentDto
import com.example.springunittesting.service.StudentService
import groovy.json.JsonBuilder
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [StudentController])
class StudentControllerTest extends Specification {


    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(
            new StudentController(
                    Mock(StudentService)
            )
    ).build()


    def "test find all"() {
        when:
        def result = mockMvc.perform(get("/student/all"))
        then:
        result.andExpect(status().isOk())
    }

    def "test find by id method"() {
        given:
        int studentById = 1L
        when:
        def result = mockMvc.perform(get("/student/id/" + studentById))
        then:
        result.andExpect(status().isOk())
    }

    def "test return not found when student not found by id"() {
        given:
        int studentById = 4L
        when:
        def result = mockMvc.perform(get("/student/id" + studentById))

        then:
        result.andExpect(status().isNotFound())
    }

    def "test save method"() {
        given:
        def studentDto = StudentDto.builder().firstName("Cristiano").lastName("Ronaldo").fatherName("Jose").email("ronaldo@gmail.com").build()
        def studentAsJson = new JsonBuilder(studentDto).toString()
        when:
        def result = mockMvc.perform(post("/student/save").contentType("application/json").content(studentAsJson))

        then:
        result.andExpect(status().isCreated())
    }


    def "test update method"() {
        given:
        long studentId = 1L
        StudentDto studentDto = StudentDto.builder().firstName("Cristiano").lastName("Ronaldo").fatherName("Jose").email("ronaldo@gmail.com").build()

        when:
        def result = mockMvc.perform(put("/student/update/{id}", studentId)
                .contentType("application/json")
                .content(new JsonBuilder(studentDto).toString()))

        then:
        result.andExpect(status().isOk())
    }

    def "test delete method"() {
        given:
        long studentId = 1L
        when:
        def result = mockMvc.perform(delete("/delete/" + studentId))
        then:
        result.andExpect(status().isOk())
    }

}
