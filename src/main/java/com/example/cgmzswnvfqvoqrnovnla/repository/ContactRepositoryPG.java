package com.example.cgmzswnvfqvoqrnovnla.repository;

import com.example.cgmzswnvfqvoqrnovnla.model.ContactPG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ContactRepositoryPG extends JpaRepository<ContactPG, Long> {
    ContactPG findByNumbers(String phoneNumber);
}
