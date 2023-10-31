package com.example.cgmzswnvfqvoqrnovnla;

import com.example.cgmzswnvfqvoqrnovnla.model.ContactMongo;
import com.example.cgmzswnvfqvoqrnovnla.model.ContactPG;
import com.example.cgmzswnvfqvoqrnovnla.repository.ContactRepositoryMongo;
import com.example.cgmzswnvfqvoqrnovnla.repository.ContactRepositoryPG;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@DataJpaTest()
public class ContactRepositoryTest {
    @Autowired
    private ContactRepositoryPG contactRepositoryPG;
    @Autowired
    private ContactRepositoryMongo contactRepositoryMongo;



    @Test
    public void testSaveContactPG() {
        Set <String> numbers = new HashSet<>();
        numbers.add("741741741");
        numbers.add("852852852");
        ContactPG contactPG = new ContactPG(1l, "John Smith", LocalDate.of(2000,10,10), numbers, LocalDate.now());
        contactRepositoryPG.save(contactPG);
        ContactPG saved = contactRepositoryPG.save(contactPG);
        assertNotNull(contactRepositoryPG.findById(1l));
        assertEquals(contactRepositoryPG.findById(1L).orElse(null).getName(), "Test Item");
        assertEquals("John Smith", contactRepositoryPG.findById(1l).get().getName());

    }
    @Test
    public void testSaveContactMongo() {
        Set <String> numbers = new HashSet<>();
        numbers.add("741741741");
        numbers.add("852852852");
        ContactMongo contactMongo = new ContactMongo(1l, "John Smith", LocalDate.of(2000,10,10), numbers, LocalDate.now());
        contactRepositoryMongo.save(contactMongo);
        ContactMongo saved = contactRepositoryMongo.save(contactMongo);
        assertNotNull(contactRepositoryMongo.findById(1l));
        assertEquals(contactRepositoryMongo.findById(1L).orElse(null).getName(), "Test Item");
        assertEquals("John Smith", contactRepositoryMongo.findById(1l).get().getName());

    }

}

