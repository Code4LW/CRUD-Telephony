package com.example.cgmzswnvfqvoqrnovnla.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Document(collection = "contacts")
public class ContactMongo {
    @Id
    private Long id;
    private String name;
    @Field(name = "date_of_birth")
    private LocalDate birthdate;
    @Field(name = "phone_numbers")
    private Set<String> numbers = new HashSet<>();//использован Set, чтобы два номера не повторялись
    private LocalDate creationDate;

}
