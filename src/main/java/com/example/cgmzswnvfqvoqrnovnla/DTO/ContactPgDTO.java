package com.example.cgmzswnvfqvoqrnovnla.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;



@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ContactPgDTO {
    private Long id;

    private String name;

    private LocalDate birthdate;

    private Set<String> numbers = new HashSet<>();

    private LocalDate creationDate;
}
