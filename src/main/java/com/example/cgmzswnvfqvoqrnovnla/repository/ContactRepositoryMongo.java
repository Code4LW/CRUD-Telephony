package com.example.cgmzswnvfqvoqrnovnla.repository;

import com.example.cgmzswnvfqvoqrnovnla.model.ContactMongo;
import com.example.cgmzswnvfqvoqrnovnla.model.ContactPG;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepositoryMongo extends MongoRepository<ContactMongo, Long> {
    ContactMongo findByNumbers(String phoneNumber);
    ContactMongo getById(Long Id);

}
