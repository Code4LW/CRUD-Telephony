package com.example.cgmzswnvfqvoqrnovnla;

import com.example.cgmzswnvfqvoqrnovnla.model.ContactPG;
import com.example.cgmzswnvfqvoqrnovnla.repository.ContactRepositoryPG;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@DataJpaTest
public class ContactRepoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    ContactRepositoryPG contactRepositoryPG;

    @Test
    public void getALlTest(){
        Set<String> numbers = new HashSet<>();
        numbers.add("+7777888999");
        numbers.add("+7444555666");
        Set<String> numbers2 = new HashSet<>();
        numbers2.add("+7111222333");
        numbers2.add("+7147147147");
        Set<String> numbers3 = new HashSet<>();
        numbers3.add("+7258258258");
        numbers3.add("+7369369369");
        ContactPG contact1 = new ContactPG(1l, "John Smith", LocalDate.of(2000,10,18),numbers, LocalDate.now());
        ContactPG contact2 =  new ContactPG(2l, "Jane Doe", LocalDate.of(2001,01,30),numbers2, LocalDate.now());
        ContactPG contact3 = new ContactPG(3l, "Sam Smith", LocalDate.of(2005,05,5),numbers3, LocalDate.now());
        contactRepositoryPG.save(contact1);
        contactRepositoryPG.save(contact2);
        contactRepositoryPG.save(contact3);
        List<ContactPG> contacts = contactRepositoryPG.findAll();
        Assert.assertEquals(contacts.get(0).getName(), "John Smith");
        Assert.assertEquals(contacts.get(1).getName(), "Jane Doe");
        Assert.assertEquals(contacts.get(2).getName(), "Sam Smith");
    }
}
