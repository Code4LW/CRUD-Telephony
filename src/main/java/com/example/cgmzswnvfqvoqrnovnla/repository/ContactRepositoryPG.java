package com.example.cgmzswnvfqvoqrnovnla.repository;

import com.example.cgmzswnvfqvoqrnovnla.FIlter.Filter;
import com.example.cgmzswnvfqvoqrnovnla.model.ContactPG;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ContactRepositoryPG extends JpaRepository<ContactPG, Long> {
    ContactPG findByNumbers(String phoneNumber);

}
