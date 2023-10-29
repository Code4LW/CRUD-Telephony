package com.example.cgmzswnvfqvoqrnovnla.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact")
public class ContactPG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "date_of_birth")
    private LocalDate birthdate;
    @Column(name = "phone_number", unique = true)
    @ElementCollection(fetch=EAGER)
    private Set<String> numbers = new HashSet<>();
    @Column(name = "date_of_creation")
    private LocalDate creationDate;

    @PrePersist
    private void init(){
        creationDate = LocalDate.now();
    }

}
