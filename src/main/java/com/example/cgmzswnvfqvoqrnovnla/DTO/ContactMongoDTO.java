package com.example.cgmzswnvfqvoqrnovnla.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ContactMongoDTO {
    private Long id;
    private String name;
    private LocalDate birthdate;
    private Set<String> numbers = new HashSet<>();//использован Set, чтобы два номера не повторялись
    private LocalDate creationDate;
}
