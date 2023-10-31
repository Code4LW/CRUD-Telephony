package com.example.cgmzswnvfqvoqrnovnla;

import com.example.cgmzswnvfqvoqrnovnla.model.ContactMongo;
import com.example.cgmzswnvfqvoqrnovnla.model.ContactPG;
import com.example.cgmzswnvfqvoqrnovnla.repository.ContactRepositoryMongo;
import com.example.cgmzswnvfqvoqrnovnla.repository.ContactRepositoryPG;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.Test;



import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Test
@SpringBootTest
public class ContactRepositoryTest {

    @Autowired
    private ContactRepositoryPG contactRepositoryPG;
    @Autowired
    private ContactRepositoryMongo contactRepositoryMongo;

    @Test
    public void testSaveContactPG() {
        Set<String> numbers = new HashSet<>();
        numbers.add("741741741");
        numbers.add("852852852");

        ContactPG contactPG = new ContactPG();
        contactPG.setName("John Smith");
        contactPG.setBirthdate(LocalDate.of(2000, 10, 10));
        contactPG.setNumbers(numbers);

        ContactPG savedContact = contactRepositoryPG.save(contactPG);

        Assert.assertNotNull(savedContact.getId());
        Assert.assertEquals("John Smith", savedContact.getName());
    }
    @Test
    public void testSaveContactMongo() {
        Set<String> numbers = new HashSet<>();
        numbers.add("741741741");
        numbers.add("852852852");

        ContactMongo contactMongo = new ContactMongo();
        contactMongo.setName("John Smith");
        contactMongo.setBirthdate(LocalDate.of(2000, 10, 10));
        contactMongo.setNumbers(numbers);

        ContactMongo savedContact = contactRepositoryMongo.save(contactMongo);

        Assert.assertNotNull(savedContact.getId());
        Assert.assertEquals("John Smith", savedContact.getName());
    }

}

