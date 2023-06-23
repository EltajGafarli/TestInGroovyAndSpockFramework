package com.example.springunittesting.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, columnDefinition = "varchar")
    private String firstName;

    @Column(length = 50, columnDefinition = "varchar")
    private String lastName;

    @Column(length = 50, columnDefinition = "varchar")
    private String fatherName;

    @Column(length = 50, columnDefinition = "varchar")
    private String email;

    @CreationTimestamp
    private LocalDate createdOn;

    @UpdateTimestamp
    private LocalDate updatedOn;

    @Version
    private Integer version;

}
