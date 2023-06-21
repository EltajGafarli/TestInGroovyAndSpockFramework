package com.example.springunittesting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {
    private String firstName;
    private String lastName;
    private String fatherName;
    private String email;
}
